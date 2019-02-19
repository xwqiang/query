package com.kuyun.client;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.expression.Query;

/**
 * Created by xuwuqiang on 2019/1/31.
 */
public class ParseTest {

    public static void main(String[] args) {

        String query = "{not={and=[{not={\"id\":1,\"name\":\"bob\"}}, {or=[{or=[{\"id\":2,\"name\":\"zhou\"}, {\"id\":3,\"name\":\"qiang\"}]}, {not={\"id\":1,\"name\":\"bob\"}}]}]}}";
//        String query = "{not={and=[{or=[{not={\"id\":1,\"name\":\"bob\"}}, {or=[{\"id\":3,\"name\":\"qiang\"}, {\"id\":3,\"name\":\"qiang\"}]}]}, {not={\"id\":1,\"name\":\"bob\"}}]}}";

//        String query = "{\"id\":1,\"name\":\"zhou\"}";

        Query q = Query.fromJson(query, UserInfo.class);

        System.out.println(q.toJson());

    }
}
