package com.yaoyili.controller;

import com.yaoyili.model.Sheet;
import lombok.Data;

import java.util.Date;

@Data
public class SheetResponse extends Sheet {

    private String username;

    public SheetResponse() {}

    public SheetResponse(String username) {
        super();
        this.username = username;
    }

    public SheetResponse(Sheet sheet, String username) {
        super(sheet.getId(), sheet.getName(), sheet.getUid(), sheet.getSongNum(), sheet.getPicUrl(), sheet.getDescription());
        this.username = username;
    }
}
