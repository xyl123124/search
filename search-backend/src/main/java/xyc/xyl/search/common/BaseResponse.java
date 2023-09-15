package xyc.xyl.search.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 * @param <T>
 * @author lacy
 */

@Data
public class BaseResponse<T> implements Serializable {
    /**
     * 状态码
     */
    private int code;

    /**
     * 数据
     */
    private T data;

    /**
     * 信息
     */
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code,data,"");
    }

    public BaseResponse(ErrorCode errorCode){
        this(errorCode.getCode(),null,errorCode.getMessage());
    }
}
