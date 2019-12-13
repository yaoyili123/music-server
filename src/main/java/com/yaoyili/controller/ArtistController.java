package com.yaoyili.controller;

import com.yaoyili.controller.resbeans.ResultBean;
import com.yaoyili.model.Artist;
import com.yaoyili.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping(value = "/artist/{id}")
    public ResultBean artistDetail(@PathVariable(value = "id") int id) {
        return new ResultBean<Artist>(artistService.findAritist(id));
    }

    @GetMapping(value = "/artist/collections")
    public ResultBean findCollections(@RequestParam(value = "uid") int uid) {
        return new ResultBean<List<Artist>>(artistService.findCollections(uid));
    }

    @GetMapping(value = "/artists")
    public ResultBean getArtists(
            @RequestParam(value = "page", required = false, defaultValue = "-1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "-1") int size,
            @RequestParam(value = "name", required = false) String name
            ) {
        ResultBean res =  new ResultBean<List<Artist>>();
        res.setData(artistService.findArtistsAll(page, size, name));
        res.setTotal(artistService.total());
        return res;
    }

    @PostMapping(value = "/artist/update")
    public ResultBean updateArtist(@RequestBody Artist artist) {
        artistService.updateArtist(artist);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/artist/add")
    public ResultBean addArtist(@RequestBody Artist artist) {
        artistService.addArtist(artist);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/artist/del/{id}")
    public ResultBean delAritist(@PathVariable Integer id) {
        artistService.deleteArtist(id);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/artist/del")
    public ResultBean delAritists(@RequestBody Integer[] ids) {
        for (int id: ids)
            artistService.deleteArtist(id);
        return new ResultBean<Map>(new HashMap());
    }
}
