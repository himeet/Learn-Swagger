package com.glj.controller;

import com.glj.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Hello控制器")
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/hi")
    public String hi() {
        return "hello";
    }

    /**
     * 只要接口中，返回值存在实体类，就会被扫描到Swagger中
     * @return
     */
    @GetMapping(value = "/user")
    public User user() {
        return new User();
    }

    @ApiOperation("登录方法")
    @PostMapping("/login")
    public String login(@ApiParam("用户名") @RequestBody String userName) {
        System.out.println("用户"+ userName + "登录成功！");
        return "login success";
    }

}
