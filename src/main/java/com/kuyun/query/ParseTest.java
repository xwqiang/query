package com.kuyun.query;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.condition.Query;
import com.kuyun.query.parsor.ParseClient;
import com.kuyun.query.visitor.PrintVisitor;

/**
 * Created by xuwuqiang on 2019/1/31.
 */
public class ParseTest {

    public static void main(String[] args) {
        String query = "{\"and\":[{\"not\":{\"id\":12,\"name\":\"zhouzhou\"}},{\"or\":[{\"id\":12,\"name\":\"zhouzhou\"},{\"id\":12,\"name\":\"zhouzhou\"}]}]}";
        ParseClient parseClient = new ParseClient();
        Query q = parseClient.parse(query, UserInfo.class);
        Object json = q.accept(new PrintVisitor());
        System.out.println(json);
    }
}
