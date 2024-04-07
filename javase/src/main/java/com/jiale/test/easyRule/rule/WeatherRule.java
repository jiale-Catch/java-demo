package com.jiale.test.easyRule.rule;

import org.jeasy.rules.annotation.*;

@Rule()
public class WeatherRule {
    @Condition
    public boolean itRains(@Fact("rain") boolean fact){
        return fact;
    }
    @Action
    public void takeAnUmbrella(){
        System.out.println("It rains, take an umbrella!");
    }
    @Priority
    public int getPriority() {
        return 3;
    }
}
