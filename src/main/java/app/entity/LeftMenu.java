package app.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 左侧菜单(LeftMenu)实体类
 *
 * @author makejava
 * @since 2020-12-16 15:33:03
 */
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

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getPri() {
        return pri;
    }

    public void setPri(Integer pri) {
        this.pri = pri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}