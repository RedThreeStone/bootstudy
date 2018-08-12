package it.lei.day1.entity;

public class Room {
    private Integer roomId;

    private String rookmRank;

    private String roomName;

    private Integer cinemaId;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRookmRank() {
        return rookmRank;
    }

    public void setRookmRank(String rookmRank) {
        this.rookmRank = rookmRank == null ? null : rookmRank.trim();
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }
}