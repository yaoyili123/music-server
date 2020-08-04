package com.yaoyili.service;

import com.yaoyili.model.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SheetService {

    List<Sheet> findbyName(String name, int limit);

    List<Sheet> getRank();

    void updateRank();

    void updatePlay();

    List<Sheet> findCollections(Long uid);

    Sheet add(String name, Long uid, String username);

    List<Sheet> findByUid(Long uid);

    Sheet find(Long sid);

    Boolean checkSong(Long songid, Long sheetid);

    void update(Long id, String name, String des, MultipartFile file);

    void del(Long sid);

    void delByUser(Long uid);

    void addToSheet(Long songId, Long sheetId);

    void delFromSheet(Long songId, Long sheetId);

    void delSong(Long songId);

    void incr(Long id);
}
