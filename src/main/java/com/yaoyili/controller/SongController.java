package com.yaoyili.controller;

import com.yaoyili.model.Song;
import com.yaoyili.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping(value = "/songs")
    public ResultBean findAllSongs(
            @RequestParam(value = "page", required = false, defaultValue = "-1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "-1") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "aid", required = false, defaultValue = "-1") int aid) {
        ResultBean res = new ResultBean<List<SongResponse>>();
        res.setData(songService.findSongsAll(page, size, name, aid));
        if (aid == -1)
            res.setTotal(songService.total());
        else
            res.setTotal(((List)res.getData()).size());
        return res;
    }

    @GetMapping(value = "/songs/{id}")
    public ResultBean findSongs(@PathVariable(value = "id") int id,
                                @RequestParam(value = "type") String type) {
        return new ResultBean<List<SongResponse>>(songService.findSongs(id, type));
    }

    @PostMapping(value = "/song/update")
    public ResultBean updateSong(@RequestBody Song song) {
        songService.updateSong(song);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/song/add")
    public ResultBean addSong(@RequestBody Song song) {
        songService.addSong(song);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/song/del/{id}")
    public ResultBean delSong(@PathVariable Integer id) {
        songService.deleteSong(id);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/song/del")
    public ResultBean delSongs(@RequestBody Integer[] ids) {
        for (int id: ids)
            songService.deleteSong(id);
        return new ResultBean<Map>(new HashMap());
    }
}
