package com.kuyun;

import java.util.List;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class QueryDTO {

    private List<QueryDTO> and;
    private List<QueryDTO> or;
    private QueryDTO not;
    private String value;

    public List<QueryDTO> getAnd() {
        return and;
    }

    public void setAnd(List<QueryDTO> and) {
        this.and = and;
    }

    public List<QueryDTO> getOr() {
        return or;
    }

    public void setOr(List<QueryDTO> or) {
        this.or = or;
    }

    public QueryDTO getNot() {
        return not;
    }

    public void setNot(QueryDTO not) {
        this.not = not;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
