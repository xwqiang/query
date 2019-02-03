package com.kuyun.query.visitor;

import com.kuyun.query.expression.Value;

/**
 * Created by xuwuqiang on 2019/2/3.
 */
public interface IConvertor<T> {

    boolean convert(Value<T> value);

}
