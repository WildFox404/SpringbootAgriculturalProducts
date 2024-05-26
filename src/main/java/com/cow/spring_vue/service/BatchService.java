package com.cow.spring_vue.service;

import com.cow.spring_vue.entity.Batch;
import com.cow.spring_vue.entity.child.BatchWithAnimalsDTO;
import com.google.protobuf.ServiceException;

import java.util.List;

public interface BatchService {
    void saveOrUpdate(Batch batch) throws ServiceException;

    List<Batch> queryAllUnquarantined();

    List<String> queryBatchIds();

    List<Batch> queryAll();

    List<BatchWithAnimalsDTO> queryAllWithAnimal();

    String getAnimalCount(String s);

    void deleteById(String s);
}
