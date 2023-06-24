package cn.ykd.store.system.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 品牌与类别关联实体类
 */
@Data
public class Brand_category implements Serializable {
    /**
     * 记录id
     */
    private Long id;
    /**
     * 品牌id
     */
    private Long brand_id;
    /**
     * 类别id
     */
    private Long category_id;
    /**
     数据的创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     数据最后修改时间
     */
    private LocalDateTime gmtModified;
}
