package com.kuyun.client;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.expression.And;
import com.kuyun.query.expression.Not;
import com.kuyun.query.expression.Or;
import com.kuyun.query.expression.Query;
import com.kuyun.query.visitor.BooleanVisitor;
import com.kuyun.query.visitor.UserBooleanConvertor;
import com.kuyun.query.visitor.Visitor;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class BooleanTest {

    public static void main(String[] args) {

        Visitor<Boolean> simpleVisitor = new BooleanVisitor();

        boolean simple = simpleQuery().accept(simpleVisitor);

        System.out.println(simple);

        Visitor<Boolean> complexVisitor = new BooleanVisitor(new UserBooleanConvertor());

        boolean complex = complexQuery().accept(complexVisitor);

        System.out.println(complex);

    }

    public static Query simpleQuery() {

        Query not = new Not.Builder("true").build();

        Query or = new Or.Builder("true", "false").build();

        Query and = new And(not, or);

        return and;
    }

    public static Query complexQuery() {

        UserInfo zhou = new UserInfo(1L, "zhou");

        UserInfo bob = new UserInfo(12L, "bob");

        Query not = new Not.Builder(zhou).build();

        Query or = new Or.Builder(zhou, bob).build();

        Query and = new And(not, or);

        return and;
    }
}
