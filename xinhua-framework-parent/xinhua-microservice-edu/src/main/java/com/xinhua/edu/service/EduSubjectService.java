package com.xinhua.edu.service;

import com.xinhua.edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinhua.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSuject();
}
