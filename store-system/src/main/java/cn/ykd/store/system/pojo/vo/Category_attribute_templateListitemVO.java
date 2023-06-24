package cn.ykd.store.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类别与属性模板关联实体列表vo类
 */
@Data
public class Category_attribute_templateListitemVO implements Serializable {
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
    /**
     数据的创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     数据最后修改时间
     */
    private LocalDateTime gmtModified;
}
