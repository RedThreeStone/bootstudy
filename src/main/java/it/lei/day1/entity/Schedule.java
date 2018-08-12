package it.lei.day1.entity;

import java.util.Date;

public class Schedule {
    private Integer scheduleId;

    private Date scheduleTime;

    private Integer movieId;

    private Double schedulePrice;

    private Integer roomId;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Double getSchedulePrice() {
        return schedulePrice;
    }

    public void setSchedulePrice(Double schedulePrice) {
        this.schedulePrice = schedulePrice;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}