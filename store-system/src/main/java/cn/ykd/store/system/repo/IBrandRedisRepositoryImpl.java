package cn.ykd.store.system.repo;

import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import cn.ykd.store.system.pojo.vo.BrandlistitemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class IBrandRedisRepositoryImpl implements IBrandRedisRepository{

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;


    @Override
    public void save(BrandStrandardVO brandStrandardVO) {
        String key = BRAND_ITEM_KEY+brandStrandardVO.getId();
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key,brandStrandardVO);

        SetOperations<String, Serializable> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(BRAND_ALL_KEYS_KEY,key);
    }

    @Override
    public void save(List<BrandlistitemVO> brandlistitemVOList) {
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        for (BrandlistitemVO item : brandlistitemVOList) {
            ops.rightPush(BRAND_LIST_KEY,item);
        }
        SetOperations<String, Serializable> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(BRAND_ALL_KEYS_KEY,BRAND_LIST_KEY);

    }

    @Override
    public void deleteAll() {
        SetOperations<String, Serializable> opsForSet = redisTemplate.opsForSet();
        Set<Serializable> serializables = opsForSet.members(BRAND_ALL_KEYS_KEY);
        Set<String> keys = new HashSet<>();
        for (Serializable key : serializables) {
            keys.add((String) key);
        }
        redisTemplate.delete(keys);
        redisTemplate.delete(BRAND_ALL_KEYS_KEY);
    }

    @Override
    public BrandStrandardVO get(Long id) {
        String key = BRAND_ITEM_KEY+id;
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        Serializable serializable = ops.get(key);
        if (serializable != null) {
            return (BrandStrandardVO) serializable;
        }
        return null;

    }

    @Override
    public List<BrandlistitemVO> list() {
        return list(0,-1);
    }

    @Override
    public List<BrandlistitemVO> list(long start, long end) {
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        List<Serializable> list = ops.range(BRAND_LIST_KEY, start, end);
        List<BrandlistitemVO> brandlistitemVOList = new ArrayList<>();
        for (Serializable serializable : list) {
            brandlistitemVOList.add((BrandlistitemVO) serializable);
        }
        return brandlistitemVOList;
    }
}
