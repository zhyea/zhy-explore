package org.chobit.spring.service;


import org.chobit.spring.entity.Master;
import org.chobit.spring.service.mapper.master.MasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MasterService {


    @Autowired
    private MasterMapper mapper;


    public void create() {
        mapper.create();
    }

    public void insert() {
        Master m = new Master();
        m.setName("mm");
        m.setJobTitle("MASTER");
        mapper.insert(m);
    }


    public Master get(int id) {
        return mapper.get(id);
    }


    @Transactional(rollbackFor = Exception.class)
    public Master get0() {
        insert();
        Master m = get(1);
        if (null == m.getJobTitle()) {
            throw new RuntimeException();
        }
        return m;
    }

}
