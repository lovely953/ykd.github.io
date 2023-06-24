package cn.ykd.store.system.service;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.pojo.dto.AlbumAddNewDTO;
import cn.ykd.store.system.pojo.dto.AlbumUpdateDTO;
import cn.ykd.store.system.pojo.dto.CategoryAddNewDTO;
import cn.ykd.store.system.pojo.dto.CategoryUpdateDTO;
import cn.ykd.store.system.pojo.entity.Category;
import cn.ykd.store.system.pojo.vo.AlbumStrandardVO;
import cn.ykd.store.system.pojo.vo.CategoryStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class ICategoryServiceImplTests {
    @Autowired
    private ICategoryService service;

    @Test
    void addNew(){
        CategoryAddNewDTO categoryAddNewDTO = new CategoryAddNewDTO();
        categoryAddNewDTO.setName("高低床");
        categoryAddNewDTO.setParent_id(73L);
        try {
            service.addNew(categoryAddNewDTO);
            log.debug("添加类别成功");
        }catch (ServiceException e){
            log.debug("捕获到异常，其中的消息是:{}",e.getMessage());
        }
    }

    @Test
    void delete(){
        Long id = 273l;
        try {
            service.delete(id);
            log.debug("类别相册成功");
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

    @Test
    void setEnable(){
        Long id = 71l;
        try {
            service.setEnable(id);
            log.debug("启用类别成功");
        }catch (ServiceException e){
            log.debug(e.getMessage());
        }
    }
    @Test
    void setDisable(){
        Long id = 71l;
        try {
            service.setDisable(id);
            log.debug("禁用类别成功");
        }catch (ServiceException e){
            log.debug(e.getMessage());
        }
    }

    @Test
    void enIs_display(){
        Long id = 71l;
        try {
            service.enIs_display(id);
            log.debug("禁用类别成功");
        }catch (ServiceException e){
            log.debug(e.getMessage());
        }
    }
    @Test
    void disIs_display(){
        Long id = 71l;
        try {
            service.disIs_display(id);
            log.debug("禁用导航栏成功");
        }catch (ServiceException e){
            log.debug(e.getMessage());
        }
    }

    @Test
    void listbyparent_id(){
        Long parent_id = 1l;
        List<?> list = service.listByParent_id(parent_id);
        log.debug("查询列表完成，数据的数量为:{}",list.size());
        for (Object item : list) {
            log.debug("{}",item);
        }
    }

    @Test
    void getStandardById(){
        Long id = 5l;
        try {
            CategoryStandardVO category = service.getStandardById(id);
            log.debug("根据id查询类别成功,结果{}",category);
        }catch (ServiceException e){
            log.debug("捕获到异常，其中的消息是:{}",e.getMessage());
        }
    }

    @Test
    void updataInfoById(){
        CategoryUpdateDTO category =new CategoryUpdateDTO();
        category.setId(72l);
        category.setName("李子1");
        category.setKeywords("味道好极了");
        category.setSort(3);
        try {
            service.updateInfoById(category.getId(),category);
            log.debug("修改相册成功");
        }catch (ServiceException e){
            log.debug("捕获到异常，其中的消息是:{}",e.getMessage());
        }
    }
}
