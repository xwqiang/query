package com.kuyun.query.visitor;

import com.kuyun.query.expression.And;
import com.kuyun.query.expression.Not;
import com.kuyun.query.expression.Or;
import com.kuyun.query.expression.Query;
import com.kuyun.query.expression.Value;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class BooleanVisitor extends Visitor<Boolean> {

    IConvertor convertor;

    public BooleanVisitor(IConvertor iConvertor) {
        this.convertor = iConvertor;
    }

    public BooleanVisitor() {
        this.convertor = new DefaultBooleanConvertor();
    }

    @Override
    public Boolean visit(And and) {
        for (Query q : and.queries()) {
            if (!q.accept(this)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean visit(Or or) {
        for (Query q : or.queries()) {
            if (q.accept(this)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean visit(Not not) {
        return !not.getA().accept(this);
    }

    @Override
    public Boolean visit(Value value) {
        return convertor.convert(value);
    }
}
