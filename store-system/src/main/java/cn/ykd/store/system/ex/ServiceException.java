package cn.ykd.store.system.ex;

import cn.ykd.store.system.web.ServiceCode;

/**
 * service层业务异常
 */
public class ServiceException extends RuntimeException{

    public ServiceCode serviceCode;

    public ServiceException() {
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }

    //关键异常
    public ServiceException(ServiceCode serviceCode,String message) {
        super(message);
        this.serviceCode=serviceCode;
    }


}
