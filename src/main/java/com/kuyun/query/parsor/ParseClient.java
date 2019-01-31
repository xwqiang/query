package com.kuyun.query.parsor;

import com.google.gson.Gson;
import com.kuyun.query.expression.And;
import com.kuyun.query.expression.Not;
import com.kuyun.query.expression.Or;
import com.kuyun.query.expression.Query;
import com.kuyun.query.expression.Value;
import com.kuyun.util.GsonUtil;
import java.util.List;
import java.util.Map;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class ParseClient {


    public <T> Query parse(String query, Class<T> clz) {
        Map<String, Object> json = new Gson().fromJson(query, Map.class);
        Query[] queryWrapper = new Query[1];
        build(json, queryWrapper, clz);
        return queryWrapper[0];
    }

    private <T> Object build(Map json, Query[] queryWrapper, Class<T> clz) {

        if (json.containsKey(Query.KEY_AND)) {
            Object obj = json.get(Query.KEY_AND);
            List list = (List) obj;
            queryWrapper[0] = new And.Builder().build();
            for (int i = 0; i < list.size(); i++) {
                Query[] condMore = new Query[1];
                if (isValue(list.get(i))) {
                    condMore[0] = new Value(GsonUtil.fromJson(list.get(1).toString(), clz));
                } else {
                    build((Map) list.get(i), condMore, clz);
                }
                ((And) queryWrapper[0]).more(condMore);
            }
        }

        if (json.containsKey(Query.KEY_OR)) {
            Object obj = json.get(Query.KEY_OR);
            List list = (List) obj;
            queryWrapper[0] = new Or.Builder().build();
            for (int i = 0; i < list.size(); i++) {
                Query[] condMore = new Query[1];
                if (isValue(list.get(i))) {
                    condMore[0] = new Value(GsonUtil.fromJson(list.get(1).toString(), clz));
                } else {
                    build((Map) list.get(i), condMore, clz);
                }
                ((Or) queryWrapper[0]).more(condMore);
            }
        }
        if (json.containsKey(Query.KEY_NOT)) {
            queryWrapper[0] = new Not.Builder().build();
            Object obj = json.get(Query.KEY_NOT);
            Query[] cond = new Query[1];
            if (isValue(obj)) {
                cond[0] = new Value(GsonUtil.fromJson(obj.toString(), clz));
            } else {
                build((Map) obj, cond, clz);
            }
            ((Not) queryWrapper[0]).not(cond[0]);
        }

        return queryWrapper[0];

    }

    private boolean isValue(Object obj) {
        return !isQuery(obj);
    }

    private boolean isQuery(Object obj) {
        return (obj instanceof Map && ((Map) obj).keySet().size() == 1 && (((Map) obj).containsKey(Query.KEY_AND)
            || ((Map) obj).containsKey(Query.KEY_NOT) || ((Map) obj).containsKey(Query.KEY_OR)));
    }

}
