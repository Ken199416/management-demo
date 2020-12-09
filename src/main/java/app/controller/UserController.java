package app.controller;

import app.entity.JWTInfo;
import app.entity.User;
import app.service.UserService;
import app.utils.CommonUtils;
import app.utils.JwtTokenUtils;
import app.vo.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-11-28 20:01:32
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id, HttpServletRequest request) {
        JWTInfo jwtInfo = JwtTokenUtils.getTokenInfo(request.getHeader("Authorization"));
        LOGGER.info("用户是：{}",jwtInfo.getUid());
        return this.userService.queryById(id);
    }

    @PostMapping("/register")
    public ResponseData register(@RequestBody User user){
         User registerUser = userService.insert(user);
         ResponseData responseData = new ResponseData(200,"注册成功",registerUser);
         return responseData;
    }


    @PostMapping("/login")
    public ResponseData login(@RequestBody User user){
        User resultUser = userService.login(user);
        if ( resultUser != null && CommonUtils.verifyPassword(user.getPassword(),resultUser.getPassword())){
            String token = JwtTokenUtils.generatorToken(new JWTInfo(resultUser.getId()+""));
            return new ResponseData(10000,"登录成功",token);
        }
        LOGGER.info("登录失败");
        return new ResponseData(9999,"登录失败，请检查用户名和密码",null);
    }

}