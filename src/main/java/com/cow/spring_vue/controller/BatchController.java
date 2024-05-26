package com.cow.spring_vue.controller;

import com.cow.spring_vue.entity.Batch;
import com.cow.spring_vue.entity.Result;
import com.cow.spring_vue.entity.child.BatchId;
import com.cow.spring_vue.entity.child.BatchWithAnimalsDTO;
import com.cow.spring_vue.service.BatchService;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/batch")
@Validated
public class BatchController {
    @Autowired
    private BatchService batchService;

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Batch batch) throws ServiceException {
        batchService.saveOrUpdate(batch);
        return Result.success("操作成功");
    }

    @GetMapping("/queryAllUnquarantined")
    public Result queryAllUnquarantined(){
        List<Batch> result =batchService.queryAllUnquarantined();
        return Result.success("操作成功",result);
    }

    @GetMapping("/queryAll")
    public Result queryAll(){
        List<Batch> result =batchService.queryAll();
        return Result.success("操作成功",result);
    }

    @GetMapping("/queryAllWithAnimal")
    public Result queryAllWithAnimal(){
        List<BatchWithAnimalsDTO> result =batchService.queryAllWithAnimal();
        return Result.success("操作成功",result);
    }

    @PostMapping("/getAnimalCount")
    public Result getAnimalCount(@RequestBody BatchId batchId){
        String result =batchService.getAnimalCount(batchId.getbBatchId());
        return Result.success("操作成功",result);
    }

    @PostMapping("/deleteById")
    public Result deleteById(@RequestBody BatchId batchId){
        batchService.deleteById(batchId.getbBatchId());
        return Result.success("删除成功");
    }
}
