package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Album;
import cn.ykd.store.system.pojo.vo.AlbumListitemVO;
import cn.ykd.store.system.pojo.vo.AlbumStrandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理相册的mapper接口
 */
@Repository
public interface AlbumMapper {
    /**
     * 新增相册
     * @param album 相册数据
     * @return 受影响的行数
     */
    int insert(Album album);

    /**
     * 批量加入相册
     * @param albumList
     * @return 受影响的行数
     */
    int insertBatch(List<Album> albumList);

    /**
     * 根据id删除相册数据
     * @param id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     *根据若干个id批量删除相册数据
     * ids 若干个相册id的数组
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改相册信息
     * @param album 相册数据
     * @return 受影响的行数
     */
    int update(Album album);

    /**
     * 统计查询
     * @return 受影响的行数
     */
    int count();

    /**
     * 根据id查询相册
     * @param id
     * @return
     */
    AlbumStrandardVO getStandardById(Long id);

    /**
     * 通过相册id查询
     * @param id 相册id
     * @return 匹配的相册详情，如果没有匹配的数据1，返回值为null
     */
    AlbumStrandardVO getAlbumStrandardById(Long id);



    /**
     * 查询相册列表
     * @return
     */
    List<AlbumListitemVO>  list();

    /**
     * 查询名字是否被占用
     * @param name
     * @return
     */
    int countByName(String name);

    /**
     * 统计非否id但名称匹配的相册数据数量，用于检查是否存在其他数据用例相同名称
     * @param name
     * @return
     */
    int countByNameAndNotId(@Param("id") Long id,  @Param("name") String name);


}


