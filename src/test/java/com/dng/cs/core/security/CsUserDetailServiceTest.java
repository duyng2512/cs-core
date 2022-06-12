package com.dng.cs.core.security;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
class CsUserDetailServiceTest {

    @Autowired
    CsUserDetailService csUserDetailService;

    @Test
    void loadUserByUsername() {
        UserDetails userDetails = csUserDetailService.loadUserByUsername("duyng@gmail.com");
        assertNotNull(userDetails);
        System.out.println(userDetails);
    }
}