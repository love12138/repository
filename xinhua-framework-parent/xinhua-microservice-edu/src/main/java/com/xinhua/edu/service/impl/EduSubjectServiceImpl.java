package com.xinhua.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinhua.edu.entity.EduSubject;
import com.xinhua.edu.entity.excel.SubjectData;
import com.xinhua.edu.entity.subject.OneSubject;
import com.xinhua.edu.entity.subject.TwoSubject;
import com.xinhua.edu.listener.SubjectExcelListener;
import com.xinhua.edu.mapper.EduSubjectMapper;
import com.xinhua.edu.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
//添加课程分类

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {

        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSuject() {
        //1 查出所有的一级分类 select * from Subject where parent_id = 0
        QueryWrapper<EduSubject> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id","0");
        List<EduSubject> oneSubjects = baseMapper.selectList(oneWrapper);

        //2 查出所有的二级分类
        QueryWrapper<EduSubject> twoWrapper = new QueryWrapper<>();
        twoWrapper.ne("parent_id","0");
        List<EduSubject> twoSubjects = baseMapper.selectList(twoWrapper);
        //3 封装到一级分类
        List<OneSubject> ones = new ArrayList<>();
            //循环把EduSubject封装到一级分类
            for(EduSubject oneSubject : oneSubjects){
                OneSubject one = new OneSubject();
                BeanUtils.copyProperties(oneSubject,one);
                ones.add(one);
                List<TwoSubject> twos = new ArrayList<>();
                for(EduSubject twoSubject:twoSubjects){
                    TwoSubject two = new TwoSubject();
                    if(twoSubject.getParentId().equals(oneSubject.getId())){
                        //3 封装到二级分类
                        BeanUtils.copyProperties(twoSubject,two);
                        twos.add(two);
                    }
                }
                //4 把所有一级分类的二级分类放到一级分类当中
                one.setChildren(twos);
            }
        return ones;
    }
}
