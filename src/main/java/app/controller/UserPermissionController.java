package app.controller;

import app.entity.UserPermission;
import app.service.UserPermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户权限表(UserPermission)表控制层
 *
 * @author makejava
 * @since 2021-01-16 12:47:31
 */
@RestController
@RequestMapping("userPermission")
public class UserPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private UserPermissionService userPermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserPermission selectOne(Integer id) {
        return this.userPermissionService.queryById(id);
    }

}