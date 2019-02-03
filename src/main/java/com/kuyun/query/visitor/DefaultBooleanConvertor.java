package com.kuyun.query.visitor;

import com.kuyun.query.expression.Value;

/**
 * Created by xuwuqiang on 2019/2/3.
 */
public class DefaultBooleanConvertor implements IConvertor<String> {

    @Override
    public boolean convert(Value<String> value) {
        return value.getValue().equalsIgnoreCase("true") || value.getValue().equalsIgnoreCase("yes");
    }
}
