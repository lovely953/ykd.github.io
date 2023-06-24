package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand_category;
import cn.ykd.store.system.pojo.entity.Spu_detail;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * spu详情mapper业务接口
 */
@Repository
public interface Spu_detailMapper {

    /**
     *
     * @param spu_detail
     * @return 受影响的行数
     */
    int insert(Spu_detail spu_detail);

    /**
     *
     * @param list
     * @return 受影响的行数
     */
    int insertBatch(List<Spu_detail> list);

    /**
     * 修改信息
     * @param brand_category
     * @return 受影响的行数
     */
    int update(Brand_category brand_category);
}
