package com.cow.spring_vue.service.impl;

import com.cow.spring_vue.entity.Animal;
import com.cow.spring_vue.entity.Batch;
import com.cow.spring_vue.entity.Hurdle;
import com.cow.spring_vue.entity.ResultCode;
import com.cow.spring_vue.entity.child.BatchWithAnimalsDTO;
import com.cow.spring_vue.entity.child.HurdleWithAnimalsDTO;
import com.cow.spring_vue.mapper.AnimalMapper;
import com.cow.spring_vue.mapper.BatchMapper;
import com.cow.spring_vue.service.BatchService;
import com.cow.spring_vue.utils.ThreadLocalUtil;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private BatchMapper batchMapper;

    @Autowired
    private AnimalMapper animalMapper;

    @Override
    public void saveOrUpdate(Batch batch) throws ServiceException {
        int result = 0;
        String BatchId = batch.getbBatchId();
        if(StringUtils.isEmpty(batch.getbQuarantine()) || StringUtils.isEmpty(batch.getbLocation()) || StringUtils.isEmpty(batch.getbStatus())) {
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
        if(StringUtils.hasText(BatchId)){
            //有bBatchId,修改操作
            result = batchMapper.updateByPrimaryKey(batch);
        }else{
            Map<String, Object> map = ThreadLocalUtil.get();
            Integer id =(Integer)map.get("id");
            batch.setUserId(id);
            batch.setbBatchId(UUID.randomUUID().toString());
            //无bBatchId,添加操作
            result = batchMapper.insert(batch);
        }

        if(result==0){
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public List<Batch> queryAllUnquarantined() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer)map.get("id");
        List<Batch> result = batchMapper.selectBatchesByQuarantine("未检疫",id);
        return result;
    }

    @Override
    public List<String> queryBatchIds() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer)map.get("id");
        List<String> BatchIds = batchMapper.selectBatchIds(id);
        return BatchIds;
    }

    @Override
    public List<Batch> queryAll() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer)map.get("id");
        List<Batch> result = batchMapper.selectAll(id);
        return result;
    }

    @Override
    public List<BatchWithAnimalsDTO> queryAllWithAnimal() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer)map.get("id");
        List<Batch> batches = batchMapper.selectAll(id);
        List<BatchWithAnimalsDTO> result = new ArrayList<>();

        for (Batch batch : batches) {
            List<Animal> animals = animalMapper.selectAnimalsByBatchId(batch.getbBatchId());

            BatchWithAnimalsDTO dto = new BatchWithAnimalsDTO();
            dto.setbBatchId(batch.getbBatchId());
            dto.setbStatus(batch.getbStatus());
            dto.setbLocation(batch.getbLocation());
            dto.setbQuarantine(batch.getbQuarantine());
            dto.setbTime(batch.getbTime());
            dto.setAnimalData(animals);

            result.add(dto);
        }
        return result;
    }

    @Override
    public String getAnimalCount(String batchId) {
        Integer result = batchMapper.getAnimalCount(batchId);
        return result.toString();
    }

    @Override
    public void deleteById(String s) {
        batchMapper.deleteById(s);
    }
}
