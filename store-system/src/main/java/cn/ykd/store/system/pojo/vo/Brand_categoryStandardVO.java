package cn.ykd.store.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 品牌与类别关联实体VO类
 */
@Data
public class Brand_categoryStandardVO implements Serializable {
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

}
