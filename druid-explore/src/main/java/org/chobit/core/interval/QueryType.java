package org.chobit.core.interval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author robin
 */
public enum QueryType {

    /**
     * 分组查询
     */
    GROUP_BY("groupBy"),

    TOP_N("topN"),

    SEARCH("search"),
    ;


    public final String type;


    QueryType(String val) {
        this.type = val;
    }


    @JsonValue
    public String getType() {
        return this.type;
    }

    @JsonCreator
    public QueryType typeOf(String type) {
        QueryType[] arr = values();
        for (QueryType t : arr) {
            if (t.type.equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }
}
