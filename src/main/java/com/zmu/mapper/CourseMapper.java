package com.zmu.mapper;

import com.zmu.pojo.Course;
import com.zmu.pojo.CourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface CourseMapper {
    long countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(String cno);

    int insert(Course row);

    int insertSelective(Course row);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(String cno);

    int updateByExampleSelective(@Param("row") Course row, @Param("example") CourseExample example);

    int updateByExample(@Param("row") Course row, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course row);

    int updateByPrimaryKey(Course row);

    String getCnameByCpno(String cpno);
}