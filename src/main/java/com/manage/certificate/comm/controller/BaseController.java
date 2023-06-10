package com.manage.certificate.comm.controller;

import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.comm.exception.BizExceptionEnum;
import com.manage.certificate.comm.exception.BizResponseEnum;
import com.manage.certificate.comm.model.Response;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:
 * @author: guohao.tan
 * @since: 2021/12/24 4:32 下午
 * @history: 1.2021/12/24 created by guohao.tan
 */
@CrossOrigin
public abstract class BaseController {

    /**
     * @method: 返回报错信息
     * @description: failResponse
     * @date:
     * @author:
     */
    public Response failResponse(BizException bizException) {
        return this.initResponse(bizException.getMessage(), bizException.getCode());
    }

    /**
     * @method: 不带返回参数报错信息
     * @description: failResponse
     * @date:
     * @author:
     */
    public Response failResponse(BizExceptionEnum bizExceptionEnum) {
        return this.initResponse(bizExceptionEnum.getMessage(), bizExceptionEnum.getCode());
    }

    /**
     * @method: 返回成功对象
     * @description: successResponse
     * @date:
     * @author:
     */
    public Response successResponse(BizResponseEnum bizResponseEnum) {
        return this.initResponse(bizResponseEnum.getMessage(),bizResponseEnum.getCode());
    }



    /**
     * @method: 返回枚举成功对象
     * @description: successResponse
     * @date:
     * @author:
     */
    public Response successResponse(BizResponseEnum bizResponseEnum, Object o) {
        Response response = this.initResponse(bizResponseEnum.getMessage(),bizResponseEnum.getCode());
        response.setData(o);
        return response;
    }

    public Response successResponse(BizResponseEnum bizResponseEnum, Object o, Long total) {
        Response response = this.initResponse(bizResponseEnum.getMessage(),bizResponseEnum.getCode());
        response.setData(o);
        response.setTotal(total);
        return response;
    }
    private Response initResponse(String msg,String code){
        Response response = new Response();
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }

}
