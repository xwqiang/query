package com.kuyun.client;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.expression.And;
import com.kuyun.query.expression.Not;
import com.kuyun.query.expression.Or;
import com.kuyun.query.expression.Query;
import com.kuyun.query.expression.Value;
import com.kuyun.query.visitor.PrintVisitor;
import com.kuyun.query.visitor.Visitor;
import com.kuyun.util.GsonUtil;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class VisitorTest {

    public static void main(String[] args) {

        Query not = new Not(new Value(new UserInfo()));

        Query or = new Or(new Value(new UserInfo()), new Value(new UserInfo()));

        Query and = new And(not, or);

        Visitor<Object> v = new PrintVisitor();

        Object value = and.accept(v);

        System.out.println(GsonUtil.toJson(value));

    }

}
