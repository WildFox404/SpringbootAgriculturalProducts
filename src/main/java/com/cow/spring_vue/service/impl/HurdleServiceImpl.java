package com.cow.spring_vue.service.impl;

import com.cow.spring_vue.entity.Animal;
import com.cow.spring_vue.entity.Hurdle;
import com.cow.spring_vue.entity.ResultCode;
import com.cow.spring_vue.entity.child.HurdleId;
import com.cow.spring_vue.entity.child.HurdleUpdate;
import com.cow.spring_vue.entity.child.HurdleWithAnimalsDTO;
import com.cow.spring_vue.entity.child.HurdlefhId;
import com.cow.spring_vue.mapper.AnimalMapper;
import com.cow.spring_vue.mapper.ManagerFenceHouseMapper;
import com.cow.spring_vue.mapper.ManagerHurdlesMapper;
import com.cow.spring_vue.service.HurdleService;
import com.cow.spring_vue.utils.ThreadLocalUtil;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HurdleServiceImpl implements HurdleService {

    @Autowired
    private ManagerHurdlesMapper managerHurdlesMapper;

    @Autowired
    private ManagerFenceHouseMapper managerFenceHouseMapper;

    @Autowired
    private AnimalMapper animalMapper;

    @Override
    public void addHurdle(Hurdle hurdle) throws ServiceException {
        String fhId=hurdle.getFhId();
        if(managerFenceHouseMapper.selectByPrimaryKey(fhId) != null){
            hurdle.sethId(UUID.randomUUID().toString());
            hurdle.sethSaved(0);
            Integer result = managerHurdlesMapper.insertHurdle(hurdle);
            if (result == 0) {
                throw new ServiceException(ResultCode.FAIL);
            }
        }else{
            throw new ServiceException(ResultCode.DATA_IS_ERROR);
        }
    }

    @Override
    public void updateEnable(String hId, String hEnable) throws ServiceException {
        if ("可用".equals(hEnable) || "禁用".equals(hEnable)){
            if(managerHurdlesMapper.selectHurdleByhId(hId) != null){
                managerHurdlesMapper.updateEnable(hId,hEnable);
            }else {
                throw new ServiceException(ResultCode.DATA_IS_ERROR);
            }
        }else{
            throw new ServiceException(ResultCode.Invalid_parameter);
        }
    }

    @Override
    public List<String> updateEnables(List<Map<String, String>> idAndStatus) throws ServiceException {
        List<String> errorhId = new ArrayList<>();
        for (Map<String, String> map : idAndStatus) {
            String hId = map.get("hId");
            String hEnable = map.get("hEnable");
            if ("可用".equals(hEnable) || "禁用".equals(hEnable)){
                if(managerHurdlesMapper.selectHurdleByhId(hId) != null){
                    managerHurdlesMapper.updateEnable(hId,hEnable);
                }else {
                    errorhId.add(hId);
                }
            }else{
                throw new ServiceException(ResultCode.Invalid_parameter);
            }
        }
        return errorhId;
    }

    @Override
    public List<Map<String, Object>> queryAllMax() {
        List<String> allFhId = managerHurdlesMapper.queryDistinctFhId();

        return allFhId.stream()
                .map(fhId -> {
                    String fhName = managerFenceHouseMapper.selectFhNameByPrimaryKey(fhId);
                    List<Map<String, Object>> hurdle = managerHurdlesMapper.selecthIdhMaxByFhId(fhId)
                            .stream()
                            .map(hIdhEnable -> {
                                Map<String, Object> hurdleresult = new HashMap<>();
                                hurdleresult.put("hId", hIdhEnable.gethId());
                                hurdleresult.put("hMax", hIdhEnable.gethMax());
                                return hurdleresult;
                            })
                            .collect(Collectors.toList());

                    Map<String, Object> fenceresult = new HashMap<>();
                    fenceresult.put("fhId", fhId);
                    fenceresult.put("fhName", fhName);
                    fenceresult.put("managehurdle", hurdle);

                    return fenceresult;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Hurdle> queryAllNoFence() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<String> fhIds = managerFenceHouseMapper.selectUserFhIds(userId);
        List<Hurdle> hurdlelist = new ArrayList<>();
        for(String fhId:fhIds){
            List<Hurdle> hurdles = managerHurdlesMapper.selectHurdlesByFhId(fhId);
            hurdlelist.addAll(hurdles);
        }
        return hurdlelist;
    }

    @Override
    public List<Hurdle> queryAllEnable() throws ServiceException {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<String> fhIds = managerFenceHouseMapper.selectUserFhIds(userId);
        List<Hurdle> hurdlelist = new ArrayList<>();
        for(String fhId:fhIds){
            List<Hurdle> hurdles = managerHurdlesMapper.selectEnableHurdlesByFhId(fhId);
            hurdlelist.addAll(hurdles);
        }
        return hurdlelist;
    }

    @Override
    public List<HurdleWithAnimalsDTO> queryHurdlesAnimalsByfhId(HurdlefhId hurdlefhId) {
        List<Hurdle> hurdles = managerHurdlesMapper.selectHurdlesByFhId(hurdlefhId.getFhId());
        List<HurdleWithAnimalsDTO> result = new ArrayList<>();

        for (Hurdle hurdle : hurdles) {
            List<Animal> animals = animalMapper.selectAnimalsByhId(hurdle.gethId());

            HurdleWithAnimalsDTO dto = new HurdleWithAnimalsDTO();
            dto.sethId(hurdle.gethId());
            dto.sethName(hurdle.gethName());
            dto.sethDesc(hurdle.gethDesc());
            dto.sethMax(hurdle.gethMax());
            dto.sethSaved(hurdle.gethSaved());
            dto.sethCreateTime(hurdle.gethCreateTime());
            dto.sethUpdateTime(hurdle.gethUpdateTime());
            dto.sethEnable(hurdle.gethEnable());
            dto.sethFull(hurdle.gethFull());
            dto.setFhId(hurdle.getFhId());
            dto.setAnimalData(animals);

            result.add(dto);
        }
        return result;
    }

    @Override
    public void updateHurdle(HurdleUpdate hurdleUpdate) throws ServiceException {
        System.out.println(hurdleUpdate.getFhId());
        Integer result = managerHurdlesMapper.updateHurdle(hurdleUpdate);
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public void deleteHurdle(HurdleId hurdleId) {
        managerHurdlesMapper.deleteHurdle(hurdleId.gethId());
    }
}
