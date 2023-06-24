package cn.ykd.store.system.pojo.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加属性模板数据标准dto类
 */
@Data
public class Attribute_templateAddNewDTO  implements Serializable {



    /**
     * 属性模板你名称
     */
    @NotNull(message = "添加属性模板失败，必须属性模板名称")
    private String name;
    /**
     * 属性模板名称拼音
     */
    private String pinyin;
    /**
     * 关键词列表，各关键词用英文的逗号分隔
     */
    private String keywords;
    /**
     * 自定义排序时间
     */
    private Integer sort;
}
