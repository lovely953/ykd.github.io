package cn.ykd.store.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌DTO类
 */
@Data
public class BrandStrandardVO implements Serializable {

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
     * logo
     */
    private String logo;
    /**
     * 简介
     */
    private String description;
    /**
     * 关键词列表，各关键词用英文的逗号分隔
     */
    private String keywords;
    /**
     * 自定义排序时间
     */
    private Integer sort;

    /**
     * 销量（冗余）
     */
    private Integer sales;

    /**
     * 商品种类数量总和
     */
    private Integer product_count;

    /**
     * 买家评论数量总和
     */
    private Integer comment_count;

    /**
     * 买家好评数量总和
     */
    private Integer positive_comment_count;

    /**
     * 是否启用 1=启用 0=未启用
     */
    private Integer enable;

}
