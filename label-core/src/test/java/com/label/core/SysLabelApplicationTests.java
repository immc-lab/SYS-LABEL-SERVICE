package com.label.core;

import com.label.core.mapper.UserAccountMapper;
import com.label.core.pojo.entity.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SysLabelApplicationTests {
    @Autowired
    private UserAccountMapper mapper;
    @Test
    void contextLoads() {
        List<UserAccount> users = mapper.searchUser("admin");
        System.out.println(users);
//
    }

}
