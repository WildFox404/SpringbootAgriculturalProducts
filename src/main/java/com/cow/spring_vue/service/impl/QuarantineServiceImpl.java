package com.cow.spring_vue.service.impl;

import com.cow.spring_vue.entity.Batch;
import com.cow.spring_vue.entity.QuarantineRegistration;
import com.cow.spring_vue.entity.ResultCode;
import com.cow.spring_vue.mapper.BatchMapper;
import com.cow.spring_vue.mapper.QuarantineMapper;
import com.cow.spring_vue.service.QuarantineService;
import com.cow.spring_vue.utils.ThreadLocalUtil;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;

@Service
public class QuarantineServiceImpl implements QuarantineService {
    @Autowired
    private QuarantineMapper quarantineMapper;

    @Autowired
    private BatchMapper batchMapper;

    @Override
    public void saveOrUpdate(QuarantineRegistration qr) throws ServiceException {
        // 参数校验
        String grBatchId = qr.getGrBatchId();
        String bQualified = qr.getbQualified();
        if (!StringUtils.hasText(grBatchId) ||
                !StringUtils.hasText(qr.getGrMechanism()) ||
                !StringUtils.hasText(bQualified) ||
                !StringUtils.hasText(qr.getGrImg())) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }
        Batch batch = batchMapper.selectByBatchId(grBatchId);
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        if (batch==null||!batch.getUserId().equals(id)){
            throw new ServiceException(ResultCode.DATA_IS_ERROR);
        }

        int result; // sql语句执行后，返回受影响的行数result
        if (qr.getGrId() != null) {
            // 有id->更新操作
            result = quarantineMapper.updateByPrimaryKey(qr);
        } else {
            // 无id->新增操作
            qr.setGrId(UUID.randomUUID().toString());
            result = quarantineMapper.insert(qr);
        }
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }

        // 修改批次表中对应批次的检疫状态和检疫合格状态
        if (batchMapper.updatebQuarantineByBatchId(bQualified, grBatchId) == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }
}
