package com.xinhua.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinhua.edu.entity.EduSubject;
import com.xinhua.edu.entity.excel.SubjectData;
import com.xinhua.edu.handler.EduException;
import com.xinhua.edu.service.EduSubjectService;

/**
 * 〈一句话功能简述〉<br>
 * 〈类别监听器〉
 *
 * @author Administrator
 * @create 2020/10/14
 * @since 1.0.0
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {


    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        if(subjectData==null){
            throw new EduException(23333,"文件为空");
        }
        //判断一级分类是否重复，添加一级分类
        EduSubject exitsOneSubject = this.exitsOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if(exitsOneSubject==null){
            //没有一级分类相同，可以添加
            exitsOneSubject=new EduSubject();
            exitsOneSubject.setParentId("0");
            exitsOneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(exitsOneSubject);
        }
        //判断二级分类是否重复，添加二级分类
        String pid = exitsOneSubject.getId();
        EduSubject exitTwoSubject = this.exitsTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);

        if(exitTwoSubject==null){
            //没有二级分类重复，可以添加
            exitTwoSubject=new EduSubject();
            exitTwoSubject.setParentId(pid);
            exitTwoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(exitTwoSubject);
        }
    }
    //判断一级分类不能重复添加
    private EduSubject exitsOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject exitsTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
