package cn.ykd.store.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类别与属性模板关联实体vo类
 */
@Data
public class Category_attribute_templateStamdardVO implements Serializable {
    /**
     * 记录id
     */
    private Long id;
    /**
     * 类别id
     */
    private Long category_id;
    /**
     * 属性模板id
     */
    private Long attribute_template_id;

}
