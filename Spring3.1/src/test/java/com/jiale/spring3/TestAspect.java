package com.jiale.spring3;

import com.jiale.spring3.moudle.testService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestAspect {
    @Autowired
    private testService testService;

    @Test
    public void test(){
       testService.testAround();
    }
}
