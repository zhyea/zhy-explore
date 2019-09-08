package org.chobit.spring.service.mapper.master;

import org.apache.ibatis.annotations.*;
import org.chobit.spring.entity.Master;

@Mapper
public interface MasterMapper {


    @Update({
            "create table if not exists master(",
            "id int auto_increment primary key,",
            "`name` varchar(64),",
            "job_title  varchar(64)",
            ")"
    })
    void create();


    @Insert({"insert into master (`name`, job_title) values ",
            "(#{name}, #{jobTitle})"})
    int insert(Master master);


    @Select("select * from master where id=#{id} ")
    Master get(@Param("id") int id);

}
