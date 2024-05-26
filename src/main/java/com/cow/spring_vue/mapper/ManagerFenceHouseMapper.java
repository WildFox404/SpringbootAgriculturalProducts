package com.cow.spring_vue.mapper;

import com.cow.spring_vue.entity.ManagerFenceHouse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ManagerFenceHouseMapper {

    @Select("select * from fencehouses")
    public List<ManagerFenceHouse> find();
    @Select("select * from fencehouses where fhName=#{fhName}")
    ManagerFenceHouse selectByFhName(String fhName);
    @Select("select * from fencehouses where fhId=#{fhId}")
    ManagerFenceHouse selectByPrimaryKey(String fhId);

    @Select("select fhName from fencehouses where fhId=#{fhId}")
    String selectFhNameByPrimaryKey(String fhId);
    @Update("update fencehouses set fhName = #{fhName}, fhDesc = #{fhDesc}, fhUpdateTime = now() where fhId = #{fhId}")
    int updateByPrimaryKey(ManagerFenceHouse managerFenceHouse);
    @Insert("insert into fencehouses(fhId, fhName, fhDesc, userId,fhCreateTime,fhUpdateTime)" +
            " values (#{fhId},#{fhName},#{fhDesc},#{userId},now(),now())")
    int insert(ManagerFenceHouse managerFenceHouse);
    @Delete("DELETE FROM fencehouses WHERE fhId = #{fhId}")
    int deleteById(String fhId);
    @Delete("DELETE FROM fencehouses WHERE fhId IN (#{enableDeleteIds});")
    int deleteByIds(List<String> enableDeleteIds);

    @Select("SELECT * FROM fencehouses where userId=#{id}")
    List<ManagerFenceHouse> selectAll(Integer id);

    @Select("SELECT DISTINCT fhId FROM fencehouses WHERE userId = #{userId}")
    List<String> selectUserFhIds(Integer id);

    @Select("select * from fencehouses where fhId=#{fhId}")
    ManagerFenceHouse getFencehouseByfhId(String fhId);
}
