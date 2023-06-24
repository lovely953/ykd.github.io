package cn.ykd.store.system.service;

import cn.ykd.store.system.pojo.dto.BrandAddNewDTO;
import cn.ykd.store.system.pojo.dto.BrandUopdateDTO;
import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import cn.ykd.store.system.pojo.vo.BrandlistitemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 品牌的业务接口
 */
@Transactional//事务管理：数据库中若干个写操作要么全部成功要么全部失败（声明式事务）
public interface IBrandService {
    /**
     * 新增业务接口
     * @param brandAddNewDTO
     */
    void addnew(BrandAddNewDTO brandAddNewDTO);

    /**
     * 根据id删除品牌
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id修改商品信息
     * @param brandUopdateDTO
     */
    void updateBrand(Long id, BrandUopdateDTO brandUopdateDTO);

    /**
     * 查询类别信息
     * @return
     */
    List<BrandlistitemVO> list();
    /**
     * 启用品牌
     *
     * @param id 尝试启用的品牌的id
     */
    void setEnable(Long id);

    /**
     * 禁用品牌
     *
     * @param id 尝试禁用的品牌的id
     */
    void setDisable(Long id);

    /**
     * 根据id获取品牌详情
     *
     * @param id 品牌id
     * @return 返回与id匹配的品牌数据详情，如果没有匹配的数据，则返回null
     */
    BrandStrandardVO getStandardById(Long id);

    /**
     * 重建缓存
     */
    void rebuildCache();

}
