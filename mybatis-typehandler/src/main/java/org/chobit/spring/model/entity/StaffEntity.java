package org.chobit.spring.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.chobit.spring.ext.mybatis.type.DlpTypeHandler;

import java.util.Date;

@TableName(value = "staff", autoResultMap = true)
@Data
public class StaffEntity extends BaseEntity {


    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 身份证号
     */
    @TableField(value = "identity_no", typeHandler = DlpTypeHandler.class)
    private String identityNo;

    /**
     * 工号
     */
    private String staffCode;

    /**
     * 删除标记
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
