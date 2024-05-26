package com.cow.spring_vue.controller;

import com.cow.spring_vue.entity.Hurdle;
import com.cow.spring_vue.entity.Result;
import com.cow.spring_vue.entity.ResultCode;
import com.cow.spring_vue.entity.child.HurdleId;
import com.cow.spring_vue.entity.child.HurdleUpdate;
import com.cow.spring_vue.entity.child.HurdleWithAnimalsDTO;
import com.cow.spring_vue.entity.child.HurdlefhId;
import com.cow.spring_vue.service.FenceHouseService;
import com.cow.spring_vue.service.HurdleService;
import com.google.protobuf.ServiceException;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/hurdle")
@Validated
public class HurdleController {

    @Autowired
    private HurdleService hurdleService;

    @PostMapping("/addHurdle")
    public Result addHurdle(@RequestBody Hurdle hurdle) throws ServiceException {
        try {
            hurdleService.addHurdle(hurdle);
            return Result.success();
        } catch (ServiceException e) {
            return Result.error(e.toString());
        }
    }

    @PutMapping("/updateEnable")
    public Result updateEnable(String hId,@Validated String hEnable) throws ServiceException {
        hurdleService.updateEnable(hId,hEnable);
        return Result.success();
    }

    @PutMapping("/updateEnables")
    public Result updateEnables(@RequestBody List<Map<String, String>> idAndStatus) throws ServiceException {
        List<String> errorhId = hurdleService.updateEnables(idAndStatus);
        if(!errorhId.isEmpty()){
            return Result.error(String.valueOf(new ServiceException(ResultCode.DATA_IS_ERROR)),errorhId);
        }
        return Result.success("操作成功");
    }

    @GetMapping("/queryAllMax")
    public Result queryAllMax(){
        List<Map<String, Object>> result = hurdleService.queryAllMax();
        return Result.success("操作成功",result);
    }

    @PostMapping("/queryHurdlesAnimalsByfhId")
    public Result queryHurdlesAnimalsByfhId(@RequestBody HurdlefhId hurdlefhId){
        List<HurdleWithAnimalsDTO> result = hurdleService.queryHurdlesAnimalsByfhId(hurdlefhId);
        return Result.success("操作成功",result);
    }

    @GetMapping("/queryAllNoFence")
    public Result queryAll(){
        List<Hurdle> result = hurdleService.queryAllNoFence();
        return Result.success("操作成功",result);
    }

    @GetMapping("/queryAllEnable")
    public Result queryAllEnable() throws ServiceException {
        List<Hurdle> result = hurdleService.queryAllEnable();
        return Result.success("操作成功",result);
    }

    @PostMapping("/updateHurdle")
    public Result updateHurdle(@RequestBody HurdleUpdate hurdleUpdate) throws ServiceException {
        hurdleService.updateHurdle(hurdleUpdate);
        return Result.success("更新成功");
    }

    @PostMapping("/deleteHurdle")
    public Result deleteHurdle(@RequestBody HurdleId hurdleId){
        hurdleService.deleteHurdle(hurdleId);
        return Result.success("删除成功");
    }
}
