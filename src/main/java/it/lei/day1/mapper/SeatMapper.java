package it.lei.day1.mapper;

import it.lei.day1.entity.Seat;

public interface SeatMapper {
    int deleteByPrimaryKey(Integer seatId);

    int insert(Seat record);

    int insertSelective(Seat record);

    Seat selectByPrimaryKey(Integer seatId);

    int updateByPrimaryKeySelective(Seat record);

    int updateByPrimaryKey(Seat record);
}