package com.cow.spring_vue.service.impl;

import com.cow.spring_vue.entity.Animal;
import com.cow.spring_vue.entity.Batch;
import com.cow.spring_vue.entity.Hurdle;
import com.cow.spring_vue.entity.ResultCode;
import com.cow.spring_vue.mapper.AnimalMapper;
import com.cow.spring_vue.mapper.BatchMapper;
import com.cow.spring_vue.mapper.ManagerFenceHouseMapper;
import com.cow.spring_vue.mapper.ManagerHurdlesMapper;
import com.cow.spring_vue.service.AnimalService;
import com.cow.spring_vue.utils.ThreadLocalUtil;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalMapper animalMapper;
    @Autowired
    private ManagerHurdlesMapper managerHurdlesMapper;
    @Autowired
    private BatchMapper batchMapper;

    @Override
    public void saveOrUpdate(Animal animal) throws ServiceException {
        String aBatchId = animal.getaBatchId(); // 批次编号
        String aHurdlesIdNew = animal.getaHurdlesId(); // 栏圈编号
        // 参数校验
        if (!StringUtils.hasText(animal.getaWeightInit().toString()) ||
                !StringUtils.hasText(animal.getaGender()) ||
                !StringUtils.hasText(animal.getaHealthy()) ||
                !StringUtils.hasText(animal.getaInoculate()) ||
                !StringUtils.hasText(aBatchId) ||
                !StringUtils.hasText(aHurdlesIdNew)||
                !StringUtils.hasText(animal.getaStatus())) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }
        // 根据栏圈编号查询栏圈信息

        Hurdle hurdle = managerHurdlesMapper.selectHurdleByhId(aHurdlesIdNew);
        if (hurdle == null) {
            throw new ServiceException(ResultCode.FAIL);
        }
        Integer hSavedNew = hurdle.gethSaved(); // 栏圈已存猪的数量
        Integer hMaxNew = hurdle.gethMax(); // 栏圈最大容量
        String hFullNew = hurdle.gethFull(); // 栏圈是否已满

        // 添加或者修改动物信息
        String aAnimalId = animal.getaAnimalId();
        if(StringUtils.hasText(aAnimalId)){
            // 有id->更新操作
            // 根据id查询动物信息
            Animal managerAnimal = animalMapper.selectAnimalByPrimaryKey(aAnimalId);
            if (managerAnimal == null) {
                // 动物不存在
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            }
            // 动物存在，修改动物信息
            int result = animalMapper.updateByPrimaryKey(animal);
            if (result == 0) {
                // 修改失败
                throw new ServiceException(ResultCode.FAIL);
            }
            animalMapper.insertrecord(animal);
            // 修改成功，判断是否修改了栏圈信息
            String aHurdlesIdOld = managerAnimal.getaHurdlesId();
            Hurdle managerHurdlesOld = managerHurdlesMapper.selectHurdleByhId(aHurdlesIdOld);
            if (!aHurdlesIdNew.equals(aHurdlesIdOld)) {
                // 修改新的栏圈信息
                hurdle.sethSaved(hSavedNew + 1);
                if (hSavedNew + result == hMaxNew) {
                    hurdle.sethFull("已满");
                }
                if (managerHurdlesMapper.updateByPrimaryKey(hurdle) == 0) {
                    throw new ServiceException(ResultCode.FAIL);
                }

                // 修改老的栏圈信息
                managerHurdlesOld.sethSaved(managerHurdlesOld.gethSaved() - 1);
                if ("已满".equals(managerHurdlesOld.gethFull())) {
                    managerHurdlesOld.sethFull("未满");
                }
                if (managerHurdlesMapper.updateByPrimaryKey(managerHurdlesOld) == 0) {
                    throw new ServiceException(ResultCode.FAIL);
                }
            }
        }else {
            // 无id->新增操作
            if (hSavedNew.equals(hMaxNew)) {
                throw new ServiceException(ResultCode.FULL);
            }
            // 根据批次编号查询批次信息
            Batch batch = batchMapper.selectByBatchId(aBatchId);
            if (batch == null) {
                throw new ServiceException(ResultCode.FAIL);
            }
            // 批次存在，根据批次检疫状态，动态设置动物过程状态
            if ("已检疫".equals(batch.getbQuarantine())) {
                animal.setaStatus("已检疫");
            }else if("养殖中".equals(batch.getbQuarantine())){
                animal.setaStatus("养殖中");
            }
            // 执行添加
            animal.setaAnimalId(UUID.randomUUID().toString());
            int result = animalMapper.insert(animal);
            animalMapper.insertrecord(animal);
            if (result == 0) {
                // 添加失败
                throw new ServiceException(ResultCode.FAIL);
            }
            // 添加成功，修改栏圈信息
            hurdle.sethSaved(hSavedNew + 1);
            if (hSavedNew + result == hMaxNew) {
                // 栏圈已满，修改h_Full字段
                hurdle.sethFull("已满");
            }
            if (managerHurdlesMapper.updateByPrimaryKey(hurdle) == 0) {
                throw new ServiceException(ResultCode.FAIL);
            };
        }
    }

    @Override
    public List<Animal> queryAll() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer)map.get("id");
        List<String> BatchIds = batchMapper.selectBatchIds(id);
        List<Animal> AnimalList =new ArrayList<>();
        for(String BatchId:BatchIds){
            List<Animal> animals =animalMapper.selectAnimalsByBatchId(BatchId);
            AnimalList.addAll(animals);
        }
        return AnimalList;
    }

    @Override
    public void deleteByaAnimalId(String AnimalId) {
        Animal animal = animalMapper.selectAnimalByPrimaryKey(AnimalId);
        animal.setaStatus("出售");
        animalMapper.insertrecord(animal);
        animalMapper.deleteByaAnimalId(AnimalId);

    }

}
