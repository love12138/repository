package com.xinhua.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinhua.edu.entity.Dto;
import com.xinhua.edu.entity.Teacher;
import com.xinhua.edu.mapper.TeacherMapper;
import com.xinhua.edu.service.TeacherService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-05
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
     @Override
    public void queryPageList(Page<Teacher> pageTeacher, Dto dto) {
        //关键：Dto有传递过来的条件值，判断，如果有条件值，拼接条件
        if(dto == null) {
            //直接查询分页，不进行条件操作
            baseMapper.selectPage(pageTeacher,null);
            return;
        }
        //如果Dto不为空
        //从Dto对象里面获取出条件值
        String name = dto.getName();
        String level = dto.getLevel();
        String begin = dto.getBegin();
        String end = dto.getEnd();
        //判断条件值是否有，如果有拼接条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            //拼接条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
         wrapper.orderByDesc("gmt_create");
        //条件查询带分页
        baseMapper.selectPage(pageTeacher,wrapper);
    }
    @Override
    public boolean removeById(Serializable id) {
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

}
