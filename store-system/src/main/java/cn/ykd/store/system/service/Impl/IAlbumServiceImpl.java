package cn.ykd.store.system.service.Impl;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.mapper.AlbumMapper;
import cn.ykd.store.system.mapper.PictureMapper;
import cn.ykd.store.system.mapper.SkuMapper;
import cn.ykd.store.system.mapper.SpuMapper;
import cn.ykd.store.system.pojo.dto.AlbumUpdateDTO;
import cn.ykd.store.system.pojo.entity.Album;
import cn.ykd.store.system.pojo.dto.AlbumAddNewDTO;
import cn.ykd.store.system.pojo.vo.AlbumListitemVO;
import cn.ykd.store.system.pojo.vo.AlbumStrandardVO;
import cn.ykd.store.system.service.IAlbumService;
import cn.ykd.store.system.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理相册数据的业务接口
 */
@Slf4j
@Service
public class IAlbumServiceImpl implements IAlbumService {
    //创建AlbumMapper
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SkuMapper skuMapper;

    @Override
    public void addNew(AlbumAddNewDTO albumAddNewDTO) {
        log.debug("开始处理【添加相册】的业务，参数:{}",albumAddNewDTO);
        //检查相册名字是否已经被占用
        String albumName = albumAddNewDTO.getName();
        // 检查相册名称是否已经被占用（相册表中是否已经存在此名称的数据）
        log.debug("检查相册名称是否已经被占用");
        int count = albumMapper.countByName(albumName);
        if (count > 0) {
            //如果被占用，抛出异常
            String message="添加相册失败，相册名称已被占用";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        log.debug("相册名称没有被占用，将向相册表中插入数据");
        //1.创建ablum对象
        Album album = new Album();
        //2.补全album对象中的属性值，来自参数AlbumAddNewDTO

        // album.setName(albumAddNewDTO.getName());
        //album.setDescription(albumAddNewDTO.getDescription());
        //album.setSort(albumAddNewDTO.getSort());

        BeanUtils.copyProperties(albumAddNewDTO,album);
        //3.执行插入数据：albumMapper。insert（album）
        log.debug("即将插入数据，参数是:{}",album);
        int rows = albumMapper.insert(album);
        if (rows != 1) {
            String message = "添加相册失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【删除相册】的业务，参数:{}", id);
        {
        //调用mapper对象的getAlbumStrandardById执行查询
        AlbumStrandardVO queryResult = albumMapper.getAlbumStrandardById(id);
        log.debug("根据id{}查询到的结果:{}", id, queryResult);
        //判断是否为null
           if (queryResult == null) {
               //是：无此id对应数据，抛出异常
              String message = "删除失败,尝试访问的id数据不存在！";
              log.warn(message);
              throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
          }
        }

        {
            //检查是否存在（picture）图片关联到此相册。如果存在，则不允许删除
            int count = pictureMapper.countByAlbumId(id);
            log.debug("{}", count);
            if (count > 0) {
                //此id关联picture表不允许删除
                String message = "删除失败,此相册存在关联picture数据";
                log.warn("删除失败,此相册存在关联picture数据");
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        {
            int count1 = skuMapper.countByAlbumId(id);
            if (count1 > 0) {
                String message = "删除失败,此相册存在关联sku数据";
                log.warn("删除失败,此相册存在关联sku数据");
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        {
            int count2 = spuMapper.countByAlbumId(id);
            if (count2 > 0) {
                String message = "删除失败,此相册存在关联spu数据";
                log.warn("删除失败,此相册存在关联spu数据");
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }
        //有调用mapper的deleteById删除数据
        int rows = albumMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除相册失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public List<AlbumListitemVO> list() {
        //调用mapper里面的list方法来获得查询结果
        log.debug("开始处理【查询相册】的业务，无参数");
        return albumMapper.list();
    }

    @Override
    public AlbumStrandardVO getStandardById(Long id) {
        log.debug("开始处理【根据id查询相册】的业务，无参数");
        AlbumStrandardVO album = albumMapper.getAlbumStrandardById(id);
        if (album == null) {
            String message = "根据id查询失败,没有此数据";
            log.warn("根据id查询失败，没有此数据");
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        return album;
    }

    @Override
    public void updateInfoById(Long id, AlbumUpdateDTO albumUpdateDTO) {
        log.debug("开始处理【通过id修改相册】的业务，参数id:{},新数据：{}",id,albumUpdateDTO);

        AlbumStrandardVO res = albumMapper.getAlbumStrandardById(id);

        if (res == null) {
            //是：无此id对应数据，抛出异常
            String message = "修改失败,尝试访问的id数据不存在！";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        //调用albumMapper.countByNameAndNotId()执行查询，检查新的名称是是否已经被别的占用
        int count = albumMapper.countByNameAndNotId(id,albumUpdateDTO.getName());
        if (count > 0) {
            String message = "修改失败,新的相册名称已被占用";
            log.warn("修改失败,新的相册名称已被占用");
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        //创建album对象
        Album album = new Album();
        //将参数对象赋值给album
        BeanUtils.copyProperties(albumUpdateDTO,album);
        album.setId(id);
        //调用mapper里面的update方法
        log.debug("即将修改数据，参数是:{}",album);
        albumMapper.update(album);
    }


}
