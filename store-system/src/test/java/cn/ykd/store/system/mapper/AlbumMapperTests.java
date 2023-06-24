package cn.ykd.store.system.mapper;


import cn.ykd.store.system.pojo.entity.Album;
import cn.ykd.store.system.pojo.vo.AlbumListitemVO;
import cn.ykd.store.system.pojo.vo.AlbumStrandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class AlbumMapperTests {
    @Autowired
    AlbumMapper mapper;
    @Test
    void insert(){
        Album data=new Album();
        data.setName("恋橙测试001");
        data.setDescription("介绍001");
        data.setSort(100);//此值只能为0-255

        log.info("插入数据之前；参数{}",data);
        int rows = mapper.insert(data);
        log.info("插入数据完成——受影响行数为"+rows);
        log.info("插入数据之后；参数{}",data);

    }

    @Test
    void insertBatch(){
        List<Album> albumList = new ArrayList<>();

        for (int i = 1; i <5 ; i++) {
            Album data=new Album();
            data.setName("恋橙测试" + i);
            data.setDescription("介绍" + i);
            data.setSort(100 + i);//此值只能为0-255
            albumList.add(data);
        }

        int rows = mapper.insertBatch(albumList);
        log.info("插入数据完成——受影响行数为"+rows);

    }

    @Test
    void deleteById(){
       Long id = 1L;
        int rows = mapper.deleteById(id);
        log.debug("删除数据之后——受影响行数{}",rows);
    }
    @Test
    void deleteByIds(){
        Long[] ids={2l,3l,4l};
        int rows = mapper.deleteByIds(ids);
        log.debug("删除数据之后——受影响行数:{}",rows);
    }

    @Test
    void update(){
        Album album = new Album();
        album.setId(13l);
        album.setName("李子");
        album.setDescription("味道好极了");
        album.setSort(3);
        int rows = mapper.update(album);
        log.debug("修改数据之后——受影响行数:{}",rows);
    }

    @Test
    void count(){
        int rows = mapper.count();
        log.debug("统计查询数据之后——受影响行数:{}",rows);
    }

    @Test
    void countByName(){
        String name="李子";
        Object res = mapper.countByName(name);
        log.debug("通过名称:{}统计查询数据之后返回的结果:{}",name,res);
    }

    @Test
    void countByid(){
        Long id=10L;
        Object res = mapper.getAlbumStrandardById(id);
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


}
