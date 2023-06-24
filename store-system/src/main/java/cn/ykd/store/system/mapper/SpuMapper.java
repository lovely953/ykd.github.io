package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand_category;
import cn.ykd.store.system.pojo.entity.Spu;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * spumapper业务接口
 */
@Repository
public interface SpuMapper {
    /**
     *
     * @param spu
     * @return 受影响的行数
     */
    int insert(Spu spu);

    /**
     *
     * @param list
     * @return 受影响的行数
     */
    int insertBatch(List<Spu> list);

    /**
     * 修改信息
     * @param spu
     * @return 受影响的行数
     */
    int update(Spu spu);

    /**
     * 根据类别id统计spu数据的数量
     * @param category_id
     * @return
     */
    int countByCategory(Long category_id);

    /**
     * 根据album_id查询spu信息
     * @param album_id
     * @return 受影响的行数
     */
    int countByAlbumId(Long album_id);
}
