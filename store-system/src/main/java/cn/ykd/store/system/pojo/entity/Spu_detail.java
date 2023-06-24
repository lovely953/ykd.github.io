package cn.ykd.store.system.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * spu详情
 */
@Data
public class Spu_detail implements Serializable {
    /**
     * 记录id
     */
    private Long id;
    /**
     *spuid
     */
    private Long spu_id;
    /**
     *spu详情，应该使用html富文本，通常内容是若干图片
     */
    private String detail;
    /**
     数据的创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     数据最后修改时间
     */
    private LocalDateTime gmtModified;
}
