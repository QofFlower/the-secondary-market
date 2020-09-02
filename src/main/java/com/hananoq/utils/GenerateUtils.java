package com.hananoq.utils;

import java.util.Random;

/**
 * @author :花のQ
 * @since 2020/8/10 21:57
 **/
public class GenerateUtils {

    private static String digit = "0123456789";
    private static Random random = new Random();

    public static String randomOrderId() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(digit.charAt(random.nextInt(10)));
        }
        return builder.toString().startsWith("0") ? randomOrderId() : builder.toString();
    }

}
