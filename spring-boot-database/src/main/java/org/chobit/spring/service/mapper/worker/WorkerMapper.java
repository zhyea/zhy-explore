package org.chobit.spring.service.mapper.worker;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.chobit.spring.entity.Worker;

@Mapper
public interface WorkerMapper {

    @Update({
            "create table if not exists worker(",
            "id int auto_increment primary key,",
            "`name` varchar(64),",
            "age    int",
            ")"
    })
    void create();



    @Select("select * from worker where id=#{id} ")
    Worker get(@Param("id") int id);

}
