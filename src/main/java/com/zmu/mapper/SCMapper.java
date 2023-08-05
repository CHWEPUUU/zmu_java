package com.zmu.mapper;

import com.zmu.pojo.SC;
import com.zmu.pojo.SCExample;
import com.zmu.pojo.SCKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface SCMapper {
    long countByExample(SCExample example);

    int deleteByExample(SCExample example);

    int deleteByPrimaryKey(SCKey key);

    int insert(SC row);

    int insertSelective(SC row);

    List<SC> selectByExample(SCExample example);

    SC selectByPrimaryKey(SCKey key);

    int updateByExampleSelective(@Param("row") SC row, @Param("example") SCExample example);

    int updateByExample(@Param("row") SC row, @Param("example") SCExample example);

    int updateByPrimaryKeySelective(SC row);

    int updateByPrimaryKey(SC row);
}