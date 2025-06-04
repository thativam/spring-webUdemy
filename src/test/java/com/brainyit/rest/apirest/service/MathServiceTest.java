package com.brainyit.rest.apirest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MathServiceTest {

    @Autowired
    private MathService mathService;

    @Test
    void shouldSumParameters() {
        Double v1 = 2.0;
        Double v2 = 5.0;
        Double expectedValue = 7.0;
        Double result = mathService.add(v1,v2);
        assertEquals( expectedValue, result);
    }

}