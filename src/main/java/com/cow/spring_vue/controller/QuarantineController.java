package com.cow.spring_vue.controller;

import com.cow.spring_vue.entity.QuarantineRegistration;
import com.cow.spring_vue.entity.Result;
import com.cow.spring_vue.service.QuarantineService;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/quarantine")
@Validated
public class QuarantineController {
    @Autowired
    private QuarantineService quarantineService;

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody QuarantineRegistration qr) throws ServiceException {
        quarantineService.saveOrUpdate(qr);
        return Result.success();
    }
}
