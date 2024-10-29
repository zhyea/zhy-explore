package org.chobit.spring.model;

import org.chobit.commons.exception.ParamException;
import org.chobit.commons.validation.SelfCheck;

import javax.validation.constraints.NotBlank;

/**
 * 用户信息请求
 *
 * @author rui.zhang
 */
public class UserRequest  {


    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SelfCheck
    public boolean check() throws ParamException {
        System.out.println("111111111111");
        return false;
    }
}
