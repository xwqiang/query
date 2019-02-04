package com.kuyun.client;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.expression.Query;

/**
 * Created by xuwuqiang on 2019/1/31.
 */
public class ParseTest {

    public static void main(String[] args) {

        String query = "{\"and\":[{\"not\":{\"id\":12,\"name\":\"test\"}},{\"or\":[{\"id\":12,\"name\":\"bob\"},{\"id\":12,\"name\":\"zhouzhou\"}]}]}";

        Query q = Query.fromJson(query, UserInfo.class);

        System.out.println(q.toJson());

    }
}
