package it.lei.day1.entity;

public class Cinema {
    private Integer cinemaId;

    private Double cinemaArea;

    private String cinemaPosition;

    private String cinemaName;

    private String cinemaOwner;

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Double getCinemaArea() {
        return cinemaArea;
    }

    public void setCinemaArea(Double cinemaArea) {
        this.cinemaArea = cinemaArea;
    }

    public String getCinemaPosition() {
        return cinemaPosition;
    }

    public void setCinemaPosition(String cinemaPosition) {
        this.cinemaPosition = cinemaPosition == null ? null : cinemaPosition.trim();
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName == null ? null : cinemaName.trim();
    }

    public String getCinemaOwner() {
        return cinemaOwner;
    }

    public void setCinemaOwner(String cinemaOwner) {
        this.cinemaOwner = cinemaOwner == null ? null : cinemaOwner.trim();
    }
}