package com.kuyun.query.visitor;

import com.google.gson.Gson;
import com.kuyun.query.condition.And;
import com.kuyun.query.condition.Not;
import com.kuyun.query.condition.Or;
import com.kuyun.query.condition.Query;
import com.kuyun.query.condition.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class PrintVisitor extends Visitor<Object> {

    private static Gson gson = new Gson();

    @Override
    public Object visit(And and) {
        Map<String, Object> map = new HashMap();
        List<Object> list = new ArrayList();
        for (Query a : and.queries()) {
            list.add(a.accept(this));
        }
        map.put(Query.KEY_AND, list);
        return map;
    }

    @Override
    public Object visit(Or or) {
        Map<String, Object> map = new HashMap();
        List<Object> list = new ArrayList();
        for (Query a : or.queries()) {
            list.add(a.accept(this));
        }
        map.put(Query.KEY_OR, list);
        return map;
    }

    @Override
    public Object visit(Not not) {
        Map<String, Object> map = new HashMap();
        map.put(Query.KEY_NOT, not.getA().accept(this));
        return map;
    }

    @Override
    public Object visit(Value value) {
        return gson.toJson(value.getValue());
    }
}
