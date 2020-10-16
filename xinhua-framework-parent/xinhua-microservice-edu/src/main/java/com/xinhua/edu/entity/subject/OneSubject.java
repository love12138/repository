package com.xinhua.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈一级分类〉
 *
 * @author Administrator
 * @create 2020/10/15
 * @since 1.0.0
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    //一个一级分类下有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
