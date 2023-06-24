package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


/**
 * 属性测试类
 */
@Slf4j
@SpringBootTest
public class AttributeMapperTests {

    @Autowired
    private AttributeMapper mapper;

    @Test
    void insert(){
        Attribute attribute = new Attribute();
        attribute .setTemplate_id(1L);
        attribute.setName("腊肉");
        attribute.setDescription("特色美食");
        attribute.setType(1);
        attribute.setInput_type(0);
        attribute.setValue_list("肉");
        attribute.setUnit("斤");
        attribute.setSort(1);
        attribute.setIs_allow_customize(0);
        int rows = mapper.insert(attribute);
        log.debug("插入数据后受影响的行数为：{}",rows);

    }

    @Test
    void insertBatch(){
        List<Attribute> attributeList  =  new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Attribute attribute = new Attribute();
            attribute .setTemplate_id(1L+i);
            attribute.setName("腊肉0"+i);
            attribute.setDescription("特色美食");
            attribute.setType(1);
            attribute.setInput_type(0);
            attribute.setValue_list("肉");
            attribute.setUnit("斤");
            attribute.setSort(1+i);
            attribute.setIs_allow_customize(0);
            attributeList.add(attribute);
        }
        int rows = mapper.insertBatch(attributeList);
        log.debug("插入数据后，受影响的行数是:{}",rows);
    }

    @Test
    void deleteById(){
        Long id = 1L;
        int rows = mapper.deleteById(id);
        log.debug("删除数据后，受影响的行数是:{}",rows);

    }

    @Test
    void deleteByIds(){
        Long[] ids = {2l,3l,4l};
        int rows = mapper.deleteByIds(ids);
        log.debug("删除数据后，受影响的行数是:{}",rows);

    }
    @Test
    void update(){
        Attribute attribute = new Attribute();
        attribute.setTemplate_id(3l);
        attribute.setId(5l);
        attribute.setName("测试");
        attribute.setDescription("jianjie");
        attribute.setType(1);
        attribute.setInput_type(1);
        attribute.setValue_list("0");
        attribute.setUnit("cs");
        attribute.setSort(2);
        attribute.setIs_allow_customize(1);
        int rows = mapper.update(attribute);
        log.debug("修改数据后受影响的行数为：{}",rows);

    }

    @Test
    void countByName(){
        String name="腊肉01";
        int rows = mapper.countByName(name);
        log.debug("根据【名称】{}查询的结果，受影响的行数是:{}",name,rows);
    }


}
