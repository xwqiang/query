package com.kuyun.query.expression;

import com.kuyun.query.parsor.ParseClient;
import com.kuyun.query.visitor.BooleanVisitor;
import com.kuyun.query.visitor.IConvertor;
import com.kuyun.query.visitor.PrintVisitor;
import com.kuyun.query.visitor.Visitor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xuwuqiang on 2017/3/1.
 */
public abstract class Query {

    public static final String KEY_AND = "and";
    public static final String KEY_NOT = "not";
    public static final String KEY_OR = "or";

    private static final String VISITMETHOD = "visit";

    private static final ParseClient parseClient = new ParseClient();
    private BooleanVisitor booleanVisitor = new BooleanVisitor();

    private static Method findMethod(Object visitor, Class<?> type) {
        if (type == Object.class) {
            return null;
        } else {
            try {
                return visitor.getClass().getMethod(VISITMETHOD, type);
            } catch (NoSuchMethodException e) {
                return findMethod(visitor, type.getSuperclass());
            }
        }
    }

    public static <T> Query fromJson(String json, Class<T> clz) {
        return parseClient.parse(json, clz);
    }

    public final <R> R accept(Visitor<R> visitor) {
        Method method = findMethod(visitor, getClass());
        if (method != null) {
            try {
                return (R) method.invoke(visitor, this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String toJson() {
        return this.accept(new PrintVisitor()).toString();
    }

    public boolean bool(IConvertor iConvertor) {
        booleanVisitor.setConvertor(iConvertor);
        return this.accept(booleanVisitor);
    }

    public boolean bool() {
        return this.accept(booleanVisitor);
    }

}
