package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Brand;
import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class BrandMapperTests {
@Autowired
private BrandMapper mapper;
    @Test
    void insert(){
        Brand brand = new Brand();
        brand.setName("大东");
        brand.setPinyin("dadong");
        brand.setKeywords("bigdong");
        brand.setLogo("logo");
        brand.setDescription("简介");
        brand.setSort(1);
        brand.setSales(1);
        brand.setProduct_count(22);
        brand.setComment_count(25);
        brand.setPositive_comment_count(14);
        brand.setEnable(1);
        int rows = mapper.insert(brand);
        log.debug("插入数据后受影响的行数为：{}",rows);

    }

    @Test
    void insertBatch(){
        List<Brand> list = new ArrayList<>();
        for (int i = 1; i <5; i++) {
            Brand brand = new Brand();
            brand.setName("大东"+i);
            brand.setPinyin("dadong"+i);
            brand.setKeywords("bigdong"+i);
            brand.setLogo("logo"+i);
            brand.setDescription("简介"+i);
            brand.setSort(1+i);
            brand.setSales(1+i);
            brand.setProduct_count(22+i);
            brand.setComment_count(25+i);
            brand.setPositive_comment_count(14+i);
            brand.setEnable(1+i);
            list.add(brand);
        }
        int rows = mapper.insertBatch(list);
        log.debug("插入数据后受影响的行数为：{}",rows);

    }

    @Test
    void countByName(){
        String name ="小米";
        int rows = mapper.countByName(name);
        log.debug("根据名称{}查询数据后受影响的行数为：{}",name,rows);
    }

    @Test
    void getbrand(){
        Long id = 999l;
        Object res = mapper.getBandStrandardById(id);
        log.debug("通过id:{}统计查询数据之后返回的结果:{}",id,res);
    }

    @Test
    void update(){
        Long id = 999l;
        Object res = mapper.getBandStrandardById(id);
        log.debug("通过id:{}统计查询数据之后返回的结果:{}",id,res);
    }
}
