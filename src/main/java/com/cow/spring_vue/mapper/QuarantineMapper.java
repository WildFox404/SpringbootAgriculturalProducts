package com.cow.spring_vue.mapper;

import com.cow.spring_vue.entity.QuarantineRegistration;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface QuarantineMapper {

    @Insert("INSERT INTO QuarantineRecord (grId, bQualified, grBatchId, grImg, grMechanism, grTime)" +
            " VALUES (#{grId}, #{bQualified}, #{grBatchId}, #{grImg}, #{grMechanism}, now())")
    int insert(QuarantineRegistration qr);

    @Update("UPDATE QuarantineRecord SET bQualified = #{bQualified}, grBatchId = #{grBatchId}, grImg = #{grImg}, grMechanism = #{grMechanism}, grTime = now() WHERE grId = #{grId}")
    int updateByPrimaryKey(QuarantineRegistration qr);
}
