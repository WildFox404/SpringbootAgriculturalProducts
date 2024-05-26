package com.cow.spring_vue.service;

import com.cow.spring_vue.entity.QuarantineRegistration;
import com.google.protobuf.ServiceException;

public interface QuarantineService {
    void saveOrUpdate(QuarantineRegistration qr) throws ServiceException;
}
