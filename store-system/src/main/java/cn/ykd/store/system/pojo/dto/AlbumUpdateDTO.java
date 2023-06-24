package cn.ykd.store.system.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumUpdateDTO implements Serializable {
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
