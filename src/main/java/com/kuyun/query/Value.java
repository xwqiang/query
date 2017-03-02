package com.kuyun.query;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class Value implements Query {
    private final String value;

    public Value(String value) {
        this.value = value;
    }

    @Override
    public Query not() {
        if (value.endsWith("!")) {
            return new Value(value.substring(0, value.length() - 1));
        } else {
            return new Value(value + "!");
        }
    }
    @Override
    public String toString() {
        return "\"" + value + "\"";
    }

}