package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Attribute;
import cn.ykd.store.system.pojo.entity.Brand_category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 品牌种类mapper业务接口
 */
@Repository
public interface Brand_cateporyMapper {

    /**
     *新增品牌种类
     * @param brand_category 品牌种类数据
     * @return 受影响的行数
     */
    int insert(Brand_category brand_category);

    /**
     * 批量新增品牌种类
     * @param list 品牌种类数据数组
     * @return 受影响的行数
     */
    int insertBatch(List<Brand_category> list);

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
     * @param brand_category
     * @return 受影响的行数
     */
    int update(Brand_category brand_category);

    /**
     *根据类别id统计Brand_category数据的数量
     * @param categoryId
     * @return
     */
    int countByCategory(Long categoryId);
}
