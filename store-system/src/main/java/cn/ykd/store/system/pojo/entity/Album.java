package cn.ykd.store.system.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 相册数据实体类
 *
 */
@Data
public class Album implements Serializable {
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
    /**
     数据的创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     数据最后修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * Lombok框架可以通过特定的注解，在编译期生成 Setter、Getter、equals()、hashCode()、toString()、
     * 无参构造方法,全参构造方法等。
     */

}
