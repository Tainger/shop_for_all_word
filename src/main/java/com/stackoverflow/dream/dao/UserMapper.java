package com.stackoverflow.dream.dao;

import com.stackoverflow.dream.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author rocky
 */
@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUserName(String userName);

    User findUser(@Param("userName") String userName, @Param("password")String md5password);

    int checkEmail(String email);

    User findUserByUserName(String userName);
}