package cn.ykd.store.system.service;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.pojo.dto.AlbumAddNewDTO;
import cn.ykd.store.system.pojo.dto.AlbumUpdateDTO;
import cn.ykd.store.system.pojo.entity.Album;
import cn.ykd.store.system.pojo.vo.AlbumStrandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 相册service的测试类
 */
@Slf4j
@SpringBootTest
public class IAlbumServiceTests {
    @Autowired
    IAlbumService service;//不建议声名为实现类
    @Test
    void addNew(){
        AlbumAddNewDTO album = new AlbumAddNewDTO();
        album.setName("大洋芋果果");
        album.setDescription("味道好极了");
        album.setSort(4);
        try {
           service.addNew(album);
            log.debug("添加相册成功");
        }catch (ServiceException e){
            log.debug("捕获到异常，其中的消息是:{}",e.getMessage());
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

    @Test
    void updataInfoById(){
        Long id = 1l;
        AlbumUpdateDTO album = new AlbumUpdateDTO();
        album.setName("李子4");
        album.setDescription("味道好极了");
        album.setSort(3);
        try {
            service.updateInfoById(id,album);
            log.debug("修改相册成功");
        }catch (ServiceException e){
            log.debug("捕获到异常，其中的消息是:{}",e.getMessage());
        }
    }

    @Test
    void getStandardById(){
        Long id = 5l;
        try {
            AlbumStrandardVO album = service.getStandardById(id);
            log.debug("根据id查询相册成功,结果{}",album);
        }catch (ServiceException e){
            log.debug("捕获到异常，其中的消息是:{}",e.getMessage());
        }
    }

    @Test
    void setEnable(){
        Long id = 257l;
        try {
            service.delete(id);
            log.debug("启用类别成功");
        }catch (ServiceException e){
            log.debug(e.getMessage());
        }
    }
    @Test
    void setDisable(){
        Long id = 257l;
        try {
            service.delete(id);
            log.debug("禁用类别成功");
        }catch (ServiceException e){
            log.debug(e.getMessage());
        }
    }

}
