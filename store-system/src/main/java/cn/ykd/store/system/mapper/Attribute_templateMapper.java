package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Attribute_template;
import cn.ykd.store.system.pojo.vo.Attribute_templateStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性模板的mapper接口
 */
@Repository
public interface Attribute_templateMapper {

    /**
     * 新增属性模板
     * @param attribute_template 属性模板实体类
     * @return 受影响行数
     */
    int insert(Attribute_template attribute_template);

    /**
     * 批量插入属性模板
     * @param list 属性模板数组
     * @return 受影响行数
     */
    int insertBatch(List<Attribute_template> list);

    /**
     * 根据id删除属性模板
     * @param id 属性id
     * @return 受影响的行数
     */
    int deleteById(long id);

    /**
     * 批量删除模板
     * @param ids 模板数组
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改模板
     * @param attribute_template 模板数据
     * @return 受影响的行数
     */
    int update(Attribute_template attribute_template);

    /**
     *根据名称查询是否被占用
     * @param name
     * @return
     */
    int countByName(String name);

    /**
     * 获取标准模板属性
     * @param id
     * @return
     */
    Attribute_templateStandardVO getAttribute_template(Long id);
}
