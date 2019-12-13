package com.yaoyili.controller;

import com.yaoyili.controller.resbeans.ResultBean;
import com.yaoyili.service.*;
import com.yaoyili.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private SongService songService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SheetService sheetService;

    @Autowired
    private MainService mainService;

    @PostMapping(value = "upload")
    public ResultBean uploadFile(@RequestParam(value = "file") MultipartFile pic) {
        mainService.savePicture(pic);
        Map map = new HashMap<String, String>();
        map.put("url", Global.HOST_NAME + '/' + pic.getOriginalFilename());
        return new ResultBean<Map>(map);
    }

    @GetMapping(value = "/search")
    public ResultBean findMusic(
            @RequestParam(value = "kw") String kw,
            @RequestParam(value = "limit", required = false, defaultValue = "-1") int limit) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("songs", songService.findSongbyName(kw, limit));
        res.put("sheets", sheetService.findSheetbyName(kw, limit));
        res.put("artists", artistService.findArtistbyName(kw, limit));
        res.put("albums", albumService.findAlbumbyName(kw, limit));

        return new ResultBean<Map>(res);
    }
}
