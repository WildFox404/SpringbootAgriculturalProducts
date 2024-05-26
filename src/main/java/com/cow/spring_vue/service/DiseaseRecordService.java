package com.cow.spring_vue.service;

import com.cow.spring_vue.entity.DiseaseRecord;
import com.google.protobuf.ServiceException;

public interface DiseaseRecordService {
    void saveOrUpdate(DiseaseRecord record) throws ServiceException;
}
