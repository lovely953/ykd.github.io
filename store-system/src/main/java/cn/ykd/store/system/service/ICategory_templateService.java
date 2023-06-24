package cn.ykd.store.system.service;

import cn.ykd.store.system.pojo.dto.AttributeAddNewDTO;
import cn.ykd.store.system.pojo.dto.Category_templateAddNewDTO;
import cn.ykd.store.system.pojo.vo.AttributeListVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 接口
 */
@Transactional//事务管理：数据库中若干个写操作要么全部成功要么全部失败（声明式事务）
public interface ICategory_templateService {
    /**
     * 添加
     * @param category_templateAddNewDTO
     */
    void addNew(Category_templateAddNewDTO category_templateAddNewDTO);

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
