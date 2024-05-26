package com.cow.spring_vue.mapper;

import com.cow.spring_vue.entity.Animal;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnimalMapper {
    @Select("SELECT * FROM animals WHERE aAnimalId = #{aAnimalId}")
    Animal selectAnimalByPrimaryKey(String aAnimalId);

    @Update("UPDATE animals SET aBatchId = #{aBatchId}, aGender = #{aGender}, aHealthy = #{aHealthy}, aStatus = #{aStatus}, aHurdlesId = #{aHurdlesId}, aInoculate = #{aInoculate}, aWeightInit = #{aWeightInit}, aWeightNow = #{aWeightNow} WHERE aAnimalId = #{aAnimalId}")
    int updateByPrimaryKey(Animal animal);

    @Insert("INSERT INTO animals (aAnimalId, aBatchId, aGender, aHealthy, aStatus, aHurdlesId, aInoculate, aTime, aWeightInit, aWeightNow) " +
            " VALUES (#{aAnimalId}, #{aBatchId}, #{aGender}, #{aHealthy}, #{aStatus}, #{aHurdlesId}, #{aInoculate}, now(), #{aWeightInit}, #{aWeightInit})")
    int insert(Animal animal);
    @Update("UPDATE animals SET aHealthy = #{aHealthy} WHERE aAnimalId = #{aAnimalId}")
    int updateAHealthyByaAnimalId(String aHealthy, String aAnimalId);

    @Select("SELECT * FROM animals WHERE abatchId = #{batchId}")
    List<Animal> selectAnimalsByBatchId(String batchId);

    @Select("SELECT * FROM animals WHERE aHurdlesId = #{aHurdlesId}")
    List<Animal> selectAnimalsByhId(String aHurdlesId);

    @Delete("DELETE FROM animals WHERE aAnimalId = #{animalId}")
    void deleteByaAnimalId(String animalId);

    @Insert("INSERT INTO animalsrecord (aAnimalId, aBatchId, aGender, aHealthy, aStatus, aHurdlesId, aInoculate, aTime, aWeightInit, aWeightNow) " +
            " VALUES (#{aAnimalId}, #{aBatchId}, #{aGender}, #{aHealthy}, #{aStatus}, #{aHurdlesId}, #{aInoculate}, now(), #{aWeightInit}, #{aWeightInit})")
    void insertrecord(Animal animal);
}
