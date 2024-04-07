package com.jiale.test.easyRule.rule;


import org.jeasy.rules.annotation.*;
@Rule
public class NonFizzBuzzRule {

    @Condition
    public boolean isNotFizzNorBuzz(@Fact("number") Integer number) {
        return number % 5 != 0 || number % 7 != 0;
    }

    @Action
    public void printInput(@Fact("number") Integer number) {
        System.out.print(number);
    }

    @Priority
    public int getPriority() {
        return 3;
    }
}
