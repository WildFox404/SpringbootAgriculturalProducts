package com.cow.spring_vue.controller;

import com.cow.spring_vue.entity.ManagerFenceHouse;
import com.cow.spring_vue.entity.Result;
import com.cow.spring_vue.entity.User;
import com.cow.spring_vue.entity.child.FencehouseId;
import com.cow.spring_vue.service.FenceHouseService;
import com.cow.spring_vue.utils.JwtUtil;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
@CrossOrigin
@RestController
@RequestMapping("/fence")
@Validated
public class FenceHouseController {
    @Autowired
    private FenceHouseService managerFenceHouseService;

    @GetMapping("/fencehouse")
    public Result<String> Fencehousefind(@RequestHeader(name="Authorization") String token, HttpServletResponse response){
        List<ManagerFenceHouse> list = managerFenceHouseService.find();
        System.out.println(list);
        return Result.success();
    }
    @PostMapping("/saveOrUpdate")
    public Result reviseFenceHouses(@RequestBody ManagerFenceHouse managerFenceHouse) throws ServiceException {
        try {
            managerFenceHouseService.saveOrUpdate(managerFenceHouse);
            return Result.success("操作成功");
        } catch (ServiceException e) {
            return Result.error(e.toString());
        }
    }
    @PostMapping("/deleteById")
    public Result removeById(@RequestBody FencehouseId fencehouseId) throws ServiceException {
        managerFenceHouseService.removeById(fencehouseId.getFhId());
        return Result.success("删除成功");
    }

    @PostMapping("/getFencehouseByfhId")
    public Result getFencehouseByfhId(@RequestBody FencehouseId fencehouseId){
        ManagerFenceHouse managerFenceHouse = managerFenceHouseService.getFencehouseByfhId(fencehouseId.getFhId());
        return Result.success(managerFenceHouse);
    }

    @DeleteMapping("/deleteByIds")
    public Result removeByIds(@RequestBody List<String> ids) throws ServiceException {
        //删除
        if (!ids.isEmpty()){
            managerFenceHouseService.removeByIds(ids);
            return Result.success();
        }else {
            return Result.error("ids为空");
        }
    }

    @GetMapping("/queryAll")
    public Result queryAll() {
        List<ManagerFenceHouse> list= managerFenceHouseService.findAll();
        return Result.success(list);
    }

    @GetMapping("/getHurdle")
    public Result getHurdle(@RequestBody Map<String, String> fhId_map) throws ServiceException {
        String fhId = fhId_map.get("fhId");
        Map<String,Object> result = managerFenceHouseService.getHurdle(fhId);
        return Result.success("操作成功",result);
    }
}