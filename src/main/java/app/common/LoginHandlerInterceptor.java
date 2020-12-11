package app.common;

import app.entity.JWTInfo;
import app.utils.JwtTokenUtils;
import app.vo.ResponseData;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拦截器，实现HandlerInterceptor接口
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    /**
     * 请求后的处理，response返回之前的处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 请求没进入控制层（Controller）之前进入
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        放行Vue传过来一个OPTIONS请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
//            logger.info("OPTIONS请求，放行");
            return true;
        }
//        从request的Header中获取Authorization
        String token = request.getHeader("Authorization");
//        不等于空，才继续判断jwt是否有效
        if (StringUtils.isNotBlank(token)){
            try {
//                获取一个JWT实体，如果JWT失效或者非法，会抛出不同的异常
                JWTInfo jwtInfo = JwtTokenUtils.getTokenInfo(token);
            }catch (SignatureException e){
//                JWT非法
                ResponseData responseBean = new ResponseData();
                responseBean.setCode(40009);
                responseBean.setMessage("未登录");
                returnJson(response,JSON.toJSONString(responseBean));
            }catch (ExpiredJwtException e){
//                JWT过期
                ResponseData responseBean = new ResponseData();
                responseBean.setCode(40009);
                responseBean.setMessage("登录信息已过期，请重新登录！");
                returnJson(response,JSON.toJSONString(responseBean));
            }catch (Exception e){
//                其他异常
                ResponseData responseBean = new ResponseData();
                responseBean.setCode(40009);
                responseBean.setMessage("登录信息异常，请重新登录！");
                returnJson(response,JSON.toJSONString(responseBean));
            }
//            没有抛出异常则放行请求，进入Controller
            return true;
        }
        ResponseData responseBean = new ResponseData();
        responseBean.setCode(40004);
        responseBean.setMessage("未登录");
        returnJson(response, JSON.toJSONString(responseBean));
        return false;
    }


    /**
     * 当jwt校验失败的时候，手动返回给前端response
     * @param response
     * @param json
     * @throws Exception
     */
    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");

        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
