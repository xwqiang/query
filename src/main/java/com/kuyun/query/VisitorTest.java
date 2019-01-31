package com.kuyun.query;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.condition.Not;
import com.kuyun.query.condition.Query;
import com.kuyun.query.condition.QueryFactory;
import com.kuyun.query.condition.Value;
import com.kuyun.query.visitor.PrintVisitor;
import com.kuyun.query.visitor.Visitor;
import org.nutz.json.Json;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class VisitorTest {

    public static void main(String[] args) {
        Query not = new Not(new Value(new UserInfo()));
        Query or = QueryFactory.or(new Value(new UserInfo()), new Value(new UserInfo()));

        Query and = QueryFactory.and(not, or);

        Visitor<Object> v = new PrintVisitor();

        Object value = and.accept(v);
        System.out.println(Json.toJson(value));

    }

}
