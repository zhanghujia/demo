package com.example.web.handler;

import com.example.core.enums.InfoEnum;
import com.example.core.exception.ResponseException;
import com.example.core.utils.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author JIA
 */

@RestControllerAdvice
public class ExceptionResultHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder errorInfo = new StringBuilder();
        BindingResult bindingResult = exception.getBindingResult();
        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            if (i > 0) {
                errorInfo.append(",");
            }
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            errorInfo.append(fieldError.getField()).append(" :").append(fieldError.getDefaultMessage());
        }

        //返回BaseResponse
        Result response = new Result();
        response.setMsg(errorInfo.toString());
        response.setCode(401);
        return response;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleRequestParameterException(MissingServletRequestParameterException e){
        return Result.exception(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleConstraintViolationException(ConstraintViolationException exception) {
        StringBuilder errorInfo = new StringBuilder();
        String errorMessage;

        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation<?> item : violations) {
            errorInfo.append(item.getMessage()).append(",");
        }
        errorMessage = errorInfo.substring(0, errorInfo.toString().length() - 1);

        //返回BaseResponse
        Result response = new Result();
        response.setMsg(errorMessage);
        response.setCode(402);
        return response;
    }


    @ExceptionHandler(ResponseException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result handleResponseException(ResponseException e){
        return Result.exception(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Result handleSqlException(SQLException e){
        return Result.exception(e.getErrorCode(),e.getMessage());
    }


    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result handleNullPointerException(NullPointerException e){
        return Result.exception(400,e.getMessage());
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public Result handlerNumberFormatException(NumberFormatException e){
        return Result.exception(e.hashCode(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e){
        return Result.exception(-1,e.getMessage());
    }


}
