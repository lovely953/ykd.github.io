package cn.ykd.store.system.service.Impl;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.mapper.BrandMapper;
import cn.ykd.store.system.pojo.dto.BrandAddNewDTO;
import cn.ykd.store.system.pojo.dto.BrandUopdateDTO;
import cn.ykd.store.system.pojo.entity.Brand;
import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import cn.ykd.store.system.pojo.vo.BrandlistitemVO;
import cn.ykd.store.system.repo.IBrandRedisRepository;
import cn.ykd.store.system.service.IBrandService;
import cn.ykd.store.system.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service

public class IBrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private IBrandRedisRepository brandRedisRepository;

    @Override
    public void addnew(BrandAddNewDTO brandAddNewDTO) {
        log.debug("【添加品牌】业务开始传入数据为:{}",brandAddNewDTO);
        //首先获取品牌名称
        String name = brandAddNewDTO.getName();
        //调用brandMapper.countByName()里面的方法判断是否占用
        int count = brandMapper.countByName(name);
        if(count > 0){
            //如果占用抛出异常
            String message = "添加失败,品牌名称被占用";
            log.warn("添加失败,品牌名称被占用");
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        //创建Brand对象将前端值赋值给它
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandAddNewDTO,brand);
        //没有占用就调用insert方法
        log.debug("即将插入数据:{}",brand);
        brandMapper.insert(brand);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("【根据id删除品牌】业务开始传入数据为:{}",id);
        //根据传来的id调用getbBandStrandardById查询数据库中是否存在改数据
        BrandStrandardVO res = brandMapper.getBandStrandardById(id);
        if (res == null) {
            //不存在抛异常
            String message  = "删除失败，尝试访问的id数据不存在！";
            log.warn("删除失败，尝试访问的id数据不存在！");
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        //调用mapper的deletebyId方法
        log.debug("id{}存在数据库中,即将删除",id);
        int rows = brandMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除品牌失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public void updateBrand(Long id, BrandUopdateDTO brandUopdateDTO) {
        log.debug("开始处理根据id修改品牌业务参数如下：id：{} ，修改数据：{}", id, brandUopdateDTO);
        //通过id判断数据库中是否存在，brandMapper.getbBandStrandardById
        //判断返回值是否为空，
        //是：数据不存在
        BrandStrandardVO brandStrandardVO = brandMapper.getBandStrandardById(id);
        if (brandStrandardVO == null) {
            String message = "修改失败！该id的数据不存在";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        //通过brandMapper.countByName()方法修改的名字再数据库是否存在
        //如果返回值>0
        //表示名字已经存在
        int countByName = brandMapper.countByName(brandUopdateDTO.getName());
        if (countByName > 0) {
            String message = "修改失败！名字重复";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        //创建brand对象
        //把前端传来的值复制给brand对象
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandUopdateDTO, brand);
        brand.setId(id);
        log.debug("修改数据如下{}",brand);
        //调用方法修改数据
        brandMapper.update(brand);
    }

    @Override
    public List<BrandlistitemVO> list() {
        log.debug("开始查询类别业务");
       // List<BrandlistitemVO> list = brandMapper.list();
        List<BrandlistitemVO> list = brandRedisRepository.list();
        return list;
    }

    @Override
    public void setEnable(Long id) {
        updateEnableById(id, 1);
    }

    @Override
    public void setDisable(Long id) {
        updateEnableById(id, 0);
    }

    private void updateEnableById(Long id, Integer enable) {
        String[] enableText = {"禁用", "启用"};
        log.debug("开始处理【{}品牌】的业务，ID：{}，目标状态：{}", enableText[enable], id, enable);
        // 检查数据是否存在
        BrandStrandardVO queryResult = brandMapper.getBandStrandardById(id);
        if (queryResult == null) {
            String message = enableText[enable] + "品牌失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 检查当前状态是否与参数表示的状态相同
        if (queryResult.getEnable().equals(enable)) {
            String message = enableText[enable] + "品牌失败，当前品牌已经处于" + enableText[enable] + "状态！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 准备执行更新
        Brand brand = new Brand();
        brand.setId(id);
        brand.setEnable(enable);
        log.debug("即将修改数据，参数：{}", brand);
        int rows = brandMapper.update(brand);
        if (rows != 1) {
            String message = enableText[enable] + "品牌失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public BrandStrandardVO getStandardById(Long id) {
        log.debug("开始处理【根据id查询品牌详情】的业务，参数：{}", id);
        // BrandStandardVO queryResult = brandMapper.getStandardById(id);
        BrandStrandardVO queryResult = brandRedisRepository.get(id);
        if (queryResult == null) {
            String message = "根据id查询品牌详情失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回品牌详情：{}", queryResult);
        return queryResult;
    }

    @Override
    public void rebuildCache() {
        log.debug("开始处理【重建缓存】的业务，无参数");

        brandRedisRepository.deleteAll();

        List<BrandlistitemVO> list = brandMapper.list();
        brandRedisRepository.save(list);

        for (BrandlistitemVO brandlistitemVO : list) {
            Long brandId = brandlistitemVO.getId();
            BrandStrandardVO bandStrandardvo = brandMapper.getBandStrandardById(brandId);
            brandRedisRepository.save(bandStrandardvo);
        }
    }

}
