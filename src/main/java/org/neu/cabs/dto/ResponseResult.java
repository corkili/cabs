package org.neu.cabs.dto;

import lombok.Data;

/**
 * 响应结果
 * @author 李浩然
 */
@Data
public class ResponseResult<T> {
    private boolean successful;

    private String msg;

    private T object;

    public static <T> ResponseResult<T> from(ServiceResult serviceResult) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setSuccessful(serviceResult.isSuccessful());
        result.setMsg(serviceResult.getMessage());
        return result;
    }

    public static <T> ResponseResult<T> from(ServiceResult serviceResult, String objectName) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setSuccessful(serviceResult.isSuccessful());
        result.setMsg(serviceResult.getMessage());
        if (serviceResult.isSuccessful()) {
            result.setObject((T) serviceResult.getExtra(objectName));
        }
        return result;
    }

    public static <T> ResponseResult<T> from(ServiceResult serviceResult, T object) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setSuccessful(serviceResult.isSuccessful());
        result.setMsg(serviceResult.getMessage());
        result.setObject(object);
        return result;
    }
}
