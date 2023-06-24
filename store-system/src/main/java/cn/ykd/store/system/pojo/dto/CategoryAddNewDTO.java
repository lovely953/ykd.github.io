package cn.ykd.store.system.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 类别实体dto类
 */
@Data
public class CategoryAddNewDTO implements Serializable {

    /**
     * 类别名称
     */
    private String name;
    /**
     * 父级类别id。如果无父级则为0
     */
    private Long parent_id;

    /**
     * 关键词列表，各关键词用英文的逗号分隔
     */
    private String keywords;
    /**
     * 自定义排序时间
     */
    private Integer sort;
    /**
     * 图标图片的url
     */
    private String icon;
    /**
     * 是否启用 1=启用 0=未启用
     */
    private Integer enable;

    /**
     * 是否为父级，1=父级，0=不是父级
     */
    private Integer is_parent;

    /**
     * 是否显示在导航栏 1=启用 0=未启用
     */
    private Integer is_display;

}
