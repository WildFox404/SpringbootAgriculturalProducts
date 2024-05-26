package com.cow.spring_vue.controller;

import com.cow.spring_vue.entity.DiseaseRecord;
import com.cow.spring_vue.entity.Result;
import com.cow.spring_vue.service.DiseaseRecordService;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/diseaseRecord")
@Validated
public class DiseaseRecordController {
    @Autowired
    private DiseaseRecordService diseaseRecordService;

    @PostMapping("/saveOrUpdate")
    public Result reviseDiseaseRecord(@RequestBody DiseaseRecord record) throws ServiceException {
        diseaseRecordService.saveOrUpdate(record);
        return Result.success("操作成功");
    }
}
