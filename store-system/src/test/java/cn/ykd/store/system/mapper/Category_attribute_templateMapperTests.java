package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Category_attribute_template;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class Category_attribute_templateMapperTests {

    @Autowired
    private Category_attridute_templateMapper mapper;
    @Test
    void insert(){
        Category_attribute_template c = new Category_attribute_template();
        c.setCategory_id(1l);
        c.setAttribute_template_id(1l);
        int rows = mapper.insert(c);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void insertBatch(){
        List<Category_attribute_template> list = new ArrayList<>();
        for(int i=1;i<5;i++){
            Category_attribute_template c = new Category_attribute_template();
            c.setCategory_id(1l+i);
            c.setAttribute_template_id(1l+i);
            list.add(c);
        }
        int rows = mapper.insertBatch(list);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

}
