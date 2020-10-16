package com.xinhua.edu.controller;


import com.xinhua.edu.entity.subject.OneSubject;
import com.xinhua.edu.service.EduSubjectService;
import com.xinhua.onlineedu.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;
    //添加课程分类
    @PostMapping("saveSubject")
    public R saveSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }
    //课程分类列表（树形结构实现）
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = eduSubjectService.getAllOneTwoSuject();
        return R.ok().data("list",list);
    }
}

