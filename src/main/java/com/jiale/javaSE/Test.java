package com.jiale.javaSE;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    private Object convert(Map<String ,Object> map , Class<?> clazz) {
        if(map.isEmpty()) {
            return null;
        }
        Object obj = null;
        //获取属性字段
        Field[] declaredFields = clazz.getDeclaredFields();
        try {
            for (Field f: declaredFields) {

                int modifiers = f.getModifiers();
                //如果是静态或者final的就跳过
                if(Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)){
                    continue;
                }

                f.setAccessible(true);
                Object o = map.get(camelToUnderline(f.getName()));
                //赋值
                f.set(obj,o);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
    private String camelToUnderline(String str) {
        Pattern PATTERN = Pattern.compile("[A-Z0-9]");
        Matcher matcher = PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    private String underlineToCamel(String param) {
        char UNICORN = '_';
        if (param==null || param.length()== 0) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = Character.toLowerCase(param.charAt(i));
            if (c == UNICORN) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
