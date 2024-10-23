package org.chobit.spring.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.chobit.spring.ext.mybatis.type.DlpTypeHandler;
import org.chobit.spring.model.entity.StaffEntity;
import org.chobit.spring.model.request.StaffAddRequest;

import java.util.List;

@Mapper
public interface StaffMapper extends BaseMapper<StaffEntity> {


    @Select({"select * from staff where gender=#{gender}"})
    @Results(
            id = "StaffResult",
            value = {
                    @Result(column = "identity_no", property = "identityNo", typeHandler = DlpTypeHandler.class)
            })
    List<StaffEntity> findByGender(@Param("gender") Integer gender);


    List<StaffEntity> findAll();


    @Update({"update staff set identity_no=#{idNo, typeHandler=org.chobit.spring.ext.mybatis.type.DlpTypeHandler}",
            "where staff_code=#{staffCode}"})
    boolean updateIdNo(@Param("staffCode") String staffCode, @Param("idNo") String idNo);


    boolean updateByStaffCode(@Param("staffCode") String staffCode, @Param("idNo") String idNo);


    @Insert({
            "insert into staff ",
            "(name, age, gender, identity_no, staff_code)",
            "values ",
            "(#{item.name}, #{item.age}, #{item.gender},",
            " #{item.identityNo, typeHandler=org.chobit.spring.ext.mybatis.type.DlpTypeHandler},",
            " #{item.staffCode})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean add(@Param("item") StaffAddRequest request);


    @Insert({
            "insert into staff ",
            "(name, age, gender, identity_no, staff_code)",
            "values ",
            "(#{item.name}, #{item.age}, #{item.gender},",
            " #{item.identityNo, typeHandler=org.chobit.spring.ext.mybatis.type.DlpTypeHandler},",
            " #{item.staffCode})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean add2(@Param("item") StaffEntity entity);



    boolean addStaff(@Param("item") StaffAddRequest request);


    @Select({
            "select * from staff",
            "where identity_no=#{idNo, typeHandler=org.chobit.spring.ext.mybatis.type.DlpTypeHandler}"
    })
    // 这里复用了 findByGender 方法上定义的ResultMap
    @ResultMap("StaffResult")
    StaffEntity getByIdNo(@Param("idNo") String idNo);


    StaffEntity getByIdentityNo(@Param("idNo") String idNo);

}
