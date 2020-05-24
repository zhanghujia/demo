package com.example.core.exception;


import com.example.core.enums.InfoEnum;

/**
 * @author JIA
 */

public class ResponseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    public ResponseException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public ResponseException(InfoEnum infoEnum){
        super(infoEnum.getMsg());
        this.code = infoEnum.getKey();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
