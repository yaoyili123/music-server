package com.yaoyili.service.impl;

import com.yaoyili.controller.*;
import com.yaoyili.controller.resbeans.SheetResponse;
import com.yaoyili.dao.*;
import com.yaoyili.model.*;
import com.yaoyili.service.SheetService;
import com.yaoyili.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class SheetServiceImpl implements SheetService {

    @Autowired
    private SheetMapper sheetMapper;

    @Autowired
    private AccountAuthMapper accountAuthMapper;

    @Autowired
    private SheetSongMapper sheetSongMapper;

    @Override
    public List<SheetResponse> findSheets(int uid) {
        return sheetsConvert(sheetMapper.selectByUid(uid));
    }

    @Override
    public SheetResponse findSheet(int sid) {
        List<Sheet> sheets = new ArrayList<>();
        sheets.add(sheetMapper.selectByPrimaryKey(sid));
        return sheetsConvert(sheets).get(0);
    }

    @Override
    public List<SheetResponse> findCollections(int uid) {
        return sheetsConvert(sheetMapper.selectCollections(uid));
    }

    @Override
    public Boolean checkSong(int songid, int sheetid) {
        return sheetSongMapper.selectByPrimaryKey(new SheetSongKey(songid, sheetid)) == null ? false : true;
    }

    @Override
    public List<SheetResponse> findSheetbyName(String name, int limit) {
        List<SheetResponse> responses = sheetsConvert(sheetMapper.selectByName(name));

        if (limit == -1 || limit > responses.size())
            return responses;
        else
            return responses.subList(0, limit);
    }

    @Override
    public SheetResponse addSheet(String name, int uid) {
        Sheet sheet = new Sheet();
        sheet.setName(name);
        sheet.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_SHEETPIC);
        sheet.setUid(uid);
        sheet.setSongNum(0);
        sheetMapper.insertSelective(sheet);
        List<Sheet> sheets = new ArrayList<>();
        sheets.add(sheetMapper.selectByPrimaryKey(sheet.getId()));
        return sheetsConvert(sheets).get(0);
    }

    @Override
    public void updateSheet(int id, String name, String des, MultipartFile file) {
        Sheet sheet = new Sheet();
        sheet.setId(id);
        sheet.setName(name);
        if (file != null)
            sheet.setPicUrl(Global.HOST_NAME + '/' + file.getOriginalFilename());
        sheet.setDescription(des);
        sheetMapper.updateByPrimaryKeySelective(sheet);
    }

    @Override
    public void deleteSheet(int sid) {
        sheetMapper.deleteByPrimaryKey(sid);
    }

    @Override
    public void addSongToSheet(int songId, int sheetId) {
        if (sheetSongMapper.selectByPrimaryKey(new SheetSongKey(songId, sheetId)) != null)
            throw new CheckException("已添加到歌单");
        Sheet sheet = sheetMapper.selectByPrimaryKey(sheetId);
        sheet.setSongNum(sheet.getSongNum() + 1);
        sheetSongMapper.insert(new SheetSongKey(songId, sheetId));
        sheetMapper.updateByPrimaryKeySelective(sheet);
    }

    @Override
    public void deleteSongFromSheet(int songId, int sheetId) {
        Sheet sheet = sheetMapper.selectByPrimaryKey(sheetId);
        sheet.setSongNum(sheet.getSongNum() - 1);
        sheetSongMapper.deleteByPrimaryKey(new SheetSongKey(songId, sheetId));
        sheetMapper.updateByPrimaryKeySelective(sheet);
    }

    private List<SheetResponse> sheetsConvert(List<Sheet> sheets) {
        List<SheetResponse> responses = new ArrayList<>();
        for(Sheet sheet: sheets) {
            AccountAuth account = accountAuthMapper.selectByPrimaryKey(sheet.getUid());
            SheetResponse response = new SheetResponse(sheet, account.getNickname());
            responses.add(response);
        }

        return responses;
    }
}
