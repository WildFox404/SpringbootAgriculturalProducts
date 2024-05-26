package com.cow.spring_vue.service;

import com.cow.spring_vue.entity.ManagerFenceHouse;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Map;

public interface FenceHouseService {
    public List<ManagerFenceHouse> find();

    void saveOrUpdate(ManagerFenceHouse managerFenceHouse) throws ServiceException;

    void removeById(String fhId) throws ServiceException;

    void removeByIds(List<String> ids) throws ServiceException;

    List<ManagerFenceHouse> findAll();

    Map<String,Object> getHurdle(String fhId) throws ServiceException;

    ManagerFenceHouse getFencehouseByfhId(String fhId);
}
