package cn.ykd.store.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 属性模板标准项vo类
 *
 */
@Data
public class Attribute_templateStandardVO implements Serializable {
    /**
     * 属性模板id
     */
    private Long id;
    /**
     * 属性模板你名称
     */
    private String name;
    /**
     * 属性模板名称拼音
     */
    private String pinyin;
    /**
     * 关键词列表，各关键词用英文的逗号分隔
     */
    private String keywords;
    /**
     * 自定义排序时间
     */
    private Integer sort;
}
