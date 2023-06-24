package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Picture;
import cn.ykd.store.system.pojo.entity.Spu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class SpuMapperTests {
@Autowired
private SpuMapper mapper;
    @Transactional
    @Test
    void insert(){
        Spu spu = new Spu();
        spu.setBrand_name("测试");
        //int rows= mapper.insert(spu);
       // log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void insertBatch(){
        List<Spu> list = new ArrayList<>();
        for (int i = 1; i <5 ; i++) {

        }
    }

    @Test
    void countByAlbum_id(){
        Long id = 10l;
        int rows = mapper.countByAlbumId(id);
        log.debug("统计Album_id{}后受影响的行数为：{}",id,rows);
    }

}
