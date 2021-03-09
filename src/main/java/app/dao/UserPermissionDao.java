package app.dao;

import app.entity.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户权限表(UserPermission)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-16 12:47:30
 */
@Mapper
public interface UserPermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserPermission queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserPermission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userPermission 实例对象
     * @return 对象列表
     */
    List<UserPermission> queryAll(UserPermission userPermission);

    /**
     * 新增数据
     *
     * @param userPermission 实例对象
     * @return 影响行数
     */
    int insert(UserPermission userPermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserPermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserPermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserPermission> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserPermission> entities);

    /**
     * 修改数据
     *
     * @param userPermission 实例对象
     * @return 影响行数
     */
    int update(UserPermission userPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<UserPermission> getPermissionByUserId(UserPermission userPermission);

}