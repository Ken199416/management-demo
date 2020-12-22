package app.controller;

import app.entity.FileContent;
import app.service.FileContentService;
import app.vo.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FileContent)表控制层
 *
 * @author makejava
 * @since 2020-12-18 14:41:04
 */
@RestController
@RequestMapping("fileContent")
public class FileContentController {
    /**
     * 服务对象
     */
    @Resource
    private FileContentService fileContentService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("queryAll")
    public ResponseData selectOne() {
        return new ResponseData(200,"查询成功",fileContentService.queryAll());
    }

    @GetMapping("/getNodeById")
    public ResponseData getNodeById(Integer id){
        return new ResponseData(200,"查询成功",fileContentService.queryById(id));
    }

}