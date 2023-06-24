package cn.ykd.store.system.service;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.pojo.dto.AttributeAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class IAttributeServiceTests {
    @Autowired
    private IAttributeService service;

    @Test
    void addew(){
        AttributeAddNewDTO attributeNewDTO = new AttributeAddNewDTO();
        attributeNewDTO .setTemplate_id(1L);
        attributeNewDTO.setName("李子");
        attributeNewDTO.setDescription("特色美食");
        attributeNewDTO.setType(1);
        attributeNewDTO.setInput_type(0);
        attributeNewDTO.setValue_list("水果");
        attributeNewDTO.setUnit("斤");
        attributeNewDTO.setSort(1);
        attributeNewDTO.setIs_allow_customize(0);
        try {
            service.addNew(attributeNewDTO);
            log.debug("属性添加成功");
        }catch (ServiceException e){
            log.debug("属性添加失败，原因是:{}",e.getMessage());
        }
    }

    @Test
    void delete(){
        Long id = 10l;
        try {
            service.delete(id);
            log.debug("删除相册成功");
        }catch (ServiceException e){
            log.debug(e.getMessage());
        }
    }

    @Test
    void list() {
        List<?> list = service.list();
        log.debug("查询列表完成，数据的数量为:{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }
}
