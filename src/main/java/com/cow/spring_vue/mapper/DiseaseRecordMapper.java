package com.cow.spring_vue.mapper;

import com.cow.spring_vue.entity.DiseaseRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DiseaseRecordMapper {
    @Select("SELECT * FROM diseaseRecord WHERE drId = #{drId}")
    DiseaseRecord selectByPrimaryKey(String drId);

    @Insert("INSERT INTO diseaseRecord (drAnimalId, drCure, drDesc, drId, drStatus, drCreateTime, drEndTime) " +
            "VALUES (#{drAnimalId}, #{drCure}, #{drDesc}, #{drId}, #{drStatus}, now(), null)")
    int insert(DiseaseRecord record);

    @Update("UPDATE diseaseRecord SET drCure = #{drCure}, drDesc = #{drDesc}, drStatus = #{drStatus}, drEndTime = #{drEndTime} WHERE drId = #{drId}")
    int updateByPrimaryKey(DiseaseRecord record);
}
