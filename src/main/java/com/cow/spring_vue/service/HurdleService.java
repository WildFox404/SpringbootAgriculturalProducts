package com.cow.spring_vue.service;

import com.cow.spring_vue.entity.Hurdle;
import com.cow.spring_vue.entity.child.HurdleId;
import com.cow.spring_vue.entity.child.HurdleUpdate;
import com.cow.spring_vue.entity.child.HurdleWithAnimalsDTO;
import com.cow.spring_vue.entity.child.HurdlefhId;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Map;

public interface HurdleService {
    void addHurdle(Hurdle hurdle) throws ServiceException;

    void updateEnable(String hId, String hEnable) throws ServiceException;

    List<String> updateEnables(List<Map<String, String>> idAndStatus) throws ServiceException;

    List<Map<String, Object>> queryAllMax();

    List<Hurdle> queryAllNoFence();

    List<Hurdle> queryAllEnable() throws ServiceException;

    List<HurdleWithAnimalsDTO> queryHurdlesAnimalsByfhId(HurdlefhId hurdlefhId);

    void updateHurdle(HurdleUpdate hurdleUpdate) throws ServiceException;

    void deleteHurdle( HurdleId hurdleId);
}
