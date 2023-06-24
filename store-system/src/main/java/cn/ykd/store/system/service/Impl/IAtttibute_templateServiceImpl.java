package cn.ykd.store.system.service.Impl;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.mapper.Attribute_templateMapper;
import cn.ykd.store.system.pojo.dto.Attribute_templateAddNewDTO;
import cn.ykd.store.system.pojo.entity.Attribute_template;
import cn.ykd.store.system.pojo.vo.Attribute_templateStandardVO;
import cn.ykd.store.system.service.IAttribute_templateService;
import cn.ykd.store.system.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 属性类型的service的实现类
 */
@Slf4j
@Service

public class IAtttibute_templateServiceImpl implements IAttribute_templateService {

    @Autowired
    private Attribute_templateMapper attribute_templateMapper;

    @Override
    public void addNew_Attribute(Attribute_templateAddNewDTO at_addnew) {
        log.debug("开始处理【添加属性模板】的业务，参数:{}",at_addnew);
        String at_addnewName= at_addnew.getName();
        int count = attribute_templateMapper.countByName(at_addnewName);
        if(count >0){
           String message = "添加失败，属性模板名称被占用";
            log.warn("添加失败，属性模板名称被占用");
           throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        log.debug("属性模板名称没有被占用");
        Attribute_template attribute_template = new Attribute_template();
        BeanUtils.copyProperties(at_addnew,attribute_template);
        log.debug("即将插入数据，参数是:{}",attribute_template);
        attribute_templateMapper.insert(attribute_template);
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【根据id删除模板属性】业务，参数：{}",id);
        //根据传过来的id调用mapper里面的getstandardAttribute_template判断参数是否存在
        Attribute_templateStandardVO count = attribute_templateMapper.getAttribute_template(id);
        log.debug("根据id{}查询到的结果:{}",id,count);
        if (count == null) {
            //不存在则抛出异常
            String message = "删除失败，尝试访问的id数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        //存在则调用deleteById删除
        log.debug("即将删除数据");
        attribute_templateMapper.deleteById(id);
    }
}
