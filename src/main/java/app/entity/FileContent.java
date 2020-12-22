package app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (FileContent)实体类
 *
 * @author makejava
 * @since 2020-12-18 14:39:29
 */
public class FileContent implements Serializable {
    private static final long serialVersionUID = -35397758646744328L;

    private List<FileContent> children;

    private Integer isShowAdd;

    public Integer getIsShowAdd() {
        return isShowAdd;
    }

    public void setIsShowAdd(Integer isShowAdd) {
        this.isShowAdd = isShowAdd;
    }

    public List<FileContent> getChildren() {
        return children;
    }

    public void setChildren(List<FileContent> children) {
        this.children = children;
    }

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public Integer getIsNode() {
        return isNode;
    }

    public void setIsNode(Integer isNode) {
        this.isNode = isNode;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}