package cn.ykd.store.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片实体列表类
 */
@Data
public class PictureListitemVO implements Serializable {
    /**
     * 记录id
     */
    private Long id;
    /**
     * 相册id
     */
    private Long ablum_id;
    /**
     * 图片路径
     */
    private String url;
    /**
     * 简介
     */
    private String description;
    /**
     * 图片宽度，单位：px
     */
    private Integer width;
    /**
     * 图片高度，单位：px
     */
    private Integer height;
    /**
     * 是否为封面图片，1=是，0=否
     */
    private Integer is_cover;
    /**
     * 自定义排序
     */
    private Integer sort;

}
