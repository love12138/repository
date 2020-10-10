package com.xinhua.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinhua.edu.entity.Dto;
import com.xinhua.edu.entity.Teacher;
import com.xinhua.edu.service.TeacherService;
import com.xinhua.onlineedu.common.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 1.讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-05
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    /**
    * Description: 1.获取讲师信息
    * @Author:yws
    * @creatDate:2020/10/5
    */
    @GetMapping
    public R list(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    /**
     * 2.逻辑删除
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        boolean result = teacherService.removeById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    /**
     * 3 分页查询讲师列表的方法
     * @param page
     * @param limit
     * @return
     */
        @GetMapping("pageList/{page}/{limit}")
        public R getPageTeacherList(@PathVariable Long page,
                                    @PathVariable Long limit) {
            //创建page对象，传递两个参数
            Page<Teacher> pageTeacher = new Page<>(page,limit);
            //调用方法分页查询
            teacherService.page(pageTeacher,null);
            //从pageTeacher对象里面获取分页数据
            long total = pageTeacher.getTotal();
            List<Teacher> records = pageTeacher.getRecords();
            return R.ok().data("total",total).data("items",records);
        }

    /**
     * 4 多条件组合查询带分页
     * @param page
     * @param limit
     * @param dto
     * @return
     */
    @PostMapping("moreCondtionPageList/{page}/{limit}")
    public R getMoreCondtionPageList(@PathVariable Long page, @PathVariable Long limit,
                                     @RequestBody(required = false) Dto dto) {//传递json数据
        Page<Teacher> pageTeacher = new Page<>(page,limit);
        //调用service的方法实现条件查询带分页
        teacherService.queryPageList(pageTeacher,dto);
        //从pageTeacher对象里面获取分页数据
        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("items",records);
    }
   /**
   * Description: 5.添加讲师
   * @Author:yws
   * @creatDate:2020/10/6
   */
    @PostMapping("addTeacher")
    public R addTeacher( @RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return R.ok();
        }else{
            return R.error();
        }
    }
        /**
        * Description: 6.根据id查询讲师
        * @Author:yws
        * @creatDate:2020/10/6
        */
        @GetMapping("getTeacherInfo/{id}")
        public R getTeacherInfo(@PathVariable String id){
            Teacher byId = teacherService.getById(id);
            return R.ok().data("byId",byId);
        }
        /**
        * Description: 修改讲师
        * @Author:yws
        * @creatDate:2020/10/6
        */
        @PostMapping("updateTeacher/{id}")
        public  R updateTeacher( @RequestBody Teacher teacher){
            boolean b = teacherService.updateById(teacher);
            if (b) {
            return R.ok();
        }else{
            return R.error();
        }
        }
    }

