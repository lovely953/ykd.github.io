package cn.ykd.store.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 相册数据标准vo类
 *
 */
@Data
public class AlbumStrandardVO implements Serializable {
    /**
     * 相册id
     */
    private Long id;
    /**
     * 相册名字
     */
    private String name;
    /**
     相册简介
     */
    private String description;
    /**
     排序
     */
    private Integer sort;
}
