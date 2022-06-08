package com.dng.cs.core.adhoc;

import com.dng.cs.core.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IntegrateTest {

    @Autowired
    ClientService clientService;

    @Test
    public void givenClientId_validState_returnNearestAddress(){
        clientService.getNearestAddress(100L);
    }

}
