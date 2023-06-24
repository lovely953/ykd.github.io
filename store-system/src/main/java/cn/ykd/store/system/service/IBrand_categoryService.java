package cn.ykd.store.system.service;

import cn.ykd.store.system.pojo.dto.Brand_categoryAddNewDTO;
import cn.ykd.store.system.pojo.dto.CategoryAddNewDTO;
import cn.ykd.store.system.pojo.vo.CategoryListitemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional//事务管理：数据库中若干个写操作要么全部成功要么全部失败（声明式事务）
public interface IBrand_categoryService {
    /**
     * 添加
     * @param brand_categoryAddNewDTO 类别参数
     */
    void addNew(Brand_categoryAddNewDTO brand_categoryAddNewDTO);

    /**
     * 根据id删除类别
     * @param id
     */
    void delete(Long id);

    /**
     * 查询类别列表
     * @return
     */
    List<CategoryListitemVO> list();


}
