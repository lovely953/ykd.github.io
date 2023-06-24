package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand_category;
import cn.ykd.store.system.pojo.entity.Category;
import cn.ykd.store.system.pojo.vo.CategoryListitemVO;
import cn.ykd.store.system.pojo.vo.CategoryStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 类别mapper业务接口
 */
@Repository
public interface CategoryMapper {
    /**
     *新增类别
     * @param category
     * @return 受影响的行数
     */
    int insert(Category category);

    /**
     *批量插入
     * @param list
     * @return 受影响的行数
     */
    int insertBatch(List<Category> list);

    /**
     * 批量删除模板
     * @param id 模板数组
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 批量删除模板
     * @param ids 模板数组
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改信息
     * @param category
     * @return 受影响的行数
     */
    int update(Category category);

    /**
     * 统计类别表中的数据的数量
     *
     * @return 类别表中的数据的数量
     */
    int count();

    /**
     * 查看名字是否被占用
     * @param name
     * @return
     */
    int countByName(String name);

    /**
     * 根据父级类别，统计其子类型的数量
     * @param parent_id
     * @return
     */
    int countByParentId(Long parent_id);

    /**
     * 过相册id查询
     * @param id
     * @return 匹配的相册详情，如果没有匹配的数据1，返回值为null
     */
    CategoryStandardVO getCategoryStandardById(Long id);

    /**
     * 查询类别
     * @return 类别列表
     */
    List<CategoryListitemVO> list();

    /**
     * 根据父级id查询子级列表
     * @param parent_id
     * @return
     */
    List<CategoryListitemVO> listByParent_id(Long parent_id);


}
