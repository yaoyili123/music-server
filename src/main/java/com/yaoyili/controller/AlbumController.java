package com.yaoyili.controller;

import com.yaoyili.model.Album;
import com.yaoyili.service.AlbumService;
import com.yaoyili.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping(value = "/album/{id}")
    public ResultBean albumDetail(@PathVariable(value = "id") int id) {

        return new ResultBean<AlbumResponse>(albumService.findAlbum(id));
    }

    @GetMapping(value = "/albums/{aid}")
    public ResultBean findAlbumsByArtist(@PathVariable(value = "aid") int aid) {
        return new ResultBean<List<AlbumResponse>>(albumService.findAlbumsByArtist(aid));
    }

    @GetMapping(value = "/albums")
    public ResultBean findAlbumsByArtist(
            @RequestParam(value = "page", required = false, defaultValue = "-1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "-1") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "aid", required = false, defaultValue = "-1") int aid) {
        ResultBean res =  new ResultBean<List<AlbumResponse>>();
        res.setData(albumService.findAlbums(page, size, name, aid));
        if (aid == -1)
            res.setTotal(albumService.total());
        else
            res.setTotal(((List)res.getData()).size());
        return res;
    }

    @PostMapping(value = "/album/update")
    public ResultBean updateAlbum(@RequestBody Album album) {
        albumService.updateAlbum(album);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/album/add")
    public ResultBean addAlbum(@RequestBody Album album) {
        albumService.addAlbum(album);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/album/del/{id}")
    public ResultBean delAlbum(@PathVariable Integer id) {
        albumService.delAlbum(id);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/album/del")
    public ResultBean delAlbums(@RequestBody Integer[] ids) {
        for (int id: ids)
            albumService.delAlbum(id);
        return new ResultBean<Map>(new HashMap());
    }
}
