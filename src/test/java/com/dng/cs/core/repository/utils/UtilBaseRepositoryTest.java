package com.dng.cs.core.repository.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UtilBaseRepositoryTest {

    @Autowired
    UtilBaseRepository utilBaseRepository;

    @Test
    void checkingCard() {
        boolean result = utilBaseRepository.checkingCard("378282246310005");
        assertTrue(result);
    }

    @Test
    void maskPan() {
        String result = utilBaseRepository.maskPan("378282246310005");
        assertEquals("378282246310005".length(), result.length()-1);
    }
}