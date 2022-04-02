package org.chobit.core.interval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author robin
 */
public enum AggregateType {

    /**
     * 直接统计
     */
    COUNT("count"),

    /**
     * 长整型求和
     */
    LONG_SUM("longSum"),

    /**
     * 浮点型求和
     */
    DOUBLE_SUM("doubleSum"),

    /**
     * 浮点型求和
     */
    FLOAT_SUM("floatSum"),
    ;

    public final String type;

    AggregateType(String type) {
        this.type = type;
    }

    @JsonCreator
    public static AggregateType typeOf(String type) {
        AggregateType[] types = values();
        for (AggregateType t : types) {
            if (t.type.equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }

    @JsonValue
    public String getVal() {
        return this.type;
    }

}
