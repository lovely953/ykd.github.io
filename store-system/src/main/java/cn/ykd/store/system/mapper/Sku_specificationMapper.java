package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand_category;
import cn.ykd.store.system.pojo.entity.Sku_specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * sku数据mapper业务接口
 */
@Repository
public interface Sku_specificationMapper {
    /**
     *
     * @param sku_specification
     * @return 受影响的行数
     */
    int insert(Sku_specification sku_specification);

    /**
     *
     * @param list
     * @return 受影响的行数
     */
    int insertBatch(List<Sku_specification> list);

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
}
