package cn.ykd.store.system.controller;

import cn.ykd.store.system.pojo.dto.AlbumUpdateDTO;
import cn.ykd.store.system.pojo.dto.CategoryAddNewDTO;
import cn.ykd.store.system.pojo.dto.CategoryUpdateDTO;
import cn.ykd.store.system.pojo.vo.AlbumListitemVO;
import cn.ykd.store.system.pojo.vo.CategoryListitemVO;
import cn.ykd.store.system.pojo.vo.CategoryStandardVO;
import cn.ykd.store.system.service.ICategoryService;
import cn.ykd.store.system.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categorys")
@Api(tags = "4.类别管理模块")
@Validated
public class CategoryController {
    @Autowired
    private ICategoryService service;

    @PostMapping("/add-new")
    @ApiOperation(value = "添加类别")
    @ApiOperationSupport(order = 100)
    public JsonResult<Void> addNew(@Valid CategoryAddNewDTO categoryAddNewDTO){
        log.debug("开始处理 【添加类别】 的请求,参数 :{}",categoryAddNewDTO);
        service.addNew(categoryAddNewDTO);
        return JsonResult.OK();
    }

    //localhost://8010/categorys/9527/delete
    @PostMapping("/{id:[0-9]+}/delete")//restful的url设计风格
    @ApiOperation("根据id删除类别")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name="id", value = "类别id" ,required = true,example = "256")
    //请求参数较少，或参数各不相关，在类上加“@validated”注解,在检查参数上加注解@Range检查数值类型参数是否在指定区间
    public JsonResult<Void> delete(@Range(min=1,max=10000,message = "删除失败,类别的id非法！") @PathVariable Long id){
        log.debug("开始处理 添加相册 的请求,参数 :{}",id);
        service.delete(id);
        return JsonResult.OK();
    }


    @PostMapping("/{id:[0-9]+}/update")
    @ApiOperationSupport(order = 210)
    @ApiImplicitParam(name="id", value = "类别id" ,required = true,example = "256")
    public JsonResult<Void> updateById(Long id, CategoryUpdateDTO categoryUpdateDTO){
        log.debug("开始处理 【根据id修改类别】的请求,参数 :{}",id);
        service.updateInfoById(id, categoryUpdateDTO);
        return JsonResult.OK();
    }


    //localhost://8010/categorys/setEnable
    @PostMapping("/{id:[0-9]+}/setEnable")//restful的url设计风格
    @ApiOperation("启用类别")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParam(name="id", value = "类别id" ,required = true)
    //请求参数较少，或参数各不相关，在类上加“@validated”注解,在检查参数上加注解@Range检查数值类型参数是否在指定区间
    public JsonResult<Void> setEnable(@Range(min=1,max=10000,message = "启用失败,类别的id非法！") @PathVariable Long id){
        log.debug("开始处理 启用类别 的请求,参数 :{}",id);
        service.setEnable(id);
        return JsonResult.OK();
    }

    //localhost://8010/categorys/52/setDisable
    @PostMapping("/{id:[0-9]+}/setDisable")//restful的url设计风格
    @ApiOperation("禁用类别")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParam(name="id", value = "类别id" ,required = true)
    //请求参数较少，或参数各不相关，在类上加“@validated”注解,在检查参数上加注解@Range检查数值类型参数是否在指定区间
    public JsonResult<Void> setDisable(@Range(min=1,max=10000,message = "禁用失败,类别的id非法！") @PathVariable Long id){
        log.debug("开始处理 禁用类别 的请求,参数 :{}",id);
        service.setDisable(id);
        return JsonResult.OK();
    }

    //localhost://8010/categorys/setEnable
    @PostMapping("/{id:[0-9]+}/disIs_display")//restful的url设计风格
    @ApiOperation("禁用导航栏")
    @ApiOperationSupport(order = 314)
    @ApiImplicitParam(name="id", value = "类别id" ,required = true)
    //请求参数较少，或参数各不相关，在类上加“@validated”注解,在检查参数上加注解@Range检查数值类型参数是否在指定区间
    public JsonResult<Void> disIs_display(@Range(min=1,max=10000,message = "启用失败,类别的id非法！") @PathVariable Long id){
        log.debug("开始处理 启用类别 的请求,参数 :{}",id);
        service.disIs_display(id);
        return JsonResult.OK();
    }

    //localhost://8010/categorys/setEnable
    @PostMapping("/{id:[0-9]+}/enIs_display")//restful的url设计风格
    @ApiOperation("启用导航栏")
    @ApiOperationSupport(order = 313)
    @ApiImplicitParam(name="id", value = "类别id" ,required = true)
    //请求参数较少，或参数各不相关，在类上加“@validated”注解,在检查参数上加注解@Range检查数值类型参数是否在指定区间
    public JsonResult<Void> enIs_display(@Range(min=1,max=10000,message = "启用失败,类别的id非法！") @PathVariable Long id){
        log.debug("开始处理 启用类别 的请求,参数 :{}",id);
        service.enIs_display(id);
        return JsonResult.OK();
    }


    @GetMapping("")
    @ApiOperation(value = "查询类别")
    @ApiOperationSupport(order = 400)
    public JsonResult<List<CategoryListitemVO>> list(){
        log.debug("开始处理【查询类别列表】 的请求");
        List<CategoryListitemVO> list = service.list();
        return JsonResult.OK(list);
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation(value = "根据id查询类别")
    @ApiOperationSupport(order = 400)
    public JsonResult<CategoryStandardVO> getStandardById(@Range(min=1,max=10000,message = "删除失败,相册的id非法！") @PathVariable Long id){
        log.debug("开始处理【根据id查询类别列表】 的请求");
        CategoryStandardVO standardById = service.getStandardById(id);
        return JsonResult.OK(standardById);
    }

    @GetMapping("/parent_id")
    @ApiOperation(value = "根据父级类别查询类别")
    @ApiOperationSupport(order = 410)
    public JsonResult<List<CategoryListitemVO>> listByParent_id(Long parent_id){
        log.debug("开始处理【根据父级类别查询类别】 的请求");
        List<CategoryListitemVO> list = service.listByParent_id(parent_id);
        return JsonResult.OK(list);
    }
}
