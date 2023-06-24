package cn.ykd.store.system.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sku数据实体类
 */
@Data
public class Sku_specification implements Serializable {
    /**
     * 记录id
     */
    private Long id;
    /**
     *skuid
     */
    private Long sku_id;
    /**
     *属性id
     */
    private Long attribute_id;
    /**
     *属性名称
     */
    private String attribute_name;
    /**
     *属性值
     */
    private String attribute_value;
    /**
     *自动补充计量单位
     */
    private String unit;
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
