package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Attribute;
import cn.ykd.store.system.pojo.entity.Attribute_template;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性模板mapper测试类
 */
@Slf4j
@SpringBootTest
public class Attribute_templateMapperTests {
    @Autowired
    private  Attribute_templateMapper mapper;

    @Test
    void insert(){
        Attribute_template attribute_template = new Attribute_template();
        attribute_template.setName("测试01");
        attribute_template.setPinyin("ceshi01");
        attribute_template.setKeywords("tests01");
        attribute_template.setSort(01);
        int rows= mapper.insert(attribute_template);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void insertBatch(){
        List<Attribute_template> list  =  new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Attribute_template attribute_template = new Attribute_template();
            attribute_template.setName("测试00"+i);
            attribute_template.setPinyin("ceshi0"+i);
            attribute_template.setKeywords("tests0"+i);
            attribute_template.setSort(1+i);
            list.add(attribute_template);
        }
        int rows = mapper.insertBatch(list);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void  deleteById(){
        Long id = 1l;
        int rows = mapper.deleteById(id);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }
    @Test
    void  deleteByIds(){
        Long[] ids = {2l,3l,4l};
        int rows = mapper.deleteByIds(ids);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void  countByName(){
        String name = "苕果子";
        int rows = mapper.countByName(name);
        log.debug("通过名字查询后受影响的行数为：{}",rows);
    }

    @Test
    void update(){
        Attribute_template attribute_template = new Attribute_template();
        attribute_template.setId(5l);
        attribute_template.setName("苕果子");
        attribute_template.setPinyin("shaoguozi");
        attribute_template.setKeywords("sgz");
        attribute_template.setSort(5);
        int rows = mapper.update(attribute_template);
        log.debug("修改数据后受影响的行数为：{}",rows);
    }

    @Test
    void getbrand(){
        Long id = 12l;
        Object res = mapper.getAttribute_template(id);
        log.debug("通过id:{}统计查询数据之后返回的结果:{}",id,res);
    }
}
