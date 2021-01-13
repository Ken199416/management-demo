package app.controller;

import app.dao.EmailCodeDao;
import app.dao.UserDao;
import app.entity.EmailCode;
import app.entity.JWTInfo;
import app.entity.User;
import app.service.EmailService;
import app.service.UserService;
import app.utils.CommonUtils;
import app.utils.JwtTokenUtils;
import app.vo.ResponseData;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailCodeDao emailCodeDao;

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
        if (userDao.getUserByEmail(user) != 0){
            return new ResponseData(402,"邮箱：" + user.getEmail() + "已存在",user);
        }
//        用户名不存在，创建用户,发送邮件
        User registerUser = userService.insert(user);
        emailService.sendEmail(user);
        return new ResponseData(200,"您的账号已注册成功，验证码已发送至您的邮箱，输入验证码后即可激活账户",registerUser.getId());
    }

//    激活账号
    @GetMapping("/activate")
    public ResponseData activate(Integer uid,String emailCode){
        int len = emailCodeDao.activate(uid,emailCode);
        if (len == 0){
            return new ResponseData(401,"验证码错误，请重试",null);
        }
//        将用户状态置位有效,验证码置位失效
        userDao.activate(uid);
        emailCodeDao.expiryCode(uid,emailCode);
        return ResponseData.success("恭喜您，账号已激活！",emailCode);
    }


    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user){
//        根据用户名获取对应的用户信息
        User resultUser = userService.login(user);
//        判断密码是否正确，如正确，将token返回
        if ( resultUser != null && CommonUtils.verifyPassword(user.getPassword(),resultUser.getPassword())){
            String token = JwtTokenUtils.generatorToken(new JWTInfo(resultUser.getId()+""));
            HashMap<String,Object> map = new HashMap<>();
            map.put("customerName",user.getUserName());
            return CommonUtils.bean2Map(new ResponseData(200,"登陆成功",token),map);
        }
        LOGGER.info("登录失败");
        return CommonUtils.bean2Map(new ResponseData(9999,"登录失败，请检查用户名和密码",null),new HashMap());
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


    @PostMapping("/updatePassword")
    public ResponseData updatePassword(@RequestBody User user){
        userService.update(user);
        return new ResponseData(200,"修改成功",null);
    }

    @RequestMapping("/queryUser")
    public Map<String,Object> getUsers(String query , int pageNum , int pageSize){
        List<User> userList = userService.getUserBySearch(query,pageNum,pageSize);
        User user = new User();
        user.setUserName(query);
        int rows = userService.getUserCountBySearch(user);
        Map<String,Object> response = new HashMap<>();
        response.put("code",200);
        response.put("message","查询成功");
        response.put("data",userList);
        response.put("total",rows);
        return response;
    }


}