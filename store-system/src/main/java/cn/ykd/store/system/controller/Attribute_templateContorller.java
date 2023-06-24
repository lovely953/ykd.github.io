package cn.ykd.store.system.controller;

import cn.ykd.store.system.pojo.dto.Attribute_templateAddNewDTO;
import cn.ykd.store.system.service.IAttribute_templateService;
import cn.ykd.store.system.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/attribute_templates")
@Api(tags = "2.模板属性管理模块")//api页面的中文显示和排序
@Validated
public class Attribute_templateContorller {
    @Autowired
    private IAttribute_templateService iAttribute_templateService;

    @ApiOperation("添加模板属性")
    @ApiOperationSupport(order = 100)
    @PostMapping ("/add_new")
    public JsonResult insert(@Valid Attribute_templateAddNewDTO attribute_templateAddNewDTO){
        log.debug("开始【添加模板属性】功能，参数为：{}",attribute_templateAddNewDTO);
        iAttribute_templateService.addNew_Attribute(attribute_templateAddNewDTO);
        return JsonResult.OK();
    }
}
