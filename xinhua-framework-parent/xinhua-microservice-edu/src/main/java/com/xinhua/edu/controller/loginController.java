package com.xinhua.edu.controller;

import org.springframework.web.bind.annotation.*;
import com.xinhua.onlineedu.common.R;
/**
 * @program: xinhua-framework-parent
 * @description:
 * @author: wys
 * @create: 2020-10-07 08:36
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class loginController {
        //{"code":20000,"data":{"token":"admin-token"}}
    //登陆
    @PostMapping("login")
    public R login() {
        return R.ok().data("token","Super Admin");
    }

    //{"code":20000,"data":{"roles":["admin"],"introduction":"I am a super administrator","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif","name":"Super Admin"}}
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
