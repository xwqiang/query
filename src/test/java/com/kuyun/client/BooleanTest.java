package com.kuyun.client;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.expression.And;
import com.kuyun.query.expression.Not;
import com.kuyun.query.expression.Or;
import com.kuyun.query.expression.Query;
import com.kuyun.query.visitor.UserBooleanConvertor;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class BooleanTest {

    public static void main(String[] args) {

        System.out.println(simpleQuery().bool());

        System.out.println(complexQuery().bool(new UserBooleanConvertor()));

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
