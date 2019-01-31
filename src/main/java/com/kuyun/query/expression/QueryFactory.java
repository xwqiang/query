package com.kuyun.query.expression;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class QueryFactory {

    public static Query and(String a, String b, String... c) {
        And and = new And(new Value(a), new Value(b));
        if (c != null && c.length > 0) {
            for (String s : c) {
                and.more(new Value(s));
            }
        }
        return and;
    }

    public static Query or(String a, String b, String... c) {
        Or or = new Or(new Value(a), new Value(b));
        if (c != null && c.length > 0) {
            for (String s : c) {
                or.more(new Value(s));
            }
        }
        return or;
    }

    public static Query not(String a) {
        return new Not(new Value(a));
    }

    public static Query and(Query a, Query b, Query... c) {
        return new And(a, b, c);
    }

    public static Query or(Query a, Query b, Query... c) {
        return new Or(a, b, c);
    }

    public static Query not(Query a) {
        return new Not(a);
    }
}
