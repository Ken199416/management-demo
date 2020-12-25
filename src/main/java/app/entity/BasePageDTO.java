package app.entity;

import java.util.List;

/**
 * @ClassName BasePageDTO
 * @Description 分页基类
 * @Author haomj
 * @Date 2020/12/25 16:41
 * @Version 1.0
 */
public class BasePageDTO<T> {
    private int pageSize;
    private int pageNum;
    private int total;
    private List<T> data;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
