package org.chobit.spring.model.request;

import lombok.Data;

@Data
public class StaffAddRequest {


    private String staffCode;


    private String name;


    private Integer age;


    private Integer gender;

    
    private String identityNo;

}
