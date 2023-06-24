package cn.ykd.store.system.service;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.pojo.dto.BrandAddNewDTO;
import cn.ykd.store.system.pojo.dto.BrandUopdateDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class IBrandServiceImplTsets {
    @Autowired
    private IBrandService service;

    @Test
    void addnew(){
        BrandAddNewDTO brandAddNewDTO = new BrandAddNewDTO();
        brandAddNewDTO.setName("小米");
        brandAddNewDTO.setPinyin("xinming");
        brandAddNewDTO.setLogo("111");
        brandAddNewDTO.setDescription("aaa");
        try {
            service.addnew(brandAddNewDTO);
            log.debug("属性品牌成功");
        }catch (ServiceException e){
            log.debug("属性添加失败，原因是:{}",e.getMessage());
        }
    }
    @Test
    void deleteById(){
        Long id=20l;
        try {
            service.deleteById(id);
            log.debug("根据id删除品牌成功");
        }catch (ServiceException e){
            log.debug("属性添加失败，原因是:{}",e.getMessage());
        }
    }

    @Test
    void updataBrand(){
        Long id = 5l;
        BrandUopdateDTO brandUopdateDTO = new BrandUopdateDTO();
        brandUopdateDTO.setName("一加");
        brandUopdateDTO.setPinyin("honor");
        brandUopdateDTO.setLogo("111");
        brandUopdateDTO.setDescription("aaa");
        try {
            service.updateBrand(id,brandUopdateDTO);
            log.debug("修改品牌成功");
        }catch (ServiceException e){
            log.debug("品牌修改失败，原因是:{}",e.getMessage());
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
