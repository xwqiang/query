package com.kuyun.query.visitor;

import com.kuyun.domain.UserInfo;
import com.kuyun.query.expression.Value;

/**
 * Created by xuwuqiang on 2019/2/3.
 */
public class UserBooleanConvertor implements IConvertor<UserInfo> {

    @Override
    public boolean convert(Value<UserInfo> value) {
        return value.getValue().getId().intValue() == 12;
    }
}
