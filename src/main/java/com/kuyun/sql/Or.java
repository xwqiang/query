package com.kuyun.sql;

import com.kuyun.query.condition.Value;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class Or implements Query {

    private final Query a;
    private final Query b;
    private Opt opt;

    public Or(Query a, Opt opt, Query b) {
        this.a = a;
        this.opt = opt;
        this.b = b;
    }

    @Override
    public Query not() {
        return new And(a.not(), opt, b.not());
    }

    @Override
    public String toString() {
        String valueA = a.toString();
        if (!(a instanceof Value)) {
            valueA = "{" + a.toString() + "}";
        }
        String valueB = b.toString();
        if (!(b instanceof Value)) {
            valueB = "{" + b.toString() + "}";
        }
        return "\"or\":[" + valueA + "," + valueB + "]";
    }

}
