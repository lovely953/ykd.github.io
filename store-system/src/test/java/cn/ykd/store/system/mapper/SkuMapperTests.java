package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Picture;
import cn.ykd.store.system.pojo.entity.Sku;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class SkuMapperTests {
@Autowired
private  SkuMapper mapper;
    @Transactional
    @Test
    void insert(){
//         Sku sku = new Sku();
//        sku.setSpu_id(1l);
//        sku.setTitle("测试");
//        sku.setBar_code("1");
//        sku.setAttribute_template_id(1l);
//        sku.setSpecifications("das");
//        sku.setAlbum_id(1l);
//        sku.setPictures("asdas");
//       // int rows= mapper.insert(sku);
//       // log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void insertBatch(){
        List<Sku> list = new ArrayList<>();
        for (int i = 1; i <5 ; i++) {
            Sku sku = new Sku();
            sku.setSpu_id(1l);
            sku.setTitle("测试"+i);
            list.add(sku);
        }
        int rows= mapper.insertBatch(list);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void countByAlbum_id(){
        Long id = 10l;
        int rows = mapper.countByAlbumId(id);
        log.debug("统计Album_id{}后受影响的行数为：{}",id,rows);
    }

}
