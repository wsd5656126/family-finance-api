package com.wusd.familyfinanceapi.mapper.gen;

import com.wusd.familyfinanceapi.entity.gen.Authority;
import com.wusd.familyfinanceapi.entity.gen.AuthorityExample;
import java.util.List;

public interface AuthorityMapper {
    long countByExample(AuthorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Authority record);

    int insertSelective(Authority record);

    List<Authority> selectByExample(AuthorityExample example);

    Authority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
}