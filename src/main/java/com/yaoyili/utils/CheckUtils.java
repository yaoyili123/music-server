package com.yaoyili.utils;

import com.yaoyili.CheckException;
import com.yaoyili.ParamErrorException;

public class CheckUtils {

    public static void check(boolean condition, String msg) {
        if (!condition) {
            fail(msg);
        }
    }

    public static void notEmpty(String str, String msg) {
        if (str == null || str.isEmpty()) {
            fail(msg);
        }
    }

    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            fail(msg);
        }
    }

    public static void checkPageParams(int page, int size) {
        //page从1开始
        if (page == -1 && size != -1
                || page != -1 && size == -1
                || size == 0 || page == 0)
            throw new CheckException("分页参数错误");
    }

    private static void fail(String msg) {
        throw new ParamErrorException(msg);
    }
}
