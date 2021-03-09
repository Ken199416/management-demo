package app.service.impl;

import app.dao.UserPermissionDao;
import app.entity.UserPermission;
import app.service.UserPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户权限表(UserPermission)表服务实现类
 *
 * @author makejava
 * @since 2021-01-16 12:47:31
 */
@Service("userPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService {
    @Resource
    private UserPermissionDao userPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserPermission queryById(Integer id) {
        return this.userPermissionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserPermission> queryAllByLimit(int offset, int limit) {
        return this.userPermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userPermission 实例对象
     * @return 实例对象
     */
    @Override
    public UserPermission insert(UserPermission userPermission) {
        this.userPermissionDao.insert(userPermission);
        return userPermission;
    }

    /**
     * 修改数据
     *
     * @param userPermission 实例对象
     * @return 实例对象
     */
    @Override
    public UserPermission update(UserPermission userPermission) {
        this.userPermissionDao.update(userPermission);
        return this.queryById(userPermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userPermissionDao.deleteById(id) > 0;
    }

    @Override
    public List<UserPermission> getPermissionByUserId(UserPermission userPermission) {
        return userPermissionDao.getPermissionByUserId(userPermission);
    }
}