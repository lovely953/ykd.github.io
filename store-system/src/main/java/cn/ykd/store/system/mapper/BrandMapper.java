package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand;
import cn.ykd.store.system.pojo.entity.Brand_category;
import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import cn.ykd.store.system.pojo.vo.BrandlistitemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandMapper {
    /**
     * 新增品牌
     * @param brand 品牌参数
     * @return 受影响的行数
     */
    int insert(Brand brand);

    /**
     *
     * @param list
     * @return 受影响的行数
     */
    int insertBatch(List<Brand> list);

    /**
     * 根据id删除属性表数据
     * @param id
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
     * 根据id获取标准品牌类
     * @param id
     * @return
     */
    BrandStrandardVO getBandStrandardById(Long id);
    /**
     * 修改信息
     * @param brand
     * @return 受影响的行数
     */
    int update(Brand brand);

    /**
     * 查询名字是否被占用
     * @param name
     * @return
     */
    int countByName(String name);

    /**
     * 查询类别信息
     * @return
     */
    List<BrandlistitemVO> list();



}
