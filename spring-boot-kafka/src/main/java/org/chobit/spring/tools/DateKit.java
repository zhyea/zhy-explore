package org.chobit.spring.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateKit {


    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:SS";


    public static Date parse(String date) throws ParseException {
        return new SimpleDateFormat(DEFAULT_PATTERN).parse(date);
    }


    public DateKit() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}
