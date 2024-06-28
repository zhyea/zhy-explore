package org.chobit.logback.appender;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;


/**
 * @author rui.zhang
 */
public class CustomAppender extends AppenderBase<LoggingEvent> {


    @Override
    protected void append(LoggingEvent event) {
        event.getArgumentArray();
    }


}
