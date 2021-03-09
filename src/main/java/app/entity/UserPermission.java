package app.entity;

import java.io.Serializable;

/**
 * 用户权限表(UserPermission)实体类
 *
 * @author makejava
 * @since 2021-01-16 12:47:30
 */
public class UserPermission implements Serializable {
    private static final long serialVersionUID = -47465329903458719L;

    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 路由的跳转path
     */
    private Integer routePathId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoutePathId() {
        return routePathId;
    }

    public void setRoutePathId(Integer routePathId) {
        this.routePathId = routePathId;
    }

}