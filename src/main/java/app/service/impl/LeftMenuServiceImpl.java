package app.service.impl;

import app.dao.LeftMenuDao;
import app.entity.LeftMenu;
import app.entity.UserPermission;
import app.service.LeftMenuService;
import app.service.UserPermissionService;
import app.utils.JwtTokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 左侧菜单(LeftMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-12-16 15:33:03
 */
@Service("leftMenuService")
public class LeftMenuServiceImpl implements LeftMenuService {
    @Resource
    private LeftMenuDao leftMenuDao;

    @Resource
    private UserPermissionService userPermissionService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LeftMenu queryById(Integer id) {
        return this.leftMenuDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<LeftMenu> queryAllByLimit(int offset, int limit) {
        return this.leftMenuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param leftMenu 实例对象
     * @return 实例对象
     */
    @Override
    public LeftMenu insert(LeftMenu leftMenu) {
        this.leftMenuDao.insert(leftMenu);
        return leftMenu;
    }

    /**
     * 修改数据
     *
     * @param leftMenu 实例对象
     * @return 实例对象
     */
    @Override
    public LeftMenu update(LeftMenu leftMenu) {
        this.leftMenuDao.update(leftMenu);
        return this.queryById(leftMenu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.leftMenuDao.deleteById(id) > 0;
    }

    @Override
    public List<LeftMenu> queryAll() {


        List<LeftMenu> menuLists = leftMenuDao.getAll(0);
        for (LeftMenu menuList: menuLists) {
//            把当前的id作为父id再去查询是否有下级目录
            menuList.setChlidren(leftMenuDao.getAll(menuList.getId()));
        }
        return menuLists;
    }

    @Override
    public List<LeftMenu> queryUserMenuList(HttpServletRequest request) {
//        用户应该有的权限的Id列表
        List<Integer> userMenusIds = new ArrayList<>();
//        通过request中的token获取当前登录的用户id
        String userId = JwtTokenUtils.getTokenInfo(request.getHeader("Authorization")).getUid();
//        通过用户id查到用户拥有的权限关系
        UserPermission userPermission = new UserPermission();
        userPermission.setUserId(Integer.parseInt(userId));
        List<UserPermission> userPermissions = userPermissionService.getPermissionByUserId(userPermission);
//        通过权限关系，找到用户拥有的所有权限，并返回
        for (UserPermission up : userPermissions) {
            userMenusIds.add(up.getRoutePathId());
        }
        List<LeftMenu> menuLists = leftMenuDao.getUserAll(0,userMenusIds);
        for (LeftMenu menuList: menuLists) {
//            把当前的id作为父id再去查询是否有下级目录
            menuList.setChlidren(leftMenuDao.getUserAll(menuList.getId(),userMenusIds));
        }
         return menuLists;
    }
}