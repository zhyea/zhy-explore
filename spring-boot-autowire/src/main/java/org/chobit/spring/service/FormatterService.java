package org.chobit.spring.service;

import org.chobit.spring.component.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FormatterService {

    @Autowired
    private List<Formatter> formatters;


    public List<String> format() {
        List<String> result = new LinkedList<>();
        for (Formatter f : formatters) {
            result.add(f.format());
        }
        return result;
    }


}
