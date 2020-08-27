package com.icboluo.controller;

import com.icboluo.service.UserService;
import com.icboluo.util.R;
import com.icboluo.util.Response;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author icboluo
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "用户")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/login")
    public boolean login(@RequestParam String name, @RequestParam String password) {
        return userService.validate(name,password);
    }

    @GetMapping("/updatePassword")
    public boolean updatePassword(@RequestParam Integer id ,@RequestParam String password) {
        return userService.updatePassword(id, password);
    }

    @GetMapping("/ddd")
    public Response dddd() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(1);
        return R.correct(integers,integers,integers,integers).page();
    }
}
