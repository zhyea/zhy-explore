package org.chobit.spring.api;

import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.chobit.spring.model.entity.StaffEntity;
import org.chobit.spring.model.request.IdNoModifyRequest;
import org.chobit.spring.model.request.IdentityNoRequest;
import org.chobit.spring.model.request.StaffAddRequest;
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


    @GetMapping("/male-staffs")
    public List<StaffEntity> maleStaffList() {
        return staffService.findMaleStaffList();
    }


    @PostMapping("/modify-id-no")
    public Boolean modifyIdNo(@RequestBody IdNoModifyRequest request) {
        return staffService.modifyIdentityNo(request.getStaffCode(), request.getIdNo());
    }


    @PostMapping("/add")
    public Boolean add(@RequestBody StaffAddRequest request) {
        return staffService.addStaff(request);
    }


    @PostMapping("/add5")
    public Integer add5(@RequestBody StaffAddRequest request) {
        return staffService.addStaff5(request);
    }


    @PostMapping("/get-by-id-no")
    public StaffEntity getByIdNo(@RequestBody IdentityNoRequest req) {
        return staffService.getByIdNo(req.getIdNo());
    }
}
