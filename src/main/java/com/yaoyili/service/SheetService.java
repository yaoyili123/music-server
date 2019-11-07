package com.yaoyili.service;

import com.yaoyili.controller.AlbumResponse;
import com.yaoyili.controller.SheetResponse;
import com.yaoyili.controller.SongResponse;
import com.yaoyili.model.Album;
import com.yaoyili.model.Artist;
import com.yaoyili.model.Sheet;
import com.yaoyili.model.Song;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SheetService {

    List<SheetResponse> findSheetbyName(String name, int limit);

    SheetResponse addSheet(String name, int uid);

    List<SheetResponse> findSheets(int uid);

    SheetResponse findSheet(int sid);

    void updateSheet(int id, String name, String des, MultipartFile file);

    void deleteSheet(int sid);

    void addSongToSheet(int songId, int sheetId);

    void deleteSongFromSheet(int songId, int sheetId);
}
