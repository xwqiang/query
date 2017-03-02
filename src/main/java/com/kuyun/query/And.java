package com.kuyun.query;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class And implements Query {

    private final Query a;
    private final Query b;

    public And(Query a, Query b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public Query not() {
        return new Or(a.not(), b.not());
    }
    @Override
    public String toString() {
        String valueA = a.toString();
        if(!(a instanceof Value)){
            valueA = "{" + a.toString() + "}";
        }
        String valueB = b.toString();
        if(!(b instanceof Value)){
            valueB = "{" + b.toString() + "}";
        }
        return "\"and\":[" + valueA + "," + valueB + "]";
    }

}
