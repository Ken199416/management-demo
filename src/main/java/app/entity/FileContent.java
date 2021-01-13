package app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (FileContent)实体类
 *
 * @author makejava
 * @since 2020-12-18 14:39:29
 */
@Data
public class FileContent implements Serializable {
    private static final long serialVersionUID = -35397758646744328L;

    private List<FileContent> children;

    private Integer isShowAdd;

    private Integer id;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件内容
     */
    private String fileContent;
    /**
     * 是文件节点，1：是，0：不是
     */
    private Integer isNode;
    /**
     * 父节点
     */
    private Integer parentId;

    private Date createTime;

    private Date updateTime;
    /**
     * 1:有效，0：删除
     */
    private Integer status;


}