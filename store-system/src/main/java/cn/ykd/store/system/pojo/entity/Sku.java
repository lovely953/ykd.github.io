package cn.ykd.store.system.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 库存管理股实体类
 */
@Data
public class Sku implements Serializable {
    /**
     *记录id
     */
    private Long id;
    /**
     *spuid
     */
    private Long spu_id;

    /**
     *标题
     */
    private String title;
    /**
     * 条形码
     */
    private String bar_code;
    /**
     *模板属性
     */
    private Long attribute_template_id;
    /**
     * 全部属性 使用json数组表示
     */
    private String specifications;
    /**
     *相册id
     */
    private Long album_id;
    /**
     *组图urls，使用json是数组表示
     */
    private String pictures;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 当前库存
     */
    private Integer stock;
    /**
     * 库存预警阈值
     */
    private Integer stock_threshold;
    /**
     *销量
     */
    private Integer sales;
    /**
     *买家评论总和
     */
    private Integer comment_count;
    /**
     *买家好评总和
     */
    private Integer positive_comment_count;
    /**
     * 自定义排序
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
