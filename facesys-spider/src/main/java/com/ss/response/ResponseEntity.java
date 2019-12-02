package com.ss.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ResponseEntity：接口响应通用对象
 * @author FrancisYs
 * @date 2019/8/13
 * @email yaoshuai@ss-cas.com
 */
public class ResponseEntity<T> extends Object implements Serializable {

    private static final long serialVersionUID = -1L;
    @JsonView({IResponseEntityView.class})
    private String code;
    @JsonView({IResponseEntityView.class})
    private String message;
    @JsonView({IResponseEntityView.class})
    private T data;
    @JsonView({IResponseEntityView.class})
    private List<ArgumentInvalidResult> error = new ArrayList<>();


    public ResponseEntity() {
    }


    public ResponseEntity(int code, String message) {
        this.code = String.valueOf(code);
        this.message = message;
    }

    public ResponseEntity(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public ResponseEntity(int code, String message, T data) {
        this(code, message);
        this.data = data;
    }


    public ResponseEntity(String code, String message, T data) {
        this(code, message);
        this.data = data;
    }


    public List<ArgumentInvalidResult> getError() {
        return this.error;
    }


    public void setError(List<ArgumentInvalidResult> error) {
        this.error = error;
    }


    public T getData() {
        return (T) this.data;
    }


    public void setData(T data) {
        this.data = data;
    }


    @JsonIgnore
    public int getCode() {
        return Integer.valueOf(this.code);
    }


    public void setCode(String code) {
        this.code = code;
    }


    public void setCode(int code) {
        this.code = String.valueOf(code);
    }


    @JsonIgnore
    public String getFullCode() {
        return this.code;
    }


    public String getMessage() {
        return this.message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "ResponseEntity [code=" + this.code + ", message=" + this.message + ", data=" + this.data + ", error=" + this.error + "]";
    }

    public static interface IResponseEntityView {

    }

}
