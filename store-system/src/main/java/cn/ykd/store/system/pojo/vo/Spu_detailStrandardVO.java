package cn.ykd.store.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * spu详情
 */
@Data
public class Spu_detailStrandardVO implements Serializable {
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

}
