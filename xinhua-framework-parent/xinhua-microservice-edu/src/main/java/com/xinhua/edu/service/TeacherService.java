package com.xinhua.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinhua.edu.entity.Dto;
import com.xinhua.edu.entity.Teacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-05
 */
public interface TeacherService extends IService<Teacher> {
    //条件查询带分页
    void queryPageList(Page<Teacher> pageTeacher, Dto dto);
}
