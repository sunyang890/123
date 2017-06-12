package cn.edu.neusoft.myapplication;

/**
 * Created by 46749 on 2017/6/2.
 */

public class Music {
    private String name;//歌名
    private String artist;//作者
    private String path;//路径

    private String duration;//音长
    private String album;//唱片

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
