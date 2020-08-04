package com.yaoyili.controller.ao;


import lombok.Data;
import lombok.NoArgsConstructor;

/*用于业务逻辑API响应*/
@Data
@NoArgsConstructor
public class ResultBean<T> {

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    public static final int CHECK_ERROR  = 3;

    private int code = 0;

    private int total;

    private String msg = "success";

    private T data;

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
}
