package app.dao;

import app.entity.EmailCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import javax.annotation.Resource;
import java.util.List;

/**
 * 邮箱code(EmailCode)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-12 15:22:18
 */
@Mapper
public interface EmailCodeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmailCode queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EmailCode> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param emailCode 实例对象
     * @return 对象列表
     */
    List<EmailCode> queryAll(EmailCode emailCode);

    /**
     * 新增数据
     *
     * @param emailCode 实例对象
     * @return 影响行数
     */
    int insert(EmailCode emailCode);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmailCode> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<EmailCode> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmailCode> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<EmailCode> entities);

    /**
     * 修改数据
     *
     * @param emailCode 实例对象
     * @return 影响行数
     */
    int update(EmailCode emailCode);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int activate(Integer userId,String code);

    void expiryCode(Integer userId, String code);
}