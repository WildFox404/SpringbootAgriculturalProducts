package com.cow.spring_vue.mapper;

import com.cow.spring_vue.entity.Batch;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BatchMapper {
    @Insert("INSERT INTO Batches (bBatchId, bTime, bQuarantine, bStatus, bLocation,userId)" +
            " VALUES (#{bBatchId}, now(), #{bQuarantine}, #{bStatus}, #{bLocation},#{userId})")
    int insert(Batch batch);

    @Update("UPDATE Batches SET bTime = now(), bQuarantine = #{bQuarantine}, bStatus = #{bStatus}, bLocation = #{bLocation} WHERE bBatchId = #{bBatchId}")
    int updateByPrimaryKey(Batch batch);

    @Update("UPDATE Batches SET bQuarantine = #{bQuarantine} WHERE bBatchId = #{grBatchId}")
    int updatebQuarantineByBatchId(String bQuarantine, String grBatchId);

    @Select("SELECT * FROM Batches where bBatchId=#{grBatchId}")
    Batch selectByBatchId(String grBatchId);

    @Select("SELECT * FROM Batches WHERE bQuarantine = #{bQuarantine} AND userId=#{userId}")
    List<Batch> selectBatchesByQuarantine(String bQuarantine,Integer userId);

    @Select("SELECT * FROM Batches WHERE userId=#{userId}")
    List<Batch> selectAll(Integer id);

    @Select("SELECT bBatchId FROM Batches WHERE userId=#{userId}")
    List<String> selectBatchIdsByUserId(Integer id);

    @Select("SELECT bBatchId FROM Batches WHERE userId = #{id}")
    List<String> selectBatchIds(Integer id);

    @Select("SELECT COUNT(*) AS total FROM Animals WHERE aBatchId = #{batchId}")
    Integer getAnimalCount(String batchId);

    @Delete("DELETE FROM Batches WHERE bBatchId = #{bBatchId}")
    void deleteById(String bBatchId);
}
