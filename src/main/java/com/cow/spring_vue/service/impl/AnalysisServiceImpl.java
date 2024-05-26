package com.cow.spring_vue.service.impl;

import com.cow.spring_vue.entity.Animal;
import com.cow.spring_vue.mapper.AnalysisMapper;
import com.cow.spring_vue.mapper.AnimalMapper;
import com.cow.spring_vue.mapper.BatchMapper;
import com.cow.spring_vue.service.AnalysisService;
import com.cow.spring_vue.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;

    @Autowired
    private AnimalMapper animalMapper;

    @Autowired
    private BatchMapper batchMapper;

    public Map<String, Integer> countWeight() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer)map.get("id");
        List<String> batchIds = batchMapper.selectBatchIdsByUserId(id);
        List<Animal> animals= new ArrayList<>();
        for(String batchId:batchIds){
            List<Animal> tempList=animalMapper.selectAnimalsByBatchId(batchId);
            animals.addAll(tempList);
        }
        Map<String, Integer> weightCountMap = new HashMap<>();
        int count0to30 = 0;
        int count30to50 = 0;
        int count50above = 0;

        for (Animal animal : animals) {
            int weightNow = animal.getaWeightNow();
            if (weightNow >= 0 && weightNow <= 30) {
                count0to30++;
            } else if (weightNow > 30 && weightNow <= 50) {
                count30to50++;
            } else {
                count50above++;
            }
        }

        weightCountMap.put("0-30", count0to30);
        weightCountMap.put("30-50", count30to50);
        weightCountMap.put("50+", count50above);

        return weightCountMap;
    }
}
