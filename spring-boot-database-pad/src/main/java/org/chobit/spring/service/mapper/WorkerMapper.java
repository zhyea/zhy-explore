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
     * @param worker Worker实例
     * @return 写入行数
     */
    @Insert({
            "insert into worker (`name`, age)",
            "values",
            "(#{name}, #{age})"
    })
    @Options(useGeneratedKeys = true)
    boolean insert(Worker worker);


    /**
     * 更新年龄
     *
     * @param id  记录ID
     * @param age 年龄
     * @return 是否更新成功
     */
    @Update({
            "update worker set age=#{age}",
            "where id=#{id}"
    })
    boolean updateAge(@Param("id") int id, @Param("age") int age);


    /**
     * 更新姓名信息
     *
     * @param id   记录ID
     * @param name 姓名
     * @return 是否更新成功
     */
    @Update({
            "update worker set name=#{name}",
            "where id=#{id}"
    })
    boolean updateName(@Param("id") int id, @Param("name") String name);


    /**
     * 取数据记录
     *
     * @param id 记录ID
     * @return 记录
     */
    @Select("select * from worker where id=#{id} ")
    Worker get(@Param("id") int id);

}
