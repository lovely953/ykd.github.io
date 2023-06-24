package cn.ykd.store.system.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌与类别关联实体DTO类
 */
@Data
public class Brand_categoryAddNewDTO implements Serializable {

    /**
     * 品牌id
     */
    private Long brand_id;
    /**
     * 类别id
     */
    private Long category_id;

}
