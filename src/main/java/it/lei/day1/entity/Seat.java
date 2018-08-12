package it.lei.day1.entity;

public class Seat {
    private Integer seatId;

    private Integer seatRownum;

    private Integer seatRank;

    private String seatIsActive;

    private Integer scheduleId;

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getSeatRownum() {
        return seatRownum;
    }

    public void setSeatRownum(Integer seatRownum) {
        this.seatRownum = seatRownum;
    }

    public Integer getSeatRank() {
        return seatRank;
    }

    public void setSeatRank(Integer seatRank) {
        this.seatRank = seatRank;
    }

    public String getSeatIsActive() {
        return seatIsActive;
    }

    public void setSeatIsActive(String seatIsActive) {
        this.seatIsActive = seatIsActive == null ? null : seatIsActive.trim();
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
}