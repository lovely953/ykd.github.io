package cn.ykd.store.system.service;

import cn.ykd.store.system.pojo.dto.AlbumAddNewDTO;
import cn.ykd.store.system.pojo.dto.AlbumUpdateDTO;
import cn.ykd.store.system.pojo.vo.AlbumListitemVO;
import cn.ykd.store.system.pojo.vo.AlbumStrandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理相册相关的业务接口
 */
@Transactional//事务管理：数据库中若干个写操作要么全部成功要么全部失败（声明式事务）
public interface IAlbumService {
    /**
     * 添加相册
     * @param albumAddNewDTO 新的相册数据
     */
    void addNew(AlbumAddNewDTO albumAddNewDTO);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Long id);

    /**
     * 查询相册列表
     * @return 相册列表
     */
    List<AlbumListitemVO>  list();

    /**
     * 修改内容
     * @param id 被修改的id
     * @param albumUpdateDTO
     */
    void  updateInfoById( Long id, AlbumUpdateDTO albumUpdateDTO );


    /**
     * 根据id查询相册
     * @param id
     * @return
     */
    AlbumStrandardVO getStandardById(Long id);


}
