package cn.ykd.store.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 属性的标准实体类
 */
@Data
public class AttributeStrandardVO implements Serializable {

    /**
     * 记录id
     */
    private Long id;
    /**
     *所属属性模板id
     */
    private Long template_id;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 简介（某些属性名称相同，进行补充说明）
     */
    private String description;

    /**
     * 属性类型，1=销售属性 0=非销售属性
     */
    private Integer type;

    /**
     * 输入类型 0=手动输入 1=单选 2=多选，3=单选（下拉列表），4=多选（下拉列表）
     */
    private Integer input_type;

    /**
     * 备选值列表
     */
    private String value_list;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 自定义排序
     */
    private Integer sort;

    /**
     * 是否允许自定义 1=允许 0=禁止
     */
    private Integer is_allow_customize;
}
