package com.kuyun.query;

import com.google.gson.Gson;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.nutz.http.Http;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public class ParseToQuery {

    public static void main(String[] args) {
//        String query = "{\"and\":[{\"or\":[\"/peers/性别/男\",\"/peers/性别/女\"]},{\"and\":[\"/peers/性别/男\",\"/peers/性别/女\"]}]}";
        String query = Http.get("http://localhost:8084/auth.html").getContent();
        System.out.println(query);
        System.out.println(parse(query));
    }

    @SuppressWarnings("rawtypes")
    public static Query parse(String queryString) {
        if (queryString.equals("{}")) {
            return null;
        }
        if (queryString.startsWith("\"")) {
            return new Value(queryString.substring(1, queryString.length() - 1));
        }
        Map json = new Gson().fromJson(queryString, Map.class);
        Query[] queryWrapper = new Query[1];
        buildQuery(json, queryWrapper);
        Query query = queryWrapper[0];

        return queryWrapper[0];
    }

    @SuppressWarnings({"rawtypes", "Duplicates"})
    private static void buildQuery(Object o, Query[] queryWrapper) {
        {
            if (o instanceof Map) {
                Map map = (Map) o;
                Entry e = (Entry) map.entrySet().iterator().next();
                if (e.getKey().equals("not")) {
                    Query[] subWrapper = new Query[1];
                    buildQuery(e.getValue(), subWrapper);
                    queryWrapper[0] = subWrapper[0].not();
                } else if (e.getKey().equals("and") || e.getKey().equals("or")) {
                    Query[] leftWrapper = new Query[1];
                    Query[] rightWrapper = new Query[1];
                    Iterator listIter = ((List) e.getValue()).iterator();
                    buildQuery(listIter.next(), leftWrapper);
                    buildQuery(listIter.next(), rightWrapper);
                    if (leftWrapper[0] == null) {
                        queryWrapper[0] = rightWrapper[0];
                    } else if (rightWrapper[0] == null) {
                        queryWrapper[0] = leftWrapper[0];
                    } else {
                        String op = (String) e.getKey();
                        if (op.equals("and")) {
                            queryWrapper[0] = new And(leftWrapper[0], rightWrapper[0]);
                        } else if (op.equals("or")) {
                            queryWrapper[0] = new Or(leftWrapper[0], rightWrapper[0]);
                        } else {
                            throw new IllegalArgumentException(op);
                        }
                    }
                }
            } else {
                queryWrapper[0] = new Value((String) o);
            }
        }
    }
}
