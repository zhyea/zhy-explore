package org.chobit.spring.app2.ext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;


/**
 * @author zhangrui137
 */
@RestControllerAdvice
public class ResponseWrapperBodyAdvice implements ResponseBodyAdvice<Object> {


    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseWrapperBody.class;


    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }


    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }
        if (selectedConverterType.equals(StringHttpMessageConverter.class)) {
            try {
                return new ObjectMapper().writeValueAsString(new Result(body));
            } catch (JsonProcessingException e) {
                return "";
            }
        }
        return new Result(body);
    }
}
