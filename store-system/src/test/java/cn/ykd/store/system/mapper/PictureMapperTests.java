package cn.ykd.store.system.mapper;

import cn.ykd.store.system.pojo.entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class PictureMapperTests {
@Autowired
private PictureMapper mapper;
    @Test
    void insert(){
        Picture picture = new Picture();
        picture.setAblum_id(1l);
        picture.setUrl("测试");
        picture.setDescription("jieshao");
        picture.setWidth(20);
        picture.setHeight(50);
        picture.setIs_cover(1);
        picture.setSort(1);
        int rows = mapper.insert(picture);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void insertBatch(){
        List<Picture> list = new ArrayList<>();
        for (int i = 1; i <5 ; i++) {
            Picture picture = new Picture();
            picture.setAblum_id(1l);
            picture.setUrl("测试"+i);
            picture.setDescription("jieshao");
            picture.setWidth(20);
            picture.setHeight(50);
            picture.setIs_cover(1);
            picture.setSort(1);
            list.add(picture);
        }
        int rows = mapper.insertBatch(list);
        log.debug("插入数据后受影响的行数为：{}",rows);
    }

    @Test
    void countByAlbum_id(){
        Long id = 10l;
        int rows = mapper.countByAlbumId(id);
        log.debug("统计Album_id{}后受影响的行数为：{}",id,rows);
    }

}
