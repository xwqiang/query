package com.kuyun.client;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.expression.And;
import com.kuyun.query.expression.Not;
import com.kuyun.query.expression.Or;
import com.kuyun.query.expression.Query;
import com.kuyun.query.expression.Value;
import com.kuyun.query.visitor.BooleanVisitor;
import com.kuyun.query.visitor.Visitor;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class BooleanTest {

    public static void main(String[] args) {

        Query not = new Not.Builder("true").build();

        Query or = new Or.Builder("true","false").build();

        Query and = new And(not, or);

        Visitor<Boolean> v = new BooleanVisitor();

        boolean value = and.accept(v);

        System.out.println(value);

    }

}
