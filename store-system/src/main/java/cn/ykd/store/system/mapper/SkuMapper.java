package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand_category;
import cn.ykd.store.system.pojo.entity.Sku;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * skumapper业务接口
 */
@Repository
public interface SkuMapper {

    /**
     *
     * @param sku
     * @return 受影响的行数
     */
    int insert(Sku sku);

    /**
     * 根据album_id查询sku信息
     * @param album_id
     * @return 受影响的行数
     */
    int countByAlbumId(Long album_id);

    /**
     *
     * @param list
     * @return 受影响的行数
     */
    int insertBatch(List<Sku> list);

    /**
     * 修改信息
     * @param brand_category
     * @return 受影响的行数
     */
    int update(Brand_category brand_category);
}

