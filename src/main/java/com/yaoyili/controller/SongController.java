package com.yaoyili.controller;

import com.yaoyili.controller.ao.ResultBean;
import com.yaoyili.controller.ao.SongLyric;
import com.yaoyili.model.Song;
import com.yaoyili.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping(value = "/all")
    public ResultBean find(
            @RequestParam(value = "page", required = false, defaultValue = "-1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "-1") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "aid", required = false, defaultValue = "-1") Long aid) {
        ResultBean res = new ResultBean<List<Song>>();
        res.setData(songService.find(page, size, name, aid));
        if (aid == -1)
            res.setTotal(songService.total());
        else
            res.setTotal(((List)res.getData()).size());
        return res;
    }

    @GetMapping(value = "/{id}")
    public ResultBean find(@PathVariable(value = "id") Long id) {
        return new ResultBean<Song>(songService.find(id));
    }

    @GetMapping(value = "/set/{id}")
    public ResultBean find(@PathVariable(value = "id") Long id,
                                @RequestParam(value = "type") String type) {
        return new ResultBean<List<Song>>(songService.find(id, type));
    }

    @PostMapping(value = "/update")
    public ResultBean update(@RequestBody SongLyric song) {
        songService.update(song);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/add")
    public ResultBean add(@RequestBody SongLyric song) {
        songService.add(song);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/del/{id}")
    public ResultBean del(@PathVariable Long id) {
        songService.del(id);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/dels")
    public ResultBean delSongs(@RequestBody Long[] ids) {
        for (Long id: ids)
            songService.del(id);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/lyric/{id}")
    public ResultBean findLyric(@PathVariable(value = "id") Long id) {
        Map<String, Object> maps = new HashMap<>();
        String lyric = songService.findLyric(id);
        maps.put("lyric", lyric);
        maps.put("id", id);
        return new ResultBean<Map>(maps);
    }
}
