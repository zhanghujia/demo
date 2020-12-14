package com.example.core.utils.result;


import com.example.core.enums.InfoEnum;
import com.example.core.enums.SuccessEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author JIA
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(example = "200")
    private Integer code;

    @ApiModelProperty(example = "服务请求成功")
    private String msg;

    private T data;

    public Result() {

    }

    /**
     * 请求成功 返回数据
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> Result success(T object) {
        Result result = new Result();
        result.setCode(SuccessEnum.SUCCESS_REQUEST.getKey());
        result.setMsg(SuccessEnum.SUCCESS_REQUEST.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 请求成功
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 自定义信息返回
     *
     * @param infoEnum
     * @return
     */
    public static Result info(InfoEnum infoEnum) {
        Result result = new Result();
        result.setCode(infoEnum.getKey());
        result.setMsg(infoEnum.getMsg());
        return result;
    }

    /**
     * 自定义异常返回
     *
     * @param key
     * @param msg
     * @return
     */
    public static Result exception(Integer key, String msg) {
        Result result = new Result();
        result.setCode(key);
        result.setMsg(msg);
        return result;
    }

}
