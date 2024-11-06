package org.chobit.spring.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.spring.ext.mybatis.type.DlpTypeHandler;
import org.chobit.spring.model.entity.StaffEntity;
import org.chobit.spring.model.request.StaffAddRequest;
import org.chobit.spring.service.mapper.StaffMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.chobit.commons.utils.StrKit.isBlank;

@Slf4j
@Service
public class StaffService extends ServiceImpl<StaffMapper, StaffEntity> {


    private static final String dlpTypeHandlerMapping = "typeHandler=" + DlpTypeHandler.class.getCanonicalName();


    /**
     * 查询全部
     * <p>
     * xml文件ResultMap配置
     *
     * @return 全部员工信息
     */
    public List<StaffEntity> findAll() {
        return this.getBaseMapper().findAll();
    }

    /**
     * 查询全部
     * <p>
     * 通过mybatis-plus读取时注解中的typeHandler会生效
     *
     * @return 全部员工信息
     */
    public List<StaffEntity> findAll2() {
        return this.list();
    }


    /**
     * 查询全部的男员工信息
     *
     * @return 男性员工集合
     */
    public List<StaffEntity> findMaleStaffList() {
        return this.getBaseMapper().findByGender(1);
    }


    /**
     * 直接使用字段加密查询比较困难
     * <p>
     * 通过xml文件的形式应用typeHandler
     *
     * @param idNo 身份证号
     * @return 员工信息
     */
    public StaffEntity getByIdNo(String idNo) {
        return this.getBaseMapper().getByIdentityNo(idNo);
    }


    /**
     * 直接使用字段加密查询比较困难
     * <p>
     * 通过mybatis注解的形式应用typeHandler
     *
     * @param idNo 身份证号
     * @return 员工信息
     */
    public StaffEntity getByIdNo3(String idNo) {
        return this.getBaseMapper().getByIdNo(idNo);
    }


    /**
     * 直接使用字段加密查询比较困难
     * <p>
     * 通过mybatis-plus需要通过apply方法应用typeHandler
     *
     * @param idNo 身份证号
     * @return 员工信息
     */
    public StaffEntity getByIdNo2(String idNo) {
        LambdaQueryWrapper<StaffEntity> lqw = new LambdaQueryWrapper<>();
        lqw.apply("identity_no={0," + dlpTypeHandlerMapping + "}", idNo);
        return this.getOne(lqw);
    }


    /**
     * 新增员工信息
     * <p>
     * 通过xml的形式
     *
     * @param req 新增请求
     * @return 是否保存成功
     */
    public boolean addStaff(StaffAddRequest req) {
        if (isBlank(req.getStaffCode())) {
            throw new RuntimeException("工号不能为空");
        }
        StaffEntity staff = this.getByStaffCode(req.getStaffCode());
        if (null != staff) {
            throw new RuntimeException("相同工号的记录已存在");
        }

        return this.getBaseMapper().addStaff(req);
    }


    /**
     * 新增员工信息
     * <p>
     * 通过mybatis mapper注解的形式
     *
     * @param req 新增请求
     * @return 是否保存成功
     */
    public boolean addStaff3(StaffAddRequest req) {
        if (isBlank(req.getStaffCode())) {
            throw new RuntimeException("工号不能为空");
        }
        StaffEntity staff = this.getByStaffCode(req.getStaffCode());
        if (null != staff) {
            throw new RuntimeException("相同工号的记录已存在");
        }

        return this.getBaseMapper().add(req);
    }


    /**
     * 新增员工信息
     * <p>
     * 通过mybatis mapper注解的形式
     *
     * @param req 新增请求
     * @return 是否保存成功
     */
    public boolean addStaff2(StaffAddRequest req) {
        if (isBlank(req.getStaffCode())) {
            throw new RuntimeException("工号不能为空");
        }
        StaffEntity staff = this.getByStaffCode(req.getStaffCode());
        if (null != staff) {
            throw new RuntimeException("相同工号的记录已存在");
        }

        staff = new StaffEntity();
        staff.setStaffCode(req.getStaffCode());
        staff.setName(req.getName());
        staff.setAge(req.getAge());
        staff.setGender(req.getGender());
        staff.setIdentityNo(req.getIdentityNo());

        return this.save(staff);
    }

    /**
     * 新增员工信息
     * <p>
     * 通过mybatis mapper注解的形式
     *
     * @param req 新增请求
     * @return 是否保存成功
     */
    public Integer addStaff5(StaffAddRequest req) {
        if (isBlank(req.getStaffCode())) {
            throw new RuntimeException("工号不能为空");
        }
        StaffEntity staff = this.getByStaffCode(req.getStaffCode());
        if (null != staff) {
            throw new RuntimeException("相同工号的记录已存在");
        }

        staff = new StaffEntity();
        staff.setStaffCode(req.getStaffCode());
        staff.setName(req.getName());
        staff.setAge(req.getAge());
        staff.setGender(req.getGender());
        staff.setIdentityNo(req.getIdentityNo());

        this.getBaseMapper().add2(staff);

        return staff.getId();
    }


    /**
     * 修改身份证号
     * <p>
     * 通过xml文件注解进行修改，typeHandler会生效
     *
     * @param staffCode 工号
     * @param idNo      身份证号
     * @return 是否修改成功
     */
    public boolean modifyIdentityNo(String staffCode, String idNo) {
        return this.getBaseMapper().updateByStaffCode(staffCode, idNo);
    }


    /**
     * 修改身份证号
     * <p>
     * 通过mybatis注解进行修改，typeHandler会生效
     *
     * @param staffCode 工号
     * @param idNo      身份证号
     * @return 是否修改成功
     */
    public boolean modifyIdentityNo4(String staffCode, String idNo) {
        return this.getBaseMapper().updateIdNo(staffCode, idNo);
    }


    /**
     * 修改身份证号
     * <p>
     * 通过Entity实例修改时注解中的typeHandler会生效
     *
     * @param staffCode 工号
     * @param idNo      身份证号
     * @return 是否修改成功
     */
    public boolean modifyIdentityNo3(String staffCode, String idNo) {
        StaffEntity staff = this.getByStaffCode(staffCode);
        staff.setIdentityNo(idNo);
        return this.updateById(staff);
    }


    /**
     * 获取员工信息
     * <p>
     * 通过mybatis-plus读取时注解中的typeHandler会生效
     *
     * @param staffCode 工号
     * @return 员工信息
     */
    public StaffEntity getByStaffCode(String staffCode) {
        LambdaQueryWrapper<StaffEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StaffEntity::getStaffCode, staffCode);
        return this.getOne(lqw);
    }


    /**
     * 修改身份证号
     * <p>
     * mybatis-plus通过UpdateWrapper修改，需要显式声明mapping信息
     *
     * @param staffCode 工号
     * @param idNo      身份证号
     * @return 是否修改成功
     */
    public boolean modifyIdentityNo2(String staffCode, String idNo) {
        LambdaUpdateWrapper<StaffEntity> luw = new LambdaUpdateWrapper<>();
        luw.set(StaffEntity::getIdentityNo, idNo, dlpTypeHandlerMapping)
                .eq(StaffEntity::getStaffCode, staffCode);
        return this.update(luw);
    }



    public List<StaffEntity> batchQuery(List<String> idNoList){
        return this.getBaseMapper().findByIdentityNoList(idNoList);
    }

}
