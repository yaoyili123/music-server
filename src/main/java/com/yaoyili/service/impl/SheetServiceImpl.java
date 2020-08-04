package com.yaoyili.service.impl;

import com.yaoyili.dao.*;
import com.yaoyili.model.*;
import com.yaoyili.CheckException;
import com.yaoyili.service.SheetService;
import com.yaoyili.utils.Global;
import com.yaoyili.utils.RedisUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import static com.yaoyili.utils.CheckUtils.*;
import java.util.*;

@Service
public class SheetServiceImpl implements SheetService {

    @Autowired
    private SheetMapper sheetMapper;

    @Autowired
    private AccountAuthMapper accountAuthMapper;

    @Autowired
    private SheetSongMapper sheetSongMapper;

    @Autowired
    private SheetCollectMapper sheetCollectMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<Sheet> findByUid(Long uid) {
        return sheetMapper.selectByUid(uid);
    }

    @Override
    public Sheet find(Long sid) {
        return sheetMapper.select(sid);
    }

    @Override
    public List<Sheet> findCollections(Long uid) {
        return sheetMapper.selectCollections(uid);
    }

    @Override
    public Boolean checkSong(Long songid, Long sheetid) {
        return sheetSongMapper.select(new SheetSongKey(songid, sheetid)) == null ? false : true;
    }

    @Override
    public List<Sheet> findbyName(String name, int limit) {
        List<Sheet> responses = sheetMapper.selectByName(name);

        if (limit == -1 || limit > responses.size())
            return responses;
        else
            return responses.subList(0, limit);
    }

    @Override
    public Sheet add(String name, Long uid, String username) {

        notEmpty(name, "名字不能为空");
        check(uid > 0, "UID不合法");
        check(name.length() <= 40, "名称长度不能超过40");

        Sheet sheet = new Sheet();
        sheet.setName(name);
        sheet.setUsername(username);
        sheet.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_SHEETPIC);
        sheet.setUid(uid);
        sheet.setSongNum(0);
        sheetMapper.insert(sheet);
        return sheet;
    }

    @Override
    public void update(Long id, String name, String des, MultipartFile file) {

        notEmpty(name, "名字不能为空");
        notNull(des, "描述不能为空");
        check(id > 0, "ID不合法");
        check(name.length() <= 40, "名称长度不能超过40");

        Sheet sheet = new Sheet();
        sheet.setId(id);
        sheet.setName(name);
        if (file != null)
            sheet.setPicUrl(Global.HOST_NAME + '/' + file.getOriginalFilename());
        sheet.setDescription(des);
        sheetMapper.update(sheet);
    }

    @Override
    @Transactional
    public void del(Long sid) {
        sheetMapper.delete(sid);
        sheetSongMapper.deleteBySheet(sid);
        sheetCollectMapper.deleteBySheet(sid);
    }

    @Override
    @Transactional
    public void delByUser(Long uid) {
        List<Sheet> sheets = sheetMapper.selectByUid(uid);
        for (Sheet item: sheets) {
            del(item.getId());
        }
    }

    @Override
    @Transactional
    public void addToSheet(Long songId, Long sheetId) {
        if (sheetSongMapper.select(new SheetSongKey(songId, sheetId)) != null)
            throw new CheckException("已添加到歌单");
        Sheet sheet = sheetMapper.select(sheetId);
        sheet.setSongNum(sheet.getSongNum() + 1);
        sheetSongMapper.insert(new SheetSongKey(songId, sheetId));
        sheetMapper.update(sheet);
    }

    @Override
    @Transactional
    public void delFromSheet(Long songId, Long sheetId) {
        Sheet sheet = sheetMapper.select(sheetId);
        sheet.setSongNum(sheet.getSongNum() - 1);
        sheetSongMapper.delete(new SheetSongKey(songId, sheetId));
        sheetMapper.update(sheet);
    }

    //删除歌曲的同时删除歌单中的歌曲
    @Override
    @Transactional
    public void delSong(Long id) {
        List<SheetSongKey> list = sheetSongMapper.selectBySong(id);
        for (SheetSongKey item: list) {
            delFromSheet(id, item.getSheetid());
        }
    }

    @Override
    public List<Sheet> getRank() {
        List<Sheet> sheets = null;
        String rKey = "sheet:rank";
        if (!redisUtils.hasKey(rKey)) {
            sheets = sheetMapper.selectRank(Global.RANK_LENGTH);
            Set<ZSetOperations.TypedTuple<Object>> tuples
                    = new HashSet<ZSetOperations.TypedTuple<Object>>();
            for (Sheet sheet : sheets) {
                ZSetOperations.TypedTuple<Object> objectTypedTuple
                        = new DefaultTypedTuple<Object>(sheet, (double)sheet.getPlay());
                tuples.add(objectTypedTuple);
            }
            long len = redisUtils.zadds(rKey, tuples);
            Collections.sort(sheets, (s1, s2) -> s2.getPlay().compareTo(s1.getPlay()));
        }
        else {
            sheets = new ArrayList<>(redisUtils.zrevrange(rKey, 0, Global.RANK_LENGTH));
        }
        return sheets;
    }

    @Override
    public void updateRank() {
        String rKey = "sheet:rank";
        List<Sheet> sheets = sheetMapper.selectRank(Global.RANK_LENGTH);
        redisUtils.del(rKey);
        Set<ZSetOperations.TypedTuple<Object>> tuples
                = new HashSet<ZSetOperations.TypedTuple<Object>>();
        for (Sheet sheet : sheets) {
            ZSetOperations.TypedTuple<Object> objectTypedTuple
                    = new DefaultTypedTuple<Object>(sheet, (double)sheet.getPlay());
            tuples.add(objectTypedTuple);
        }
//        if (sheets.size() > 0) {
        redisUtils.zadds(rKey, tuples);
//        }
//        else {
//            redisUtils.del(rKey);
//        }
    }

    @Override
    public void incr(Long id) {
        String key = "sheet:" + id.toString() + ":incr";
        redisUtils.incr(key, 1);
    }

    @Override
    public void updatePlay() {
        Set<String> keys = redisUtils.keys("sheet:*:incr");
        List<Integer> values = redisUtils.mget(keys);
        int idx = 0;

        for (String key : keys) {
            int incr = values.get(idx++);
            Long id = Long.parseLong(key.substring(6, key.length()-5));
            sheetMapper.updateIncr(id, incr);
        }

        redisUtils.del(keys);
    }
}
