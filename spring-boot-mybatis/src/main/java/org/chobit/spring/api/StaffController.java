package org.chobit.spring.api;

import org.chobit.spring.model.entity.StaffEntity;
import org.chobit.spring.model.request.ModifyIdNoRequest;
import org.chobit.spring.rw.ResponseWrapper;
import org.chobit.spring.service.StaffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@ResponseWrapper
@RestController
@RequestMapping("/api/staff")
public class StaffController {


    @Resource
    private StaffService staffService;


    @GetMapping("/all")
    public List<StaffEntity> all() {
        return staffService.findAll();
    }


    @PostMapping("/modify-id-no")
    public Boolean modifyIdNo(@RequestBody ModifyIdNoRequest request) {
        return staffService.modifyIdentityNo(request.getStaffCode(), request.getIdNo());
    }

}
