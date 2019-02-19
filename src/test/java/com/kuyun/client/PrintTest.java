package com.kuyun.client;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.expression.And;
import com.kuyun.query.expression.Not;
import com.kuyun.query.expression.Or;
import com.kuyun.query.expression.Query;
import com.kuyun.query.expression.Value;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class PrintTest {

    public static void main(String[] args) {

        Query not = new Not(new UserInfo(1L, "bob"));

        Query or = new Or.Builder(new UserInfo(2L, "zhou"), new UserInfo(3L, "qiang")).build();

        Query and = new And(not, new Or(or,not));

        System.out.println(new Not(and).toJson());

        System.out.println(new Value(new UserInfo(1L,"zhou")).toJson());
    }
}
