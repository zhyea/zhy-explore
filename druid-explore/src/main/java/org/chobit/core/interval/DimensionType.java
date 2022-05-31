package org.chobit.core.interval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author robin
 */
public enum DimensionType {

    /**
     *
     */
    DEFAULT("default"),

    /**
     *
     */
    EXTRACTION("extraction"),

    /**
     *
     */
    LIST_FILTERED("listFiltered"),

    /**
     *
     */
    REGEX_FILTERED("regexFiltered"),

    /**
     *
     */
    PREFIX_FILTERED("prefixFiltered"),

    /**
     *
     */
    LOOKUP("lookup"),
    ;

    public final String type;


    DimensionType(String type) {
        this.type = type;
    }


    @JsonCreator
    public static DimensionType typeOf(String type) {
        DimensionType[] values = values();
        for (DimensionType t : values) {
            if (t.type.equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }


    @JsonValue
    public String value() {
        return this.type;
    }

}
