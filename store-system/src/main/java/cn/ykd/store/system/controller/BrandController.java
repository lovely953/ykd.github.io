package cn.ykd.store.system.controller;

import cn.ykd.store.system.pojo.dto.BrandAddNewDTO;
import cn.ykd.store.system.pojo.dto.BrandUopdateDTO;
import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import cn.ykd.store.system.pojo.vo.BrandlistitemVO;
import cn.ykd.store.system.service.IBrandService;
import cn.ykd.store.system.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/brands")
@Api(tags = "5.品牌管理模块")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    //添加
    @ApiOperation(value = "添加类别")
    @ApiOperationSupport(order = 100)//排序
    @PostMapping("/add_new")
    public JsonResult<Void> BrandAddnew(BrandAddNewDTO brandAddNewDTO){
        log.debug("处理新增类别业务，参数:{}",brandAddNewDTO);
        brandService.addnew(brandAddNewDTO);
        return JsonResult.OK();
    }

    //删除
    @PostMapping("/{id:[0-9]+}/delete")//restful的url设计风格
    @ApiOperation("根据id删除品牌")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name="id", value = "品牌id" ,required = true,example = "9527")
    public JsonResult<Void> delete(@Range(min=1,max=10000,message = "删除失败,品牌的id非法！") @PathVariable Long id){
        log.debug("开始处理根据id：{}删除品牌业务",id);
        brandService.deleteById(id);
        return JsonResult.OK();
    }

    //修改
    @ApiOperation(value = "根据id修改品牌")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/update")
    public  JsonResult<Void> update(@PathVariable Long id, BrandUopdateDTO brandUopdateDTO){
        log.debug("开始处理根据id{}修改品牌业务",brandUopdateDTO);
        brandService.updateBrand(id,brandUopdateDTO);
        return JsonResult.OK();
    }

    @ApiOperation(value = "查询品牌列表")
    @ApiOperationSupport(order = 420)
    @GetMapping("")
    public JsonResult<List<BrandlistitemVO>> list(){
        log.debug("开始处理查询品牌列表业务");
        List<BrandlistitemVO> list = brandService.list();
        return JsonResult.OK(list);
    }

    // http://localhost:9080/brands/9527/enable
    @ApiOperation("启用品牌")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/enable")
    public JsonResult<Void> setEnable(@PathVariable Long id) {
        log.debug("开始处理【启用品牌】的请求，参数：{}", id);
        brandService.setEnable(id);
        return JsonResult.OK();
    }

    // http://localhost:9080/brands/9527/disable
    @ApiOperation("禁用品牌")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/disable")
    public JsonResult<Void> setDisable(@PathVariable Long id) {
        log.debug("开始处理【禁用品牌】的请求，参数：{}", id);
        brandService.setDisable(id);
        return JsonResult.OK();
    }

    // http://localhost:9080/brands/9527
    @ApiOperation("根据id查询品牌详情")
    @ApiOperationSupport(order = 400)
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<BrandStrandardVO> getStandardById(@PathVariable Long id) {
        log.debug("开始处理【根据id查询品牌详情】的请求，参数：{}", id);
        BrandStrandardVO brand = brandService.getStandardById(id);
        return JsonResult.OK(brand);
    }

    @ApiOperation("重建品牌缓存")
    @ApiOperationSupport(order = 500)
    @PostMapping("/rebuild-cache")
    public JsonResult<Void> rebuildCache() {
        log.debug("开始处理【重建品牌缓存】的请求，无参数");
        brandService.rebuildCache();
        return JsonResult.OK();
    }
}
