package cn.ykd.store.system.service;

import cn.ykd.store.system.pojo.dto.Attribute_templateAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 属性模板的业务接口
 */
@Transactional//事务管理：数据库中若干个写操作要么全部成功要么全部失败（声明式事务）
public interface IAttribute_templateService {
    /**
     * 添加属性模板
     * @param attribute_templateAddNewDTO
     */
    void addNew_Attribute(Attribute_templateAddNewDTO attribute_templateAddNewDTO);

    /**
     * 根据id删除模板属性
     * @param id
     */
    void delete(Long id);
}
