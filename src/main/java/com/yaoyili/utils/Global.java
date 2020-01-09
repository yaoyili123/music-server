package com.yaoyili.utils;

import com.yaoyili.controller.CheckException;

public class Global {
    public static final String HOST_NAME = "http://localhost:8080";

    public static final String DEAFAULT_FILENAME = "unlogined.jpg";

    public static final String DEAFAULT_SHEETPIC = "defaultsheet.jpg";

    public static final int RANK_LENGTH = 6;

    public static void checkPageParams(int page, int size) {
        //page从1开始
        if (page == -1 && size != -1
                || page != -1 && size == -1
                || size == 0 || page == 0)
            throw new CheckException("分页参数错误");
    }
}
