package app.controller;

import app.entity.LeftMenu;
import app.service.LeftMenuService;
import app.vo.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 左侧菜单(LeftMenu)表控制层
 *
 * @author makejava
 * @since 2020-12-16 15:33:04
 */
@RestController
@RequestMapping("leftMenu")
public class LeftMenuController {
    /**
     * 服务对象
     */
    @Resource
    private LeftMenuService leftMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public LeftMenu selectOne(Integer id) {
        return this.leftMenuService.queryById(id);
    }

    @GetMapping("/getAll")
    public ResponseData getAll(){
        List<LeftMenu> data= leftMenuService.queryAll();
        return new ResponseData(200,"查询左侧列表成功",data);
    }

}