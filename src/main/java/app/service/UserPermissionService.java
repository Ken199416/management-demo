package app.service;

import app.entity.UserPermission;

import java.util.List;

/**
 * 用户权限表(UserPermission)表服务接口
 *
 * @author makejava
 * @since 2021-01-16 12:47:30
 */
public interface UserPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserPermission queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserPermission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userPermission 实例对象
     * @return 实例对象
     */
    UserPermission insert(UserPermission userPermission);

    /**
     * 修改数据
     *
     * @param userPermission 实例对象
     * @return 实例对象
     */
    UserPermission update(UserPermission userPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    List<UserPermission> getPermissionByUserId(UserPermission userPermission);

}