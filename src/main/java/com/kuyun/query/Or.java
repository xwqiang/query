package com.kuyun.query;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class Or implements Query {

    private final Query a;
    private final Query b;

    public Or(Query a,Query b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public Query not() {
        return new And(a.not(), b.not());
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
        return "\"or\":[" + valueA + "," + valueB + "]";
    }

}
