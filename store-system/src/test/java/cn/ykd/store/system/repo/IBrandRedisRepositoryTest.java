package cn.ykd.store.system.repo;

import cn.ykd.store.system.mapper.BrandMapper;
import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import cn.ykd.store.system.pojo.vo.BrandlistitemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Slf4j
@SpringBootTest
public class IBrandRedisRepositoryTest {

    @Autowired
    private BrandMapper mapper;
    @Autowired
    IBrandRedisRepository repository;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void save(){
        BrandStrandardVO bandStrandardvo = mapper.getBandStrandardById(1l);
        repository.save(bandStrandardvo);
        log.debug("向redis写入完成");
    }

    @Test
    void saveList(){
        List<BrandlistitemVO> list = mapper.list();
        repository.save(list);
        log.debug("向redis写入列表数据，完成");
    }

    @Test
    void get(){
        Long id = 10l;
        BrandStrandardVO brandStrandardVO = repository.get(id);
        log.debug("向redis读取的品牌数据，结果是：{}",brandStrandardVO);
    }

    @Test
    void list(){
        long start = 0l;
        long end = -1l;
        List<BrandlistitemVO> list = repository.list(start, end);
        log.debug("从redis中读取从下标{}至下标{}的品牌数据",start,end);
        for (BrandlistitemVO item : list) {
            log.debug("列表项：{}",item);
        }
    }

    @Test
    void listAll(){
        List<BrandlistitemVO> list = repository.list();
        log.debug("从redis中读取从下标{}至下标{}的品牌数据");
        for (BrandlistitemVO item : list) {
            log.debug("列表项：{}",item);
        }
    }

}
