package cn.ykd.store.system.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;


import java.io.Serializable;

/**
 * 添加相册数据标准dto类
 *
 */
@Data
public class AlbumAddNewDTO implements Serializable {

    /**
     * 相册名字
     */
    @ApiModelProperty(value = "相册名字" ,required = true,example = "腊肉的相册")
    @NotNull(message = "添加相册失败，必须提交相册名称")
    private String name;
    /**
     相册简介
     */
    @ApiModelProperty(value = "相册简介" ,required = true,example = "腊肉的简介")
    @NotNull(message = "添加相册失败，必须提交相册简介")
    private String description;
    /**
     排序
     */
    @ApiModelProperty(value = "相册排序" ,required = true,example = "99")
    @NotNull(message = "添加相册失败，必须提交排序序号")
    private Integer sort;
}
