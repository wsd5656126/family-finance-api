package com.wusd.familyfinanceapi.mapper.gen;

import com.alibaba.fastjson.JSON;
import com.wusd.familyfinanceapi.FamilyFinanceApiApplicationTests;
import com.wusd.familyfinanceapi.entity.gen.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends FamilyFinanceApiApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.print(JSON.toJSONString(user));
    }
}