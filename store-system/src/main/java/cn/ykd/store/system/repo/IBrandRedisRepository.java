package cn.ykd.store.system.repo;

import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import cn.ykd.store.system.pojo.vo.BrandlistitemVO;

import java.util.List;

/**
 * 处理品牌缓存的数据接口
 */
public interface IBrandRedisRepository {
    /**
     * 品牌数据在缓存中的key前缀
     */
    public static final String BRAND_ITEM_KEY="brand:item:";
    /**
     * 品牌列表数据在缓存中的key前缀
     */
    public static final String BRAND_LIST_KEY="brand:list";

    /**
     * 品牌数据的所有的缓存数据的key的集合的key
     */
    String BRAND_ALL_KEYS_KEY="brand:all-keys";

    /**
     * 向redis写入品牌数据
     * @param brandStrandardVO
     */
    void save(BrandStrandardVO brandStrandardVO);

    /**
     * 向redis写入品牌列表数据
     * @param brandlistitemVOList
     */
    void save(List<BrandlistitemVO> brandlistitemVOList);

    /**
     * 删除所有
     */
    void deleteAll();

    /**
     * 从redis缓存中获取数据
     * @param id 品牌id
     * @return 匹配的匹配数据,没有返回null
     */
    BrandStrandardVO  get(Long id);

    /**
     * 从redis缓存中获取品牌列表的全部数据
     * @return
     */
    List<BrandlistitemVO> list();
    /**
     * 从redis缓存中获取品牌列表数据
     * @param start  读取数据的启始下标，从0开始
     * @param end 读取数据的截止下标，如果需要读取值最后一条数据请用-1
     * @return
     */
    List<BrandlistitemVO> list(long start , long end);

}
