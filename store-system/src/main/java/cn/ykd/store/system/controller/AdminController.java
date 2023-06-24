package cn.ykd.store.system.controller;



import cn.ykd.store.system.pojo.dto.AdminAddNewDTO;
import cn.ykd.store.system.pojo.dto.AdminLoginDTO;
import cn.ykd.store.system.pojo.vo.AdminListItemVO;
import cn.ykd.store.system.security.AdminDetails;
import cn.ykd.store.system.security.LoginPrincipal;
import cn.ykd.store.system.service.IAdminService;
import cn.ykd.store.system.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 处理管理员相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/admins")
@Api(tags = "01. 管理员管理模块")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    public AdminController() {
        log.debug("创建控制器对象：AdminController");
    }

        // http://localhost:9081/admins/login
        @ApiOperation("管理员登录")
        @ApiOperationSupport(order = 10)
        @PostMapping("/login")
        public JsonResult<String> login(AdminLoginDTO adminLoginDTO) {
            log.debug("开始处理【管理员登录】的请求，参数：{}", adminLoginDTO);
            String jwt = adminService.login(adminLoginDTO);
            return JsonResult.OK(jwt);
        }

    // http://localhost:9081/admins/add-new
    @ApiOperation("添加管理员")
    @ApiOperationSupport(order = 100)
    @PreAuthorize("hasAuthority('/ams/admin/add-new')")
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(AdminAddNewDTO adminAddNewDTO) {
        log.debug("开始处理【添加管理员】的请求，参数：{}", adminAddNewDTO);
        adminService.addNew(adminAddNewDTO);
        return JsonResult.OK();
    }

    // http://localhost:9081/admins/9527/delete
    @ApiOperation("根据id删除删除管理员")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name = "id", value = "管理员ID", required = true, dataType = "long")
    @PreAuthorize("hasAuthority('/ams/admin/delete')")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【根据id删除删除管理员】的请求，参数：{}", id);
        adminService.delete(id);
        return JsonResult.OK();
    }

    // http://localhost:9081/admins/9527/enable
    @ApiOperation("启用管理员")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParam(name = "id", value = "管理员ID", required = true, dataType = "long")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    @PostMapping("/{id:[0-9]+}/enable")
    public JsonResult<Void> setEnable(@PathVariable Long id) {
        log.debug("开始处理【启用管理员】的请求，参数：{}", id);
        adminService.setEnable(id);
        return JsonResult.OK();
    }

    // http://localhost:9081/admins/9527/disable
    @ApiOperation("禁用管理员")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParam(name = "id", value = "管理员ID", required = true, dataType = "long")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    @PostMapping("/{id:[0-9]+}/disable")
    public JsonResult<Void> setDisable(@PathVariable Long id) {
        log.debug("开始处理【禁用管理员】的请求，参数：{}", id);
        adminService.setDisable(id);
        return JsonResult.OK();
    }

//    // http://localhost:9081/admins
//    @ApiOperation("查询管理员列表")
//    @ApiOperationSupport(order = 420)
//    @PreAuthorize("hasAuthority('/ams/admin/read')")
//    @GetMapping("")
//    public JsonResult<List<AdminListItemVO>> list(@ApiIgnore @AuthenticationPrincipal LoginPrincipal loginPrincipal) {
//        log.debug("开始处理【查询管理员列表】的请求，参数：无");
//        log.debug("当事人：{}", loginPrincipal);
//        log.debug("当事人的ID：{}", loginPrincipal.getId());
//        log.debug("当事人的用户名：{}", loginPrincipal.getUsername());
//        List<AdminListItemVO> list = adminService.list();
//        return JsonResult.OK(list);
//    }
    // http://localhost:9081/admins
    @ApiOperation("查询管理员列表")
    @ApiOperationSupport(order = 420)
   @PreAuthorize("hasAuthority('/ams/admin/read')")//配置权限
    @GetMapping("")
   // public JsonResult<List<AdminListItemVO>> list(@ApiIgnore@AuthenticationPrincipal AdminDetails adminDetails) {
    public JsonResult<List<AdminListItemVO>> list(@ApiIgnore@AuthenticationPrincipal LoginPrincipal principal) {
        log.debug("开始处理【查询管理员列表】的请求，参数：无");
        log.debug("当事人:{}",principal);
        log.debug("当事人ID:{}",principal.getId());
        log.debug("当事人用户名:{}",principal.getUsername());
        List<AdminListItemVO> list = adminService.list();
        return JsonResult.OK(list);
    }

}
