package com.baidu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mayongbin01 on 2017/3/6.
 */
public class Test {
    public static void main(String[] args) {
        String a="love23next234_c-sdn3423ja*vaeye";
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(a);
        System.out.println( m.replaceAll("").trim());
    }
}
