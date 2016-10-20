package com.jxufe.dto;

/**
 * 秒杀分类的相关web数据返回格式
 * Created by liuburu on 2016/10/16.
 */
public class SeckillResult<T> {
    private boolean success;
    private T data;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
