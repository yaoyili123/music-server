package com.yaoyili.controller.resbeans;

import com.yaoyili.model.Sheet;
import lombok.Data;

import java.util.Date;

public class SheetResponse extends Sheet {

    private String username;

    public SheetResponse() {}

    public SheetResponse(String username) {
        super();
        this.username = username;
    }

    public SheetResponse(Sheet sheet, String username) {
        super(sheet.getId(), sheet.getName(), sheet.getUid(), sheet.getSongNum(), sheet.getPicUrl(), sheet.getDescription(), sheet.getPlay());
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
