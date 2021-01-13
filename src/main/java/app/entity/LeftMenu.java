package app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 左侧菜单(LeftMenu)实体类
 *
 * @author makejava
 * @since 2020-12-16 15:33:03
 */
@Data
public class LeftMenu implements Serializable {
    private static final long serialVersionUID = 198141802931643214L;

    private List<LeftMenu> chlidren;

    public List<LeftMenu> getChlidren() {
        return chlidren;
    }

    public void setChlidren(List<LeftMenu> chlidren) {
        this.chlidren = chlidren;
    }

    private Integer id;

    private String menuName;
    /**
     * 级别
     */
    private Integer leave;
    /**
     * 父级
     */
    private Integer parent;
    /**
     * 排序
     */
    private Integer pri;

    private String icon;

    private String path;

    private Integer menuStatus;


}