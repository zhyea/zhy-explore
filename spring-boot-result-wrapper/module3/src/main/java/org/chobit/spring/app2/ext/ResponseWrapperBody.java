package org.chobit.spring.app2.ext;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @author zhangrui137
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseWrapperBody {



}
