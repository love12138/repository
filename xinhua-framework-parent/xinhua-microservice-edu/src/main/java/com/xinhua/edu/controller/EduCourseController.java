package com.xinhua.edu.controller;


import com.xinhua.edu.entity.vo.CourseInfoVo;
import com.xinhua.edu.service.EduCourseService;
import com.xinhua.onlineedu.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-16
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;
    //添加课程信心
    @PostMapping("saveCource")
    public R saveCource(@RequestBody CourseInfoVo courseInfoVo){
        String cId = eduCourseService.addSource(courseInfoVo);
        return R.ok().data("cId",cId);
    }

}

