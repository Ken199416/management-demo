package app.service.impl;

import app.dao.FileContentDao;
import app.entity.FileContent;
import app.service.FileContentService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * (FileContent)表服务实现类
 *
 * @author makejava
 * @since 2020-12-18 14:39:29
 */
@Service("fileContentService")
public class FileContentServiceImpl implements FileContentService {
    @Resource
    private FileContentDao fileContentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FileContent queryById(Integer id) {
        return this.fileContentDao.queryById(id);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<FileContent> queryAll() {
//        先查找出所有顶级父节点
        List<FileContent> fileContents = fileContentDao.queryByParent(0);
        List<FileContent> result = new ArrayList<>();
//        通过顶级父节点向下寻找所有的子节点
        for (FileContent fileContent : fileContents) {
            result.add(getFileContent(fileContent));
        }
        return result;
    }

//  递归查找所有节点子节点，并set到节点中
    public FileContent getFileContent(FileContent fileContent){
        List<FileContent> childrenList = new ArrayList<>();
        //        用当前节点的id去查询所有的子节点
        List<FileContent> childrenFileContents = fileContentDao.queryByParent(fileContent.getId());
        for (FileContent children : childrenFileContents) {
//            是node节点。则把这个子节点加入到子节点list中
            if (children.getIsNode()==1){
                childrenList.add(children);
            }else {
//                不是node节点，说明下面可能还有节点，需要递归查询
                childrenList.add(getFileContent(children));
            }
        }
        fileContent.setChildren(childrenList);
        return fileContent;
    }

    /**
     * 新增数据
     *
     * @param fileContent 实例对象
     * @return 实例对象
     */
    @Override
    public FileContent insert(FileContent fileContent) {
        this.fileContentDao.insert(fileContent);
        return fileContent;
    }

    /**
     * 修改数据
     *
     * @param fileContent 实例对象
     * @return 实例对象
     */
    @Override
    public FileContent update(FileContent fileContent) {
        this.fileContentDao.update(fileContent);
        return this.queryById(fileContent.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.fileContentDao.deleteById(id) > 0;
    }
}