package app.service;

import app.entity.LeftMenu;

import java.util.List;

/**
 * 左侧菜单(LeftMenu)表服务接口
 *
 * @author makejava
 * @since 2020-12-16 15:33:03
 */
public interface LeftMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LeftMenu queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<LeftMenu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param leftMenu 实例对象
     * @return 实例对象
     */
    LeftMenu insert(LeftMenu leftMenu);

    /**
     * 修改数据
     *
     * @param leftMenu 实例对象
     * @return 实例对象
     */
    LeftMenu update(LeftMenu leftMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<LeftMenu> queryAll();

}