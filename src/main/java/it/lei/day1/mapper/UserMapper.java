package it.lei.day1.mapper;

import it.lei.day1.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer ssmUserId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer ssmUserId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUsername(@Param("username") String username);

}