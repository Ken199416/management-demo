package app.dao;

import app.entity.LeftMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 左侧菜单(LeftMenu)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-16 15:33:03
 */
@Mapper
public interface LeftMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LeftMenu queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<LeftMenu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<LeftMenu> getAll(Integer parent);

    /**
     * 新增数据
     *
     * @param leftMenu 实例对象
     * @return 影响行数
     */
    int insert(LeftMenu leftMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<LeftMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<LeftMenu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<LeftMenu> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<LeftMenu> entities);

    /**
     * 修改数据
     *
     * @param leftMenu 实例对象
     * @return 影响行数
     */
    int update(LeftMenu leftMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}