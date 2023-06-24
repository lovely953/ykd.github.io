package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Picture;
import cn.ykd.store.system.pojo.entity.Sku_specification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class Sku_specificationMapperTests {
    @Autowired
    private Sku_specificationMapper mapper;

    @Test
    void insert(){
        Sku_specification sku_specification = new Sku_specification();
        sku_specification.setSku_id(1l);
        sku_specification.setAttribute_id(1l);
        sku_specification.setAttribute_name("测试");
        sku_specification.setAttribute_value("测试");
        sku_specification.setUnit("hh");
        sku_specification.setSort(1);
        int rows = mapper.insert(sku_specification);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void insertBatch(){
        List<Sku_specification> list = new ArrayList<>();
        for (int i = 1; i <5 ; i++) {
            Sku_specification sku_specification = new Sku_specification();
            sku_specification.setSku_id(1l);
            sku_specification.setAttribute_id(1l);
            sku_specification.setAttribute_name("测试");
            sku_specification.setAttribute_value("测试");
            sku_specification.setUnit("hh");
            sku_specification.setSort(1);
            list.add(sku_specification);

        }
        int rows= mapper.insertBatch(list);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }
}
