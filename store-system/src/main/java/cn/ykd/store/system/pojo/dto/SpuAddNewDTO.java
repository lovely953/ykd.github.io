package cn.ykd.store.system.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 标准化产品单元实体类
 */
@Data
public class SpuAddNewDTO implements Serializable {

    /**
     *spu名称
     */
    private String name;
    /**
     *spu编号
     */
    private String type_number;
    /**
     *标题
     */
    private String title;
    /**
     *简介
     */
    private String description;
    /**
     *价格
     */
    private BigDecimal list_price;
    /**
     *当前库存
     */
    private Integer stock;
    /**
     *库存警戒阈值
     */
    private Integer stock_threshold;
    /**
     *计件单位
     */
    private String unit;
    /**
     *产品id
     */
    private Long brand_id;
    /**
     *产品名称
     */
    private String brand_name;
    /**
     *类别id
     */
    private Long category_id;
    /**
     *类别名称
     */
    private String category_name;
    /**
     *模板属性
     */
    private Long attribute_template_id;
    /**
     *相册id
     */
    private Long album_id;
    /**
     *组图urls，使用json是数组表示
     */
    private String pictures;
    /**
     *关键字列表，个关键字用英文逗号分隔
     */
    private String keywords;
    /**
     *标签列表，各4标签使用英文的逗号分隔
     */
    private String tags;
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
     *自定义排序
     */
    private Integer sort;
    /**
     *是否标记为删除 1=已删除 0=未删除
     */
    private Integer is_deleted;
    /**
     *是否上架 1=上架 0=未上架
     */
    private Integer is_published;
    /**
     *是否为新品 1=是新品 0=不是新品
     */
    private Integer is_new_arrival;
    /**
     *是否推荐 1=推荐 0=不推荐
     */
    private Integer is_recommend;
    /**
     *是否审核 1=已审核 0=未审核
     */
    private Integer is_checked;
    /**
     *审核人
     */
    private String check_user;
    /**
     * 审核时间
     */
    private LocalDateTime gmt_check;


}
