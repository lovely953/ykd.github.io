package cn.ykd.store.system.service;

import cn.ykd.store.system.pojo.dto.AlbumUpdateDTO;
import cn.ykd.store.system.pojo.dto.CategoryAddNewDTO;
import cn.ykd.store.system.pojo.dto.CategoryUpdateDTO;
import cn.ykd.store.system.pojo.vo.AlbumStrandardVO;
import cn.ykd.store.system.pojo.vo.CategoryListitemVO;
import cn.ykd.store.system.pojo.vo.CategoryStandardVO;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * 类别业务接口
 */
@Transactional//事务管理：数据库中若干个写操作要么全部成功要么全部失败（声明式事务）
public interface ICategoryService {
    /**
     * 添加类别
     * @param categoryAddNewDTO 类别参数
     */
    void addNew(CategoryAddNewDTO categoryAddNewDTO);

    /**
     * 根据id删除类别
     * @param id
     */
    void delete(Long id);

    /**
     * 修改
     * @param categoryUpdateDTO
     */
    void  updateInfoById( Long id, CategoryUpdateDTO categoryUpdateDTO );

    /**
     * 查询类别列表
     * @return
     */
    List<CategoryListitemVO >  list();

    /**
     * 启用
     * @param id
     */
    void  setEnable(Long id);

    /**
     * 禁用
     * @param id
     */
    void  setDisable(Long id);

    /**
     * 展示在导航栏
     * @param id
     */
    void enIs_display(Long id);

    /**
     * 展示在导航栏
     * @param id
     */
    void disIs_display(Long id);

    /**
     * 根据父级id查询子级列表
     * @param parent_id
     * @return
     */
    List<CategoryListitemVO> listByParent_id(Long parent_id);

    /**
     * 根据id查询相册
     * @param id
     * @return
     */
    CategoryStandardVO getStandardById(Long id);




}
