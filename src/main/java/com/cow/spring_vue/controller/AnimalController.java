package com.cow.spring_vue.controller;

import com.cow.spring_vue.entity.Animal;
import com.cow.spring_vue.entity.ManagerFenceHouse;
import com.cow.spring_vue.entity.Result;
import com.cow.spring_vue.entity.child.AnimalId;
import com.cow.spring_vue.service.AnimalService;
import com.cow.spring_vue.service.BatchService;
import com.cow.spring_vue.service.FenceHouseService;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/animal")
@Validated
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Animal animal) throws ServiceException {
        animalService.saveOrUpdate(animal);
        return Result.success("操作成功");

    }

    @PostMapping("/queryAll")
    public Result queryAll(){
        List<Animal> result = animalService.queryAll();
        return Result.success(result);
    }

    @PostMapping("/deleteByaAnimalId")
    public Result deleteByaAnimalId(@RequestBody AnimalId animalId){
        animalService.deleteByaAnimalId(animalId.getaAnimalId());
        return Result.success("删除成功");
    }
}
