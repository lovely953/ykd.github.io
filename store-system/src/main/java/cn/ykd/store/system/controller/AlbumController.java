package cn.ykd.store.system.controller;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.pojo.dto.AlbumAddNewDTO;
import cn.ykd.store.system.pojo.dto.AlbumUpdateDTO;
import cn.ykd.store.system.pojo.vo.AlbumListitemVO;
import cn.ykd.store.system.pojo.vo.AlbumStrandardVO;
import cn.ykd.store.system.service.IAlbumService;
import cn.ykd.store.system.web.JsonResult;
import cn.ykd.store.system.web.ServiceCode;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/albums")
@Api(tags = "1.相册管理模块")
@Validated
public class AlbumController  {

    @Autowired
    private IAlbumService albumService;

    /**
     * 添加功能
     * @param albumAddNewDTO
     * @return
     */
    //http://localhost:8010/albums/add_new?name=yy&description=tests&sort=1
    //@RequestMapping(value = {"/add_new","add"},method = {RequestMethod.POST,RequestMethod.GET})
    // @GetMapping("/add_newget")
    @ApiOperation(value = "添加相册")
    @ApiOperationSupport(order = 100)//排序
    @PostMapping("/add_new")
    //@Valid检查请求参数的基本格式
    public JsonResult<Void> insert(@Valid  AlbumAddNewDTO albumAddNewDTO){
        log.debug("开始处理 添加相册 的请求,参数 :{}",albumAddNewDTO);
            albumService.addNew(albumAddNewDTO);
            //返回结果
          return  JsonResult.OK();

    }


//
//    @GetMapping("/{name:[a-z0-9]+}")
//    public String getStandardByName(@PathVariable String name){
//        return "根据id【"+name+"】查询相册详情";
//    }

    //localhost://8010/albums/9527/delete
    @PostMapping("/{id:[0-9]+}/delete")//restful的url设计风格
    @ApiOperation("根据id删除相册")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name="id", value = "相册id" ,required = true,example = "9527")
    //请求参数较少，或参数各不相关，在类上加“@validated”注解,在检查参数上加注解@Range检查数值类型参数是否在指定区间
    public JsonResult<Void> delete(@Range(min=1,max=10000,message = "删除失败,相册的id非法！") @PathVariable Long id){
        log.debug("开始处理 添加相册 的请求,参数 :{}",id);
        albumService.delete(id);
       return JsonResult.OK();
    }

    @ApiOperation(value = "根据id修改相册")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/update")
   // @ApiImplicitParam(name = "id",value = "相册属性")//未封装的参数,参数只有一个
    public JsonResult<Void> updateInfoById(@PathVariable Long id, AlbumUpdateDTO albumUpdateDTO){
        log.debug("开始处理 修改相册的请求,参数 :{}",id);
        albumService.updateInfoById(id,albumUpdateDTO);
        return  JsonResult.OK();
    }



    //localhost://8010/albums/111
    @GetMapping("/{id:[0-9]+}")
    @ApiOperation(value = "根据id查询相册")
    @ApiOperationSupport(order = 410)
    public JsonResult<AlbumStrandardVO> getStandardById(@Range(min=1,max=10000,message = "删除失败,相册的id非法！") @PathVariable Long id){
        log.debug("根据id【"+id+"】查询相册详情");
        AlbumStrandardVO album = albumService.getStandardById(id);
        return JsonResult.OK(album);
    }

    @ApiOperation(value = "查询相册列表")
    @ApiOperationSupport(order = 420)
    @GetMapping("")
    public JsonResult<List<AlbumListitemVO>> list(){
        log.debug("开始处理【查询相册列表】业务");
        List<AlbumListitemVO> list = albumService.list();
        return JsonResult.OK(list);
    }


}
