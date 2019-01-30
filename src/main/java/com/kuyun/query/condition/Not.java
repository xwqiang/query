package com.kuyun.query.condition;


/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class Not extends Query {


    private final Query a;

    public Not(Query a) {
        this.a = a;
    }

    public Query getA() {
        return a;
    }
}
