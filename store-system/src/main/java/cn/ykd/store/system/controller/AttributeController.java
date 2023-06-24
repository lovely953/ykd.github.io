package cn.ykd.store.system.controller;

import cn.ykd.store.system.pojo.dto.AttributeAddNewDTO;
import cn.ykd.store.system.service.IAttributeService;
import cn.ykd.store.system.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 属性业务
 */
@Slf4j
@Api(tags = "3.属性管理模块")
@RestController
@RequestMapping("/abttributes")
@Validated
public class AttributeController {
    @Autowired
    private IAttributeService iAttributeService;

    @PostMapping("/add_new")
    @ApiOperation("添加属性")
    @ApiOperationSupport(order = 100)
    public JsonResult  insert(@Valid AttributeAddNewDTO attributeAddNewDTO){
        log.debug("【添加属性】功能启用，参数如下：{}",attributeAddNewDTO);
      //调用service中的addnew方法
        iAttributeService.addNew(attributeAddNewDTO);
        return JsonResult.OK();
    }
}
