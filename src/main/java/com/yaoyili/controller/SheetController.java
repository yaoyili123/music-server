package com.yaoyili.controller;

import com.yaoyili.controller.ao.ResultBean;
import com.yaoyili.model.Sheet;
import com.yaoyili.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/sheet")
public class SheetController {

    @Autowired
    private SheetService sheetService;

    @PostMapping(value = "add")
    public ResultBean add(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "uid") Long uid,
            @RequestParam(value = "username") String username)
    {
        return new ResultBean<Sheet>(sheetService.add(name, uid, username));
    }

    @GetMapping(value = "/user/{uid}")
    public ResultBean findByUid(@PathVariable(value = "uid") Long uid) {
        return new ResultBean<List<Sheet>>(sheetService.findByUid(uid));
    }


    @GetMapping(value = "/{sid}")
    public ResultBean find(@PathVariable(value = "sid") Long sid) {
        return new ResultBean<Sheet>(sheetService.find(sid));
    }

    @GetMapping(value = "/cols")
    public ResultBean findCollections(@RequestParam(value = "uid") Long uid) {
        return new ResultBean<List<Sheet>>(sheetService.findCollections(uid));
    }

    @GetMapping(value = "/checkSong")
    public ResultBean checkSong(
            @RequestParam(value = "songid") Long songid,
            @RequestParam(value = "sheetid") Long sheetid) {
        return new ResultBean<Boolean>(sheetService.checkSong(songid, sheetid));
    }



    @PostMapping(value = "update")
    public ResultBean update(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            @RequestParam(value = "pic", required = false) MultipartFile pic)
    {
        sheetService.update(id, name, description, pic);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "del/{sid}")
    public ResultBean del(@PathVariable(value = "sid") Long sid) {
        sheetService.del(sid);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/addSong")
    public ResultBean addToSheet(
            @RequestParam(value = "songId") Long songId,
            @RequestParam(value = "sheetId") Long sheetId)
    {
        sheetService.addToSheet(songId, sheetId);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/delSong")
    public ResultBean delFromSheet(
            @RequestParam(value = "songId") Long songId,
            @RequestParam(value = "sheetId") Long sheetId)
    {
        sheetService.delFromSheet(songId, sheetId);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/play")
    public ResultBean incr(@RequestParam(value = "id") Long id) {
        sheetService.incr(id);
        return new ResultBean<Map>(new HashMap());
    }

    @GetMapping(value = "/rank")
    public ResultBean getRank() {
        return new ResultBean<List>(sheetService.getRank());
    }
}
