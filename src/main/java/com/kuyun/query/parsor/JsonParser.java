package com.kuyun.query.parsor;

import com.google.gson.Gson;

/**
 * Created by xuwuqiang on 2019/1/31.
 */
public class JsonParser {

    private Gson gson = new Gson();

    public <T> T parse(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }

}
