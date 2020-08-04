package com.yaoyili.controller;

import com.yaoyili.controller.ao.ResultBean;
import com.yaoyili.model.Artist;
import com.yaoyili.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping(value = "/{id}")
    public ResultBean find(@PathVariable(value = "id") Long id) {
        return new ResultBean<Artist>(artistService.find(id));
    }

    @GetMapping(value = "/cols")
    public ResultBean findCollections(@RequestParam(value = "uid") Long uid) {
        return new ResultBean<List<Artist>>(artistService.findCollections(uid));
    }

    @GetMapping(value = "/all")
    public ResultBean find(
            @RequestParam(value = "page", required = false, defaultValue = "-1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "-1") int size,
            @RequestParam(value = "name", required = false) String name
            ) {
        ResultBean res =  new ResultBean<List<Artist>>();
        res.setData(artistService.find(page, size, name));
        res.setTotal(artistService.total());
        return res;
    }

    @PostMapping(value = "/update")
    public ResultBean update(@RequestBody Artist artist) {
        artistService.update(artist);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/add")
    public ResultBean add(@RequestBody Artist artist) {
        artistService.add(artist);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/del/{id}")
    public ResultBean del(@PathVariable Long id) {
        artistService.del(id);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/dels")
    public ResultBean del(@RequestBody Long[] ids) {
        for (Long id: ids)
            artistService.del(id);
        return new ResultBean<Map>(new HashMap());
    }
}
