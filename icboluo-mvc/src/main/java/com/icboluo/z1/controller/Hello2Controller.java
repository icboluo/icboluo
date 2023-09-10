package com.icboluo.z1.controller;

import com.icboluo.z1.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class Hello2Controller {
    @RequestMapping("demo1")
    public List<User> test28() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(i + 1L);
            user.setUsername("admin" + i);
            user.setName("用戶" + i);
            user.setAge(18 + i);
            users.add(user);
        }
        return users;
    }

    @RequestMapping("demo2")
    public String demo2(Model model) {
        model.addAttribute("msg", "demo2");
        return "hello";
    }
}
