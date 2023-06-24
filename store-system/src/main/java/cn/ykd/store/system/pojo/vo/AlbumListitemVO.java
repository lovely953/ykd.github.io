package cn.ykd.store.system.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 相册数据列表项vo类
 *
 */
@Data
public class AlbumListitemVO implements Serializable {
    /**
     * 相册id
     */
    @ApiModelProperty("相册id")
    private Long id;
    /**
     * 相册名字
     */
    @ApiModelProperty("相册名字")
    private String name;
    /**
     相册简介
     */
    @ApiModelProperty("相册简介")
    private String description;
    /**
     排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
}
