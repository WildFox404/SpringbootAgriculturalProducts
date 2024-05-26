package com.cow.spring_vue.service.impl;

import com.cow.spring_vue.entity.Hurdle;
import com.cow.spring_vue.entity.ManagerFenceHouse;
import com.cow.spring_vue.entity.ResultCode;
import com.cow.spring_vue.mapper.ManagerFenceHouseMapper;
import com.cow.spring_vue.mapper.ManagerHurdlesMapper;
import com.cow.spring_vue.service.FenceHouseService;
import com.cow.spring_vue.utils.ThreadLocalUtil;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class FenceHouseServiceImpl implements FenceHouseService {
    @Autowired
    private ManagerFenceHouseMapper managerFenceHouseMapper;

    @Autowired
    private ManagerHurdlesMapper managerHurdlesMapper;
    @Override
    public List<ManagerFenceHouse> find() {
        return managerFenceHouseMapper.find();
    }

    @Override
    public void saveOrUpdate(ManagerFenceHouse managerFenceHouse) throws ServiceException {
        // 参数校验
        String fhName = managerFenceHouse.getFhName(); //栏舍名称
        if (!StringUtils.hasText(fhName)) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }

        int result; // sql语句执行后，返回受影响的行数result

        // 根据名称查询栏舍信息
        ManagerFenceHouse house = managerFenceHouseMapper.selectByFhName(fhName);
        String fhId = managerFenceHouse.getFhId(); // 栏圈编号
        if (StringUtils.hasText(fhId)) {
            // 有id->更新操作
            // 判断该条数据是否存在
            if (managerFenceHouseMapper.selectByPrimaryKey(fhId) == null) {
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            }
            // 判断栏舍名称是否存在
            if (house != null && !house.getFhId().equals(fhId)) {
                throw new ServiceException(ResultCode.FENCE_HOUSE_IS_EXIST);
            }
            // 修改栏舍信息
            result = managerFenceHouseMapper.updateByPrimaryKey(managerFenceHouse);
        } else {
            // 无id->新增操作
            // 判断栏舍名称是否存在
            if (house != null) {
                throw new ServiceException(ResultCode.FENCE_HOUSE_IS_EXIST);
            }
            Map<String,Object> map = ThreadLocalUtil.get();
            managerFenceHouse.setFhId(UUID.randomUUID().toString());
            managerFenceHouse.setUserId(map.get("id").toString());
            // 添加栏舍信息
            result = managerFenceHouseMapper.insert(managerFenceHouse);
        }
        // 添加或者修改失败
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public void removeById(String fhId) throws ServiceException {
        if (managerFenceHouseMapper.selectByPrimaryKey(fhId) != null) {
            if (managerHurdlesMapper.selectCountByFhId(fhId) > 0) {
                // 栏舍下有栏圈信息
                throw new ServiceException(ResultCode.DATA_CAN_NOT_DELETE);
            }
            // 栏舍下没有栏圈信息，删除栏舍
            if (managerFenceHouseMapper.deleteById(fhId) == 0) {
                // 删除失败
                throw new ServiceException(ResultCode.FAIL);
            }
        } else {
            // 数据不存在或者已删除
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
    }

    @Override
    @Transactional
    public void removeByIds(List<String> ids) throws ServiceException {
        //能够被删除的栏舍id集合
        List<String> enableDeleteIds = new ArrayList<>();
        int emptyCount = 0; // 不存在的栏舍信息的条数
        int relativeCount = 0;// 关联栏圈信息的栏舍信息条数
        // 查询能够被删除的数据
        for (String id : ids) {
            if (managerFenceHouseMapper.selectByPrimaryKey(id) != null) {
                if (managerHurdlesMapper.selectCountByFhId(id) == 0) {
                    // 栏舍下没有栏圈信息
                    enableDeleteIds.add(id);
                }else {
                    relativeCount++;
                }
//                enableDeleteIds.add(id);//调试完删除这条,放出上面的注释
            }else {
                emptyCount++;
            }
        }

        // 要删除的栏舍信息都不存在
        if (emptyCount == ids.size()) {
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
        // 要删除的栏舍信息都关联栏圈信息
        if (relativeCount == ids.size()) {
            throw new ServiceException(ResultCode.DATA_CAN_NOT_DELETE);
        }
        // 要删除的栏舍编号为空
        if (enableDeleteIds.isEmpty()) {
            throw new ServiceException(ResultCode.FAIL);
        }
        // 执行删除操作
        if (managerFenceHouseMapper.deleteByIds(enableDeleteIds) == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }

    @Override
    public List<ManagerFenceHouse> findAll() {
        Map<String,Object> map = ThreadLocalUtil.get();
        return managerFenceHouseMapper.selectAll((Integer) map.get("id"));
    }

    @Override
    public Map<String,Object> getHurdle(String fhId) throws ServiceException {
        List<Hurdle> hurdle_list = managerHurdlesMapper.selectHurdlesByFhId(fhId);
        ManagerFenceHouse managerFenceHouse = managerFenceHouseMapper.selectByPrimaryKey(fhId);
        if (managerFenceHouse == null) {
            throw new ServiceException(ResultCode.DATA_IS_EMPTY);
        }
        Map<String,Object> result =new HashMap<>();
        result.put("fhId",managerFenceHouse.getFhId());
        result.put("fhName",managerFenceHouse.getFhName());
        result.put("fhDesc",managerFenceHouse.getFhDesc());
        result.put("manageHurdle",hurdle_list);
        return result;
    }

    @Override
    public ManagerFenceHouse getFencehouseByfhId(String fhId) {
        ManagerFenceHouse managerFenceHouse = managerFenceHouseMapper.getFencehouseByfhId(fhId);
        return managerFenceHouse;
    }
}
