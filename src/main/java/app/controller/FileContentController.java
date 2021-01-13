package app.controller;

import app.entity.FileContent;
import app.service.FileContentService;
import app.utils.CommonUtils;
import app.vo.ResponseData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("getTree")
    public ResponseData getTree() {
        return ResponseData.success(fileContentService.getTree());
    }

    @GetMapping("/getNodeById")
    public ResponseData getNodeById(Integer id){
        return new ResponseData(200,"查询成功",fileContentService.queryById(id));
    }

    @PostMapping("/updateFile")
    public ResponseData updateFile(@RequestBody FileContent fileContent){
        return new ResponseData(200,"修改成功",fileContentService.update(fileContent));
    }


    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody FileContent fileContent){
        Map<String,Object> map = new HashMap<>();
        map = CommonUtils.bean2Map(ResponseData.success(fileContentService.insert(fileContent)),map);
        map.put("tree",fileContentService.getTree());
        return map;
    }

    @GetMapping("/getTreeNew")
    public ResponseData getTreeNew(){
        FileContent fileContent = new FileContent();
        fileContent.setParentId(0);
        return ResponseData.success(fileContentService.getTreeNew(fileContent));
    }


}