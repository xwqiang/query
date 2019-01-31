package com.kuyun.query.visitor;

import com.kuyun.query.expression.And;
import com.kuyun.query.expression.Not;
import com.kuyun.query.expression.Or;
import com.kuyun.query.expression.Query;
import com.kuyun.query.expression.Value;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public abstract class Visitor<R> {


    public R visit(Query query) throws Exception {
        throw new IllegalAccessException("未实现的接口");
    }

    public abstract R visit(And and);

    public abstract R visit(Or or);

    public abstract R visit(Not not);

    public abstract R visit(Value value);

}
