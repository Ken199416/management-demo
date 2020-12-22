package app.dao;

import app.entity.FileContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * (FileContent)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-18 14:39:29
 */
@Mapper
public interface FileContentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FileContent queryById(Integer id);

    /**
     * 查询指定行数据
     * @return 对象列表
     */
    List<FileContent> queryFolder();


    List<FileContent> queryNode();

    List<FileContent> queryByParent(Integer parentId);


    /**
     * 新增数据
     *
     * @param fileContent 实例对象
     * @return 影响行数
     */
    int insert(FileContent fileContent);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FileContent> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FileContent> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FileContent> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<FileContent> entities);

    /**
     * 修改数据
     *
     * @param fileContent 实例对象
     * @return 影响行数
     */
    int update(FileContent fileContent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}