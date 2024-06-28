package org.chobit.spring.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.spring.ext.mybatis.type.DlpTypeHandler;
import org.chobit.spring.model.entity.StaffEntity;
import org.chobit.spring.service.mapper.StaffMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StaffService extends ServiceImpl<StaffMapper, StaffEntity> {


    private static final String dlpTypeHandlerMapping = "typeHandler=" + DlpTypeHandler.class.getCanonicalName();


    /**
     * 查询全部
     *
     * @return 全部员工信息
     */
    public List<StaffEntity> findAll() {
        return this.list();
    }


    /**
     * 修改身份证号
     *
     * @param staffCode 工号
     * @param idNo      身份证号
     * @return 是否修改成功
     */
    public boolean modifyIdentityNo(String staffCode, String idNo) {
        StaffEntity staff = this.getByStaffCode(staffCode);
        staff.setIdentityNo(idNo);
        return this.updateById(staff);
    }


    public StaffEntity getByStaffCode(String staffCode) {
        LambdaQueryWrapper<StaffEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StaffEntity::getStaffCode, staffCode);
        return this.getOne(lqw);
    }


    /**
     * 修改身份证号
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

}
