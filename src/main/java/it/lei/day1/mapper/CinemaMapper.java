package it.lei.day1.mapper;

import it.lei.day1.entity.Cinema;

public interface CinemaMapper {
    int deleteByPrimaryKey(Integer cinemaId);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer cinemaId);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);
}