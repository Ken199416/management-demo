package app.service;

import app.entity.FileContent;

import java.util.List;


/**
 * (FileContent)表服务接口
 *
 * @author makejava
 * @since 2020-12-18 14:39:29
 */
public interface FileContentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FileContent queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<FileContent> queryAll();

    /**
     * 新增数据
     *
     * @param fileContent 实例对象
     * @return 实例对象
     */
    FileContent insert(FileContent fileContent);

    /**
     * 修改数据
     *
     * @param fileContent 实例对象
     * @return 实例对象
     */
    FileContent update(FileContent fileContent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}