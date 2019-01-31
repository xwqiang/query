package com.kuyun.query.expression;


/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class Not extends Query {


    private Query a;

    public Not(Query a) {
        this.a = a;
    }

    private Not(Builder builder) {
        this.a = builder.a;
    }

    public void not(Query a) {
        this.a = a;
    }

    public Query getA() {
        return a;
    }

    public static class Builder {

        private Query a;

        public Builder() {
        }

        public Builder(Query a) {
            this.a = a;
        }

        public Builder(String a) {
            this.a = new Value(a);
        }

        public Builder not(Query a) {
            this.a = a;
            return this;
        }

        public Not build() {
            return new Not(this);
        }
    }
}
