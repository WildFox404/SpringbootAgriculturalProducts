package com.cow.spring_vue.controller;

import com.cow.spring_vue.entity.Result;
import com.cow.spring_vue.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/analysis")
@Validated
@CrossOrigin
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/indexCount")
    public Result getAnimalWeightInfo() {
        Map<String, Integer> result = analysisService.countWeight();
        return Result.success(result);
    }
}
