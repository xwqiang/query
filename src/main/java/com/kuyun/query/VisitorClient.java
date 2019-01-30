package com.kuyun.query;

import com.kuyun.query.condition.Query;
import com.kuyun.query.condition.QueryFactory;
import com.kuyun.query.visitor.PrintVisitor;
import com.kuyun.query.visitor.Visitor;
import org.nutz.json.Json;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class VisitorClient {

    public static void main(String[] args) {
        Query not = QueryFactory.not("lalala");
        Query or = QueryFactory.or("hahaha", "123");

        Query and = QueryFactory.and(not, or);

        Visitor<Object> v = new PrintVisitor();

        Object value = and.accept(v);
        System.out.println(Json.toJson(value));

    }

}
