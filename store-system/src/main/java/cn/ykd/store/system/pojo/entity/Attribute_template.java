package cn.ykd.store.system.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 属性模板实体类
 */
@Data
public class Attribute_template implements Serializable {

    /**
     * 记录id
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
    /**
     数据的创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     数据最后修改时间
     */
    private LocalDateTime gmtModified;
}
