package com.kuyun.query.parsor;

import com.google.gson.Gson;
import com.kuyun.query.expression.And;
import com.kuyun.query.expression.BinaryQuery;
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
        return build(json, clz);
    }

    private <T> Query build(Map json, Class<T> clz) {

        if (isValue(json)) {
            return new Value(json);
        }

        if (json.containsKey(Query.KEY_AND)) {
            Object obj = json.get(Query.KEY_AND);
            And and = new And.Builder().build();
            new MoreCondition((List) obj, clz).more(and);
            return and;
        }

        if (json.containsKey(Query.KEY_OR)) {
            Object obj = json.get(Query.KEY_OR);
            Or or = new Or.Builder().build();
            new MoreCondition((List) obj, clz).more(or);
            return or;
        }
        if (json.containsKey(Query.KEY_NOT)) {
            Object obj = json.get(Query.KEY_NOT);
            Query cond;
            if (isValue(obj)) {
                cond = new Value(GsonUtil.fromJson(obj.toString(), clz));
            } else {
                cond = build((Map) obj, clz);
            }
            return new Not(cond);
        }

        return null;
    }

    private boolean isValue(Object obj) {
        return !isQuery(obj);
    }

    private boolean isQuery(Object obj) {
        return (obj instanceof Map && ((Map) obj).keySet().size() == 1 && (((Map) obj).containsKey(Query.KEY_AND)
            || ((Map) obj).containsKey(Query.KEY_NOT) || ((Map) obj).containsKey(Query.KEY_OR)));
    }

    class MoreCondition {

        Class clz;
        private List list;

        public MoreCondition(List list, Class clz) {
            this.list = list;
            this.clz = clz;
        }

        public void more(BinaryQuery query) {
            for (int i = 0; i < list.size(); i++) {
                Query condMore;
                if (isValue(list.get(i))) {
                    condMore = new Value(GsonUtil.fromJson(list.get(1).toString(), clz));
                } else {
                    condMore = build((Map) list.get(i), clz);
                }
                query.more(condMore);
            }
        }
    }

}
