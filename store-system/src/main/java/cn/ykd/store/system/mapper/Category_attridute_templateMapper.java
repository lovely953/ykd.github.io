package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand_category;
import cn.ykd.store.system.pojo.entity.Category_attribute_template;
import cn.ykd.store.system.pojo.vo.CategoryListitemVO;
import cn.ykd.store.system.pojo.vo.CategoryStandardVO;
import cn.ykd.store.system.pojo.vo.Category_attribute_templateListitemVO;
import cn.ykd.store.system.pojo.vo.Category_attribute_templateStamdardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类别与模板mapper业务接口
 */
@Repository
public interface Category_attridute_templateMapper {
    /**
     * 插入类别与属性模板的关联数据
     *
     * @param category_attribute_template 类别与属性模板的关联数据
     * @return 受影响的行数
     */
    int insert(Category_attribute_template category_attribute_template);

    /**
     * 批量插入类别与属性模板的关联数据
     *
     * @param categoryAttributeTemplateList 若干个类别与属性模板的关联数据的集合
     * @return 受影响的行数
     */
    int insertBatch(List<Category_attribute_template> categoryAttributeTemplateList);

    /**
     * 根据id删除类别与属性模板的关联数据
     *
     * @param id 类别与属性模板的关联id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 批量删除类别与属性模板的关联数据
     *
     * @param ids 需要删除的若干个类别与属性模板的关联的id
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 更新类别与属性模板的关联数据
     *
     * @param categoryAttributeTemplate 封装了类别与属性模板的关联的id和需要更新的新数据的对象
     * @return 受影响的行数
     */
    int update(Category_attribute_templateStamdardVO categoryAttributeTemplate);

    /**
     * 统计类别与属性模板的关联数据的数量
     *
     * @return 类别与属性模板的关联数据的数量
     */
    int count();

    /**
     * 根据类别统计关联数据的数量
     *
     * @param categoryId 类别id
     * @return 此类别关联的数据的数量
     */
    /**
     * 根据类别id统计spu数据的数量
     * @param category_id
     * @return
     */
    int countByCategory(Long category_id);

    /**
     * 根据属性模板统计关联数据的数量
     *
     * @param attributeTemplateId 属性模板id
     * @return 此属性模板关联的数据的数量
     */
    int countByAttributeTemplate(Long attributeTemplateId);

    /**
     * 根据id查询类别与属性模板的关联数据详情
     *
     * @param id 类别与属性模板的关联id
     * @return 匹配的类别与属性模板的关联数据详情，如果没有匹配的数据，则返回null
     */
    Category_attribute_templateStamdardVO getStandardById(Long id);

    /**
     * 查询类别与属性模板的关联的数据列表
     *
     * @return 类别与属性模板的关联的数据列表
     */
    List<Category_attribute_templateListitemVO> list();

}
