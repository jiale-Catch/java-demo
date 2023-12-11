package com.jiale.test.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Match {
    //正则表达式的部分使用
    public static void main(String[] args) {
        //匹配1个数字
        Pattern pattern =Pattern.compile("\\d{0,1}");
        String str = "1afbs2ad";
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
    }
}
