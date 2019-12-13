package com.yaoyili.service;

import com.yaoyili.controller.resbeans.SheetResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SheetService {

    List<SheetResponse> findSheetbyName(String name, int limit);

    List<SheetResponse> findCollections(int uid);

    SheetResponse addSheet(String name, int uid);

    List<SheetResponse> findSheets(int uid);

    SheetResponse findSheet(int sid);

    Boolean checkSong(int songid, int sheetid);

    void updateSheet(int id, String name, String des, MultipartFile file);

    void deleteSheet(int sid);

    void addSongToSheet(int songId, int sheetId);

    void deleteSongFromSheet(int songId, int sheetId);
}
