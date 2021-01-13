package app.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-11-28 20:01:24
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 958025306529671663L;
    
    private Integer id;
    
    private String userName;
    
    private String password;
    /**
    * 1：有效，0：无效
    */
    private Integer status;

    private String email;


}