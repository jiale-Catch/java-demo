package com.jiale.test.easyRule;

import com.jiale.test.easyRule.rule.*;
import org.jeasy.rules.annotation.Fact;

import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

public class RuleTest {

    public static void main(String[] args) {
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        Facts  facts = new Facts();
        facts.put("rain",true);
        WeatherRule weatherRule = new WeatherRule();
        Rules rules = new Rules();
        rules.register(weatherRule);
        DefaultRulesEngine defaultRulesEngine = new DefaultRulesEngine(parameters);
        defaultRulesEngine.fire(rules,facts);
        System.out.println();
//        test();
    }

    public static void test(){
        // 创建规则引擎
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);

        // 创建规则
        Rules rules = new Rules();
        rules.register(new FizzRule());
        rules.register(new BuzzRule());
//        rules.register(new FizzBuzzRule(new FizzRule(), new BuzzRule()));
        rules.register(new NonFizzBuzzRule());

        // 触发规则
        Facts facts = new Facts();
        for (int i = 1; i <= 100; i++) {
            facts.put("number", i);
            fizzBuzzEngine.fire(rules, facts);
            System.out.println();
        }
    }
}
