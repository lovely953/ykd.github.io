package cn.ykd.store.system.web;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.pojo.vo.AlbumStrandardVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Data
//@NoArgsConstructor//无参构造方法
//@AllArgsConstructor//有参构造方法

public class JsonResult<T> implements Serializable {
    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private Integer state;

    /**
     * 操作失败时的描述文本
     */
    @ApiModelProperty("消息")
    private  String message;


    /**
     * 操作成功时的响应的数据
     */
    @ApiModelProperty("数据")
    private T data;

    /**
     * 成功
     * @return jsonresult
     */
    public static JsonResult<Void> OK(){
        return OK(null);
    }

    public static<T> JsonResult<T> OK(T data){
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.state  =ServiceCode.OK.getValue();
        jsonResult.data=data;
        return jsonResult;
    }

    public static JsonResult<Void> fail(ServiceException e){
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static JsonResult<Void> fail(ServiceCode state,String message){
        JsonResult <Void>jsonResult = new JsonResult<Void>();
        jsonResult.setState(state.getValue());
        jsonResult.setMessage(message);
        return jsonResult;
    }
}
