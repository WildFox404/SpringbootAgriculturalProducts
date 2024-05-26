package com.cow.spring_vue.mapper;

import com.cow.spring_vue.entity.Hurdle;
import com.cow.spring_vue.entity.child.HurdleUpdate;
import com.cow.spring_vue.entity.child.Hurdle_hIdhMax;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ManagerHurdlesMapper {

    @Select("SELECT COUNT(*) FROM hurdles WHERE fhId = #{fhId}")
    int selectCountByFhId(String fhId);

    @Select("SELECT * FROM hurdles WHERE fhId = #{fhId}")
    List<Hurdle> selectHurdlesByFhId(String fhId);

    @Select("SELECT * FROM hurdles WHERE hEnable != '禁用' AND hSaved < hMax AND fhId = #{fhId};")
    List<Hurdle> selectEnableHurdlesByFhId(String fhId);

    @Select("SELECT hId,hMax FROM hurdles WHERE fhId = #{fhId}")
    List<Hurdle_hIdhMax> selecthIdhMaxByFhId(String fhId);

    @Select("SELECT * FROM hurdles WHERE hId = #{hId}")
    Hurdle selectHurdleByhId(String hId);

    @Insert("INSERT INTO hurdles (hId, hName, hDesc, hMax, hSaved, hCreateTime, hUpdateTime, hEnable, hFull, fhId)" +
            " VALUES (#{hId},#{hName},#{hDesc},#{hMax},#{hSaved},now(),now(),#{hEnable},#{hFull},#{fhId})")
    int insertHurdle(Hurdle hurdle);

    @Update("UPDATE hurdles SET hEnable = #{hEnable}, hUpdateTime = now() WHERE hId = #{hId}")
    void updateEnable(String hId, String hEnable);

    @Select("SELECT DISTINCT fhId FROM hurdles;")
    List<String> queryDistinctFhId();

    @Update("UPDATE hurdles SET hName = #{hName}, hDesc = #{hDesc}, hMax = #{hMax}, hSaved = #{hSaved}, hUpdateTime = now(), hEnable = #{hEnable}, hFull = #{hFull} WHERE hId = #{hId}")
    int updateByPrimaryKey(Hurdle hurdle);

    @Update("UPDATE hurdles SET hName = #{hName}, hDesc = #{hDesc}, hMax = #{hMax}, hSaved = #{hSaved}, hUpdateTime = now(), hEnable = #{hEnable} WHERE hId = #{hId}")
    Integer updateHurdle(HurdleUpdate hurdleUpdate);

    @Delete("DELETE FROM hurdles WHERE hId = #{hId}")
    void deleteHurdle(String hId);
}
