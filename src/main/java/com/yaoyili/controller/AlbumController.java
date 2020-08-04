package com.yaoyili.controller;

import com.yaoyili.controller.ao.ResultBean;
import com.yaoyili.model.Album;
import com.yaoyili.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping(value = "/{id}")
    public ResultBean find(@PathVariable(value = "id") Long id) {

        return new ResultBean<Album>(albumService.find(id));
    }

    @GetMapping(value = "/artist/{aid}")
    public ResultBean findByArtist(@PathVariable(value = "aid") Long aid) {
        return new ResultBean<List<Album>>(albumService.findByArtist(aid));
    }

    @GetMapping(value = "/all")
    public ResultBean find(
            @RequestParam(value = "page", required = false, defaultValue = "-1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "-1") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "aid", required = false, defaultValue = "-1") Long aid) {
        ResultBean res =  new ResultBean<List<Album>>();
        res.setData(albumService.find(page, size, name, aid));
        if (aid == -1)
            res.setTotal(albumService.total());
        else
            res.setTotal(((List)res.getData()).size());
        return res;
    }

    @GetMapping(value = "/cols")
    public ResultBean findCollections(@RequestParam(value = "uid") Long uid) {
        return new ResultBean<List<Album>>(albumService.findCollections(uid));
    }

    @PostMapping(value = "/update")
    public ResultBean update(@RequestBody Album album) {
        albumService.update(album);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/add")
    public ResultBean add(@RequestBody Album album) {
        albumService.add(album);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/del/{id}")
    public ResultBean del(@PathVariable Long id) {
        albumService.del(id);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/dels")
    public ResultBean del(@RequestBody Long[] ids) {
        for (Long id: ids)
            albumService.del(id);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/play")
    public ResultBean incr(@RequestParam(value = "id") Long id) {
        albumService.incr(id);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/rank")
    public ResultBean getRank() {
        return new ResultBean<List>(albumService.getRank());
    }
}
