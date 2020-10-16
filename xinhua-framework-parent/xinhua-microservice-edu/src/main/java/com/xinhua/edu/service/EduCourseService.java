package com.xinhua.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinhua.edu.entity.EduCourse;
import com.xinhua.edu.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-16
 */
public interface EduCourseService extends IService<EduCourse> {

    void addSource(CourseInfoVo courseInfoVo);
}
