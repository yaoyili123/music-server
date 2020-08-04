package com.yaoyili.service.impl;

import com.yaoyili.CheckException;
import com.yaoyili.controller.ao.SongLyric;
import com.yaoyili.dao.*;
import com.yaoyili.model.Lyric;
import com.yaoyili.model.Sheet;
import com.yaoyili.model.Song;
import com.yaoyili.service.SheetService;
import com.yaoyili.service.SongService;
import com.yaoyili.utils.Global;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.yaoyili.utils.CheckUtils.*;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private SheetService sheetService;

    @Autowired
    private SheetSongMapper sheetSongMapper;

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private LyricMapper lyricMapper;


    @Override
    public int total() {
        return songMapper.total();
    }

    @Override
    public Song find(Long id) {
        return songMapper.select(id);
    }

    @Override
    public List<Song> find(Long id, String type) {
        if (!type.equals("album") && !type.equals("artist") && !type.equals("sheet"))
            throw new CheckException("type参数错误");
        if (type.equals("artist"))
            return songMapper.selectByArtist(id);
        else if (type.equals("album"))
            return songMapper.selectByAlbum(id);
        else
            return songMapper.selectBySheet(id);
    }

    @Override
    public List<Song> find(int page, int size, String name, Long aid) {
        checkPageParams(page, size);
        if (page == -1)
            return songMapper.selectAll(-1, -1, name, aid);
        else {
            int offset = (page - 1) * size;
            return songMapper.selectAll(offset, size, name, aid);
        }
    }

    @Override
    public List<Song> findbyName(String name, int limit) {
        List<Song> responses = songMapper.selectByName(name);

        if (limit == -1 || limit > responses.size())
            return responses;
        else
            return responses.subList(0, limit);
    }

    @Override
    @Transactional
    public void add(SongLyric song) {

        notNull(song, "未知错误");
        notEmpty(song.getName(), "歌曲名称不能为空");
        notEmpty(song.getAlbumName(), "专辑名为空！");
        notEmpty(song.getAuthor(), "作者名为空！");
        check(song.getArtistId() > 0, "作者ID非法！");
        check(song.getAlbumId() > 0, "专辑ID非法！");
        check(song.getName().length() <= 200, "名称长度不能超过200");

        Song data = new Song();
        BeanUtils.copyProperties(song, data);
        songMapper.insert(data);
        lyricMapper.insert(new Lyric(data.getId(), song.getLyric()));
    }

    @Override
    @Transactional
    public void update(SongLyric song) {

        notNull(song, "未知错误");
        notEmpty(song.getName(), "歌曲名称不能为空");
        notEmpty(song.getAlbumName(), "专辑名为空！");
        notEmpty(song.getAuthor(), "作者名为空！");
        check(song.getArtistId() > 0, "作者ID非法！");
        check(song.getAlbumId() > 0, "专辑ID非法！");
        check(song.getName().length() <= 200, "名称长度不能超过200");

        Song data = new Song();
        BeanUtils.copyProperties(song, data);
        songMapper.update(data);
        lyricMapper.update(new Lyric(song.getId(), song.getLyric()));
    }

    @Override
    @Transactional
    public void del(Long id) {
        songMapper.delete(id);
        sheetService.delSong(id);
        lyricMapper.delete(id);
    }

    @Override
    @Transactional
    public void delByAlbum(Long aid) {
        List<Song> songs = songMapper.selectByAlbum(aid);
        for (Song item : songs) {
            del(item.getId());
        }
    }

    @Override
    public String findLyric(Long id) {
        Lyric lyric =  lyricMapper.select(id);
        if (lyric == null)
            return null;
        else if (lyric.getLyric() == null)
            return null;
        else
            return lyric.getLyric();
    }
}
