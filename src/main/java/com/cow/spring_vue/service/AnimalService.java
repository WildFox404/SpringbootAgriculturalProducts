package com.cow.spring_vue.service;

import com.cow.spring_vue.entity.Animal;
import com.google.protobuf.ServiceException;

import java.util.List;

public interface AnimalService {
    void saveOrUpdate(Animal animal) throws ServiceException;


    List<Animal> queryAll();

    void deleteByaAnimalId(String s);
}
