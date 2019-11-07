package com.yaoyili.service.impl;

import com.yaoyili.controller.CheckException;
import com.yaoyili.controller.SongResponse;
import com.yaoyili.dao.AlbumMapper;
import com.yaoyili.dao.ArtistMapper;
import com.yaoyili.dao.SongMapper;
import com.yaoyili.model.Album;
import com.yaoyili.model.Artist;
import com.yaoyili.model.Song;
import com.yaoyili.service.SongService;
import com.yaoyili.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public int total() {
        return songMapper.total();
    }

    @Override
    public List<SongResponse> findSongs(int id, String type) {
        if (!type.equals("album") && !type.equals("artist") && !type.equals("sheet"))
            throw new CheckException("type参数错误");
        if (type.equals("artist"))
            return songMapper.selectByArtist(id);
        else if (type.equals("album"))
            return songsConvert(songMapper.selectByAlbum(id));
        else
            return songsConvert(songMapper.selectBySheet(id));
    }

    @Override
    public List<SongResponse> findSongsAll(int page, int size, String name, int aid) {
        Global.checkPageParams(page, size);
        if (page == -1)
            return songsConvert(songMapper.selectAll(-1, -1, name, aid));
        else {
            int offset = (page - 1) * size;
            return songsConvert(songMapper.selectAll(offset, size, name, aid));
        }
    }

    @Override
    public List<SongResponse> findSongbyName(String name, int limit) {
        List<SongResponse> responses = songsConvert(songMapper.selectByName(name));

        if (limit == -1 || limit > responses.size())
            return responses;
        else
            return responses.subList(0, limit);
    }

    @Override
    public void addSong(Song song) {
        songMapper.insertSelective(song);
    }

    @Override
    public void updateSong(Song song) {
        songMapper.updateByPrimaryKeySelective(song);
    }

    @Override
    public void deleteSong(int id) {
        songMapper.deleteByPrimaryKey(id);
    }

    private List<SongResponse> songsConvert(List<Song> songs) {
        List<SongResponse> responses = new ArrayList<>();

        for(Song song: songs) {
            Album album = albumMapper.selectByPrimaryKey(song.getAlbumId());
            Artist artist = artistMapper.selectByPrimaryKey(album.getArtistId());
            SongResponse response = new SongResponse(song, album.getName(), album.getPicUrl(),artist.getId(), artist.getName());
            responses.add(response);
        }

        return responses;
    }
}
