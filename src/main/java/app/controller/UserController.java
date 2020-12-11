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

//    注册接口
    @PostMapping("/register")
    public ResponseData register(@RequestBody User user){
//        判断用户名是否存在
        if (userService.login(user) != null){
            return new ResponseData(401,"用户名[" + user.getUserName() + "]已存在",null);
        }
//        用户名不存在，创建用户
         User registerUser = userService.insert(user);
         return new ResponseData(200,"注册成功",registerUser);
    }


    @PostMapping("/login")
    public ResponseData login(@RequestBody User user){
//        根据用户名获取对应的用户信息
        User resultUser = userService.login(user);
//        判断密码是否正确，如正确，将token返回
        if ( resultUser != null && CommonUtils.verifyPassword(user.getPassword(),resultUser.getPassword())){
            String token = JwtTokenUtils.generatorToken(new JWTInfo(resultUser.getId()+""));
            return new ResponseData(10000,"登录成功",token);
        }
        LOGGER.info("登录失败");
        return new ResponseData(9999,"登录失败，请检查用户名和密码",null);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public ResponseData delete(Integer id){
//        删除用户
        if (userService.deleteById(id)){
            return new ResponseData(200,"删除用户成功",null);
        }
        return new ResponseData(401,"删除用户失败,请确认用户的状态",null);
    }






}