package com.cloud.cloudcomputation.util;

import java.lang.reflect.Field;

public class FieldUtils {
    public static void setAttribute(Object o,String field, Object keyWord) {
        try {
            Field f = o.getClass().getDeclaredField(field);
            f.setAccessible(true);
            f.set(o, keyWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAttribute(Object o,String field) {
        String r = "";
        try {
            Field f = o.getClass().getDeclaredField(field);
            f.setAccessible(true);
            r =  f.get(o).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
