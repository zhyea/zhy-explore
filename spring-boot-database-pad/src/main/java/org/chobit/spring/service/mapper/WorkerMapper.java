package org.chobit.spring.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.spring.entity.Worker;

/**
 * @author robin
 */
@Mapper
public interface WorkerMapper {

    /**
     * 建表
     */
    @Update({
            "create table if not exists worker(",
            "id int auto_increment primary key,",
            "`name` varchar(64) not null,",
            "age    int",
            ")"
    })
    void create();


    /**
     * 写入数据
     *
     * @return 写入行数
     */
    @Insert({
            "insert into worker (`name`, age)",
            "values",
            "(#{name}, #{age})"
    })
    boolean insert(Worker worker);


    /**
     * 取数据记录
     *
     * @param id 记录ID
     * @return 记录
     */
    @Select("select * from worker where id=#{id} ")
    Worker get(@Param("id") int id);

}
