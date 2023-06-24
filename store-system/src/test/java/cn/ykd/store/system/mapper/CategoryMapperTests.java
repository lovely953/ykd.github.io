package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class CategoryMapperTests {
    @Autowired
    private CategoryMapper mapper;

    @Test
    void insert(){
        Category category = new Category();
        category.setName("测试");
        category.setParent_id(1l);
        category.setDepth(1);
        category.setKeywords("测试");
        category.setSort(1);
        category.setIcon("测试");
        category.setEnable(1);
        category.setIs_parent(1);
        category.setIs_display(0);
        int rows = mapper.insert(category);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void insertBatch(){
        List<Category> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Category category = new Category();
            category.setName("测试00"+i);
            category.setParent_id(1l);
            category.setDepth(1);
            category.setKeywords("测试");
            category.setSort(1);
            category.setIcon("测试");
            category.setEnable(1);
            category.setIs_parent(1);
            category.setIs_display(0);
            list.add(category);
        }
        int rows = mapper.insertBatch(list);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void countByName(){
        String name ="家用电器";
        int rows = mapper.countByName(name);
        log.debug("根据名称{}查询数据后受影响的行数为：{}",name,rows);
    }
    @Test
    void countByParentId(){
        Long id = 66l;
        Object res = mapper.countByParentId(id);
        log.debug("通过id:{}统计查询数据之后返回的结果:{}",id,res);
    }


    @Test
    void countByid(){
        Long id=999L;
        Object res = mapper.getCategoryStandardById(id);
        log.debug("通过id:{}统计查询数据之后返回的结果:{}",id,res);
    }

    @Test
    void list(){
        List<?> list = mapper.list();
        log.debug("查询列表完成，数据的数量为:{}",list.size());
        for (Object item : list) {
            log.debug("{}",item);
        }
    }

    @Test
    void listbyparent_id(){
        Long parent_id = 1l;
        List<?> list = mapper.listByParent_id(parent_id);
        log.debug("查询列表完成，数据的数量为:{}",list.size());
        for (Object item : list) {
            log.debug("{}",item);
        }
    }

}
