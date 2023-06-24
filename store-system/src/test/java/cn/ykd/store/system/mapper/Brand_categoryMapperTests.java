package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand_category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class Brand_categoryMapperTests {
    @Autowired
    private Brand_cateporyMapper mapper;

    @Test
    void insert(){
        Brand_category brand_category = new Brand_category();
        brand_category.setBrand_id(1l);
        brand_category.setCategory_id(1l);
        int rows = mapper.insert(brand_category);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void insertBatch(){
        List<Brand_category> list =new ArrayList<>();
        for (int i = 1; i <5 ; i++) {
            Brand_category brand_category = new Brand_category();
            brand_category.setBrand_id(1l+i);
            brand_category.setCategory_id(1l+i);
            list.add(brand_category);
        }
        int rows = mapper.insertBatch(list);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }
    
    @Test
    void countByCatogory(){
        Long id = 1l;
        int rows = mapper.countByCategory(id);
        log.debug("通过id:{}统计查询数据之后返回的结果:{}",id,rows);
    }

}
