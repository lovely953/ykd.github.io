package cn.ykd.store.system.service;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.mapper.Attribute_templateMapper;
import cn.ykd.store.system.pojo.dto.Attribute_templateAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.Service;

@Slf4j
@SpringBootTest
public class IAttribute_templateTests {
    @Autowired
    private IAttribute_templateService service;

    @Test
    void addNew(){
        Attribute_templateAddNewDTO attribute_templateAddNewDTO = new Attribute_templateAddNewDTO();
        attribute_templateAddNewDTO.setName("测试521");
        attribute_templateAddNewDTO.setPinyin("ceshi520");
        attribute_templateAddNewDTO.setKeywords("cs520");
        attribute_templateAddNewDTO.setSort(5);
        try {
            service.addNew_Attribute(attribute_templateAddNewDTO);
            log.debug("添加成功");
        } catch (ServiceException e){
            log.warn("添加失败原因：{}",e.getMessage());
        }
    }

    @Test
    void delete(){
        Long id = 12l;
        try {
            service.delete(id);
            log.debug("删除模板属性成功");
        }catch (ServiceException e){
            log.debug(e.getMessage());
        }
    }
}
