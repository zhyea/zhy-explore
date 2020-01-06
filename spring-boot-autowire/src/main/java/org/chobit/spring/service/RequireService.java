package org.chobit.spring.service;

import org.chobit.spring.service.dao.FooDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequireService {

    @Autowired(required = false)
    private FooDao fooDao;


    public FooDao getFooDao() {
        return fooDao;
    }
}
