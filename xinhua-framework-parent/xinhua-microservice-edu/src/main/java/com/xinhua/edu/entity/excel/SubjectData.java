package com.xinhua.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈分类级别〉
 *
 * @author Administrator
 * @create 2020/10/14
 * @since 1.0.0
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
