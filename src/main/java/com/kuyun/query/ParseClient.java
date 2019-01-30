package com.kuyun.query;

import com.google.gson.Gson;
import com.kuyun.query.condition.And;
import com.kuyun.query.condition.Not;
import com.kuyun.query.condition.Or;
import com.kuyun.query.condition.Query;
import com.kuyun.query.condition.Value;
import com.kuyun.query.visitor.PrintVisitor;
import java.util.List;
import java.util.Map;
import org.nutz.json.Json;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class ParseClient {


    public static void main(String[] args) {
        String query = "{\"and\":[{\"not\":\"lalala\"},{\"or\":[\"hahaha\",\"123\"]}]}";
        Map<String, Object> json = new Gson().fromJson(query, Map.class);
        Query[] queryWrapper = new Query[1];//通过数组，避免实例话，同时方便按引用传值
        build(json, queryWrapper);
        Query q = queryWrapper[0];
        Object o = q.accept(new PrintVisitor());
        System.out.println(Json.toJson(o));
    }

    /**
     * todo 格式转化
     * @param json
     * @param queryWrapper
     * @return
     */
    private static Object build(Map json, Query[] queryWrapper) {

        if (json.containsKey(Query.KEY_AND)) {
            Object obj = json.get(Query.KEY_AND);
            List list = (List) obj;

            queryWrapper[0] = new And.Builder().build();
            for (int i = 0; i < list.size(); i++) {
                Query[] condMore = new Query[1];
                if (!(list.get(i) instanceof Map)) {
                    condMore[0] = new Value(list.get(1).toString());
                } else {
                    build((Map) list.get(i), condMore);
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

                if (!(list.get(i) instanceof Map)) {
                    condMore[0] = new Value(list.get(1).toString());
                } else {
                    build((Map) list.get(i), condMore);
                }
                ((Or) queryWrapper[0]).more(condMore);
            }
        }
        if (json.containsKey(Query.KEY_NOT)) {
            Object obj = json.get(Query.KEY_NOT);
            Query[] cond = new Query[1];
            if (!(obj instanceof Map)) {
                queryWrapper[0] = new Not(new Value(obj.toString()));
                return queryWrapper[0];
            }

            build((Map) obj, cond);
            queryWrapper[0] = cond[0];

        }
        return queryWrapper[0];
    }

}
