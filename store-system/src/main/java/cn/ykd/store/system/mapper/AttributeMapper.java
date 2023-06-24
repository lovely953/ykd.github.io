package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Attribute;
import cn.ykd.store.system.pojo.entity.Attribute_template;
import cn.ykd.store.system.pojo.vo.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 处理属性的mapper接口
 */
@Repository
public interface AttributeMapper {
    /**
     * 新增属性
     * @param attribute 属性数据
     * @return 受影响的行数
     */
    int insert(Attribute attribute);

    /**
     * 批量新增属性
     * @param attributeList 属性数组
     * @return 受影响的行数
     */
    int insertBatch(List<Attribute> attributeList);

    /**
     * 根据id删除属性表数据
     * @param id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 批量删除模板
     * @param ids 模板数组
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改信息
     * @param attribute
     * @return 受影响的行数
     */
    int update(Attribute attribute);

    /**
     * 根据名称查询
     * @param name 属性名称
     * @return 受影响的行数
     */
    int countByName(String name);

    /**
     * 查询模板属性列表
     * @return
     */
    List<AttributeListVO>  list();

    /**
     * 过相册id查询
     * @param id
     * @return 匹配的相册详情，如果没有匹配的数据1，返回值为null
     */
    AttributeStrandardVO getAttributeStrandardById(Long id);
}
