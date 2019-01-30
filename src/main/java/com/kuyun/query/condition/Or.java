package com.kuyun.query.condition;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class Or extends BinaryQuery {

    public Or(Query a, Query b, Query... c) {
        super(a, b, c);
    }

    public Or(Query a, Query b) {
        super(a, b);
    }

    private Or(Builder builder) {
        super();
        super.a = builder.a;
        super.b = builder.b;
        super.c = builder.c;
    }

    public static class Builder {

        private Query a;
        private Query b;
        private Query[] c;

        public Builder() {
        }

        public Builder(Query a, Query b) {
            this.a = a;
            this.b = b;
        }

        public Builder(Query a, Query b, Query... c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Builder more(Query... c) {
            this.c = BinaryQuery.merge(this.c, c);
            return this;
        }

        public Or build() {
            return new Or(this);
        }
    }
}