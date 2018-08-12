package it.lei.day1.mapper;

import it.lei.day1.entity.Schedule;

public interface ScheduleMapper {
    int deleteByPrimaryKey(Integer scheduleId);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(Integer scheduleId);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);
}