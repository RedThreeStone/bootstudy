package it.lei.day1.entity;

import java.util.Date;

public class Movie {
    private Integer movieId;

    private String movieName;

    private String movieMainrole;

    private String movieDirector;

    private String movieInfo;

    private String movieNoticeUrl;

    private Date moviePremiereDay;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName == null ? null : movieName.trim();
    }

    public String getMovieMainrole() {
        return movieMainrole;
    }

    public void setMovieMainrole(String movieMainrole) {
        this.movieMainrole = movieMainrole == null ? null : movieMainrole.trim();
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector == null ? null : movieDirector.trim();
    }

    public String getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(String movieInfo) {
        this.movieInfo = movieInfo == null ? null : movieInfo.trim();
    }

    public String getMovieNoticeUrl() {
        return movieNoticeUrl;
    }

    public void setMovieNoticeUrl(String movieNoticeUrl) {
        this.movieNoticeUrl = movieNoticeUrl == null ? null : movieNoticeUrl.trim();
    }

    public Date getMoviePremiereDay() {
        return moviePremiereDay;
    }

    public void setMoviePremiereDay(Date moviePremiereDay) {
        this.moviePremiereDay = moviePremiereDay;
    }
}