package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand_category;
import cn.ykd.store.system.pojo.entity.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 图片mapper业务接口
 */
@Repository
public interface PictureMapper {

    /**
     *
     * @param picture
     * @return 受影响的行数
     */
    int insert(Picture picture);

    /**
     *
     * @param list
     * @return 受影响的行数
     */
    int insertBatch(List<Picture> list);

    /**
     * 批量删除模板
     * @param ids 模板数组
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据album_id查询picture信息
     * @param album_id
     * @return 受影响的行数
     */
    int countByAlbumId(Long album_id);

    /**
     * 修改信息
     * @param picture
     * @return 受影响的行数
     */
    int update(Picture picture);
}
