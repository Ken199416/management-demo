package app.entity;

import java.io.Serializable;

/**
 * 邮箱code(EmailCode)实体类
 *
 * @author makejava
 * @since 2021-01-12 15:22:18
 */
public class EmailCode implements Serializable {
    private static final long serialVersionUID = 227230927160076252L;

    private Integer id;

    private String code;

    private Integer userId;
    /**
     * 1：激活
     */
    private Integer type;
    /**
     * 1：有效
     * 0：无效
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}