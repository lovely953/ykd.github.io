package cn.ykd.store.system;

import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
public class RedisTest {
    //提示：当写入的数据包含非ASCII字符时，在终端窗口中查询出来的会是十六进制值
   @Autowired
    RedisTemplate<String , Serializable> redisTemplate;

   @Test
    void set(){
       ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        ops.set("username","wxm");
        log.debug("向Redis中存入数据，成功！");
   }
   @Test
    void get(){
       ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
       String key = "username";
       Serializable value = ops.get(key);
       log.debug("从Redis中读取Value类型（String）的数据，key={},value={}",key,value);
   }

   @Test
    void setObject(){
       BrandStrandardVO brandStrandardVO = new BrandStrandardVO();
       brandStrandardVO.setId(1l);
       brandStrandardVO.setName("一加");
       brandStrandardVO.setEnable(1);

       ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
       ops.set("brand1",brandStrandardVO);
       log.debug("从Redis中读取Value类型（String）的数据，成功");
   }

    @Test
    void getObject(){
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        String key = "brand1";
        Serializable value = ops.get(key);
        log.debug("从Redis中读取Value类型（String）的数据，key={},value={}",key,value);
        if (value != null) {
            log.debug("数据的类型：{}",value.getClass().getName());
            if (value instanceof BrandStrandardVO) {
                BrandStrandardVO brandStrandardVO = (BrandStrandardVO) value;
                log.debug("转换成为数据的真实类型：{}",brandStrandardVO);
            }

        }

    }

    @Test
    void Keys(){
       String pattern = "*";
        Set<String> keys = redisTemplate.keys(pattern);
        log.debug("根据模式={}查询Key，结果:{}",pattern,keys);

    }

    @Test
    void delete(){
       String key = "username";
        Boolean result = redisTemplate.delete(key);
        log.debug("根据Key={}删除Redis中的数据,结果：{}",key,result);
    }

    @Test
    void deleteBatch(){
        Set<String> keys = new HashSet<>();
        keys.add("username");
        keys.add("brand1");
        Long result = redisTemplate.delete(keys);
        log.debug("根据Key={}删除Redis中的数据,结果：{}",keys,result);
    }

    //存数据list
    @Test
    void rightPush(){
       String key= "brands";
        List<BrandStrandardVO> brands = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            BrandStrandardVO brandStrandardVO = new BrandStrandardVO();
            brandStrandardVO.setId(i+0l);
            brandStrandardVO.setName("华为"+i);
            brands.add(brandStrandardVO);
        }

        ListOperations<String, Serializable>  ops = redisTemplate.opsForList();
        for (BrandStrandardVO brand : brands) {
            Long result = ops.rightPush(key, brand);
            log.debug("向Redis中写入list数据，key={}，value:{},结果：{}",key,brands,result);
        }


    }

    @Test
    void size(){
       String key = "brands";
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        Long size = ops.size(key);
        log.debug("在Redis中读取key={}的list长度是{}",key,size);
    }

    //取数据list
    @Test
    void range(){
       String key = "brands";
       long start = 0;
       long end = 8;
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        List<Serializable> list = ops.range(key, start, end);
        for (Serializable item : list) {
            log.debug("从Redis中读取key={}的list，列表项：{}",key,item);
        }

    }

    //set类型存数据
    @Test
    void add(){
       String key = "keys";
       String value = "key-1";
        SetOperations<String, Serializable> ops = redisTemplate.opsForSet();
        Long result = ops.add(key, value);
        log.debug("从Redis中读取set类型（String）的数据，key={},value={}",key,value,result);
    }
    //set类型取数据
    @Test
    void members(){
       String key = "keys";
        SetOperations<String, Serializable> ops = redisTemplate.opsForSet();
        Set<Serializable> members = ops.members(key);
        for (Serializable item : members) {
            log.debug("在Redis中读取key={}的set 当前元素是：{}",key,item);
        }
    }
}
