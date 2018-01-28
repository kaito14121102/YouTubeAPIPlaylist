package com.anime.rezero.youtubeapiplaylist;

/**
 * Created by zing on 1/26/2018.
 */

public class VideoYouTube {
    private String Title;
    private String Thumnails;
    private String IdVideo;

    public VideoYouTube(String title, String thumnails, String idVideo) {
        Title = title;
        Thumnails = thumnails;
        IdVideo = idVideo;
    }

    public VideoYouTube() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumnails() {
        return Thumnails;
    }

    public void setThumnails(String thumnails) {
        Thumnails = thumnails;
    }

    public String getIdVideo() {
        return IdVideo;
    }

    public void setIdVideo(String idVideo) {
        IdVideo = idVideo;
    }
}
