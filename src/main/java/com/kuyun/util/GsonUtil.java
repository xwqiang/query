package com.kuyun.util;

import com.google.gson.Gson;

/**
 * Created by xuwuqiang on 2019/1/31.
 */
public class GsonUtil {

    private static Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }
    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

}
