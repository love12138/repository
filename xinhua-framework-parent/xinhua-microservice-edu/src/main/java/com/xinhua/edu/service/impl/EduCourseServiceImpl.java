package com.xinhua.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinhua.edu.entity.EduCourse;
import com.xinhua.edu.entity.EduCourseDescription;
import com.xinhua.edu.entity.vo.CourseInfoVo;
import com.xinhua.edu.handler.EduException;
import com.xinhua.edu.mapper.EduCourseMapper;
import com.xinhua.edu.service.EduCourseDescriptionService;
import com.xinhua.edu.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-16
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Override
    public void addSource(CourseInfoVo courseInfoVo) {
        //添加课程信息
        EduCourse eduCourse = new EduCourse();
        //把courseInfoVo转换成eduCourse进行添加
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int num = baseMapper.insert(eduCourse);
        if(0==num){
            throw new EduException(10005,"添加课程失败！！");
        }
        String cId = eduCourse.getId();
        //添加课程描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cId);
        eduCourseDescriptionService.save(eduCourseDescription);
    }
}
