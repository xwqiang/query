package com.kuyun.query.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xuwuqiang on 2019/1/30.
 */
public class BinaryQuery extends Query {

    protected Query a;
    protected Query b;
    protected Query[] c;


    public BinaryQuery(Query a, Query b, Query... c) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public BinaryQuery(Query a, Query b) {
        super();
        this.a = a;
        this.b = b;
    }

    protected BinaryQuery() {
        super();
    }


    protected static Query[] merge(Query[] c, Query... more) {
        if (c == null || c.length == 0) {
            return more;
        }
        if (more != null && more.length > 0) {
            List<Query> queryList = new ArrayList(Arrays.asList(more));
            queryList.addAll(Arrays.asList(c));
            Query[] result = new Query[queryList.size()];
            queryList.toArray(result);
            return result;
        }
        return null;
    }

    public void more(Query... more) {
        this.c = merge(this.c, more);
    }

    public List<Query> queries() {

        List<Query> querys = new ArrayList();
        if (a != null) {
            querys.add(a);
        }
        if (b != null) {
            querys.add(b);
        }
        if (c != null && c.length > 0) {
            querys.addAll(Arrays.asList(c));
        }
        return querys;
    }


}
