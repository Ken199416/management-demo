package app.service.impl;

import app.entity.User;
import app.dao.UserDao;
import app.service.UserService;
import app.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-11-28 20:01:32
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit("",offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
//        默认创建的用户，状态都设置为1
        user.setStatus(1);
//        加密密码
        user.setPassword(CommonUtils.encoderPassword(user.getPassword()));
        int len = this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
//        如果密码不为空,将密码加密
        if (StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(CommonUtils.encoderPassword(user.getPassword()));
        }
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public User login(User user) {
        return userDao.queryByUserName(user);
    }

    @Override
    public List<User> getUserBySearch(String query, int pageNum, int pageSize) {
        return userDao.queryAllByLimit(query,(pageNum-1) * pageSize,pageSize);
    }

    @Override
    public int getUserCountBySearch(User user) {
        return userDao.getUserCountBySearch(user);
    }


}