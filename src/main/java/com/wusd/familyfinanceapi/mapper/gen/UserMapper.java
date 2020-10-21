package com.wusd.familyfinanceapi.mapper.gen;

import com.wusd.familyfinanceapi.entity.gen.User;
import com.wusd.familyfinanceapi.entity.gen.UserExample;
import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}