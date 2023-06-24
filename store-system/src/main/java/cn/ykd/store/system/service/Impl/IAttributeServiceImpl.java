package cn.ykd.store.system.service.Impl;


import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.mapper.AttributeMapper;
import cn.ykd.store.system.pojo.entity.Attribute;
import cn.ykd.store.system.pojo.dto.AttributeAddNewDTO;
import cn.ykd.store.system.pojo.vo.AlbumListitemVO;
import cn.ykd.store.system.pojo.vo.AttributeListVO;
import cn.ykd.store.system.pojo.vo.AttributeStrandardVO;
import cn.ykd.store.system.service.IAttributeService;
import cn.ykd.store.system.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 属性的业务接口实现类
 */
@Service
@Slf4j

public class IAttributeServiceImpl implements IAttributeService {

    @Autowired
    private AttributeMapper mapper;

    @Override
    public void addNew(AttributeAddNewDTO attributeNewDTO) {
        log.debug("开始处理【添加相册】的业务，参数:{}",attributeNewDTO);
        //1.查询属性名称是否被占用
        String name = attributeNewDTO.getName();
        int countByName = mapper.countByName(name);
        if (countByName > 0) {
            //名称已被占用，抛出异常
            String message = "添加属性异常，名称已被占用";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        log.debug("属性名称没有被占用，将向属性表中插入数据");
        //2.创建attribute对象，补全对象参数
        Attribute attribute = new Attribute();
        BeanUtils.copyProperties(attributeNewDTO,attribute);
        //3.执行attributeMapper.insert(attribute);
        log.debug("即将插入数据，参数是:{}",attribute);
        mapper.insert(attribute);
    }

    @Override
    public void delete(long id) {
        log.debug("开始处理【根据id删除类别】的业务，参数{}",id);
        AttributeStrandardVO count = mapper.getAttributeStrandardById(id);
        if (count == null) {
            //抛出异常
            String message = "删除失败，尝试查询的id不存在";
            log.warn("删除失败，尝试查询的id不存在");
            throw  new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        log.debug("即将删除，id：{}",id);
        mapper.deleteById(id);
    }

    @Override
    public List<AttributeListVO> list() {
        log.debug("开始处理【查询类别】的业务，无参数");
        return mapper.list();
    }
}
