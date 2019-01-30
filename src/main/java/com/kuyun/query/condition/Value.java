package com.kuyun.query.condition;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class Value<T> extends Query {

    private final T value;

    public Value(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}