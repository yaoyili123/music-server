package com.yaoyili.controller;

import com.yaoyili.controller.resbeans.ResultBean;
import com.yaoyili.controller.resbeans.SheetResponse;
import com.yaoyili.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SheetController {

    @Autowired
    private SheetService sheetService;

    @PostMapping(value = "addSheet")
    public ResultBean addSheet(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "uid") int uid)
    {
        return new ResultBean<SheetResponse>(sheetService.addSheet(name, uid));
    }

    @GetMapping(value = "/sheets/{uid}")
    public ResultBean findSheets(@PathVariable(value = "uid") int uid) {
        return new ResultBean<List<SheetResponse>>(sheetService.findSheets(uid));
    }


    @GetMapping(value = "/sheet/{sid}")
    public ResultBean findSheet(@PathVariable(value = "sid") int sid) {
        return new ResultBean<SheetResponse>(sheetService.findSheet(sid));
    }

    @GetMapping(value = "/sheet/collections")
    public ResultBean findCollections(@RequestParam(value = "uid") int uid) {
        return new ResultBean<List<SheetResponse>>(sheetService.findCollections(uid));
    }

    @GetMapping(value = "/checkSong")
    public ResultBean checkSong(
            @RequestParam(value = "songid") int songid,
            @RequestParam(value = "sheetid") int sheetid) {
        return new ResultBean<Boolean>(sheetService.checkSong(songid, sheetid));
    }



    @PostMapping(value = "updateSheet")
    public ResultBean updateSheet(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            @RequestParam(value = "pic", required = false) MultipartFile pic)
    {
        sheetService.updateSheet(id, name, description, pic);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "deleteSheet/{sid}")
    public ResultBean deleteSheet(@PathVariable(value = "sid") int sid) {
        sheetService.deleteSheet(sid);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/song/addsheet")
    public ResultBean addSongToSheet(
            @RequestParam(value = "songId") int songId,
            @RequestParam(value = "sheetId") int sheetId)
    {
        sheetService.addSongToSheet(songId, sheetId);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/song/delsheet")
    public ResultBean deleteSongFromSheet(
            @RequestParam(value = "songId") int songId,
            @RequestParam(value = "sheetId") int sheetId)
    {
        sheetService.deleteSongFromSheet(songId, sheetId);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/sheet/play")
    public ResultBean incr(@RequestParam(value = "id") int id) {
        sheetService.incr(id);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/sheet/rank")
    public ResultBean getRank() {
        return new ResultBean<List>(sheetService.getRank());
    }
}
