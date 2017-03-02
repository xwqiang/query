package com.kuyun.query;

/**
 * Created by xuwuqiang on 2017/3/2.
 */
public class ParseToJson {

    public static void main(String[] args) {
        Query and1 = new And(new Value("/性别/男").not(),new Value("/年龄/23"));
        Query and2 = new And(new Value("/性别/男").not(),new Value("/年龄/23"));
        Query or =  new Or(and1,and2);
        String result  = "{" + or + "}";
        System.out.println(result);
    }
}
