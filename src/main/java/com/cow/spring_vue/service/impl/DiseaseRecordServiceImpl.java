package com.cow.spring_vue.service.impl;

import com.cow.spring_vue.entity.Animal;
import com.cow.spring_vue.entity.DiseaseRecord;
import com.cow.spring_vue.entity.ResultCode;
import com.cow.spring_vue.mapper.AnimalMapper;
import com.cow.spring_vue.mapper.DiseaseRecordMapper;
import com.cow.spring_vue.service.DiseaseRecordService;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class DiseaseRecordServiceImpl implements DiseaseRecordService {
    @Autowired
    private DiseaseRecordMapper diseaseRecordMapper;
    @Autowired
    private AnimalMapper animalMapper;


    @Override
    public void saveOrUpdate(DiseaseRecord record) throws ServiceException {
        String drAnimalId = record.getDrAnimalId();
        // 参数校验
        if (!StringUtils.hasText(drAnimalId) || !StringUtils.hasText(record.getDrDesc())) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }

        // 根据动物编号查询动物信息
        Animal managerAnimal = animalMapper.selectAnimalByPrimaryKey(drAnimalId);
        if (managerAnimal == null) {
            throw new ServiceException(ResultCode.FAIL);
        }

        // 添加或者修改诊疗记录
        int result; // sql语句执行后，返回受影响的行数result
        String drId = record.getDrId();
        String drStatus = record.getDrStatus();
        if (drId != null) {
            // 有id->更新操作
            if (diseaseRecordMapper.selectByPrimaryKey(drId) != null) {
                result = diseaseRecordMapper.updateByPrimaryKey(record);
            }else {
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            }
        } else {
            // 无id->新增操作
            // 未传诊疗状态，默认为无
            if (!StringUtils.hasText(drStatus)) {
                record.setDrStatus("无");
            }
            record.setDrId(UUID.randomUUID().toString());
            result = diseaseRecordMapper.insert(record);
        }

        // 添加病症记录失败
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }

        // 添加病症记录成功，根据诊疗状态修改动物健康状态
        String aHealthy = "健康";
        if (!"健康".equals(drStatus)) {
            aHealthy = "生病";
        }
        if (animalMapper.updateAHealthyByaAnimalId(aHealthy, drAnimalId) == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }
}
