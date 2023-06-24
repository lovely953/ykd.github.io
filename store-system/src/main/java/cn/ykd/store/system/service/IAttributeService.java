package cn.ykd.store.system.service;

import cn.ykd.store.system.pojo.dto.AttributeAddNewDTO;
import cn.ykd.store.system.pojo.vo.AlbumListitemVO;
import cn.ykd.store.system.pojo.vo.AttributeListVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理属性的业务接口
 */
@Transactional//事务管理：数据库中若干个写操作要么全部成功要么全部失败（声明式事务）
public interface IAttributeService {
    /**
     * 添加属性
     * @param attributeNewDTO
     */
    void addNew(AttributeAddNewDTO attributeNewDTO);

    /**
     * 根据id删除
     * @param id
     */
    void delete(long id);

    /**
     * 查询数据
     * @return
     */
    List<AttributeListVO> list();

}
