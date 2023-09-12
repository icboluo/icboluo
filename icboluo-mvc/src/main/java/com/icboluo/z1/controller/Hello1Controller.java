package com.icboluo.z1.controller;


import com.icboluo.z1.pojo.User;
import com.icboluo.z1.pojo.UserVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class Hello1Controller {
    @RequestMapping("/show1.do")
    public ModelAndView test1() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "这是第一个springmvc程序");
        return mv;
    }

    //  @RequestMapping("/show2?")
    public ModelAndView test2() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "ant 风格的映射?");
        return mv;
    }

    @RequestMapping("s*/show3*.do")
    public ModelAndView test3() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "ant 风格的映射*");
        return mv;
    }

//    @RequestMapping("**/show4.do")
//    public ModelAndView test4() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("hello");
//        mv.addObject("msg", "ant 风格的映射**");
//        return mv;
//    }

    @RequestMapping("show5/{name}/{userId}.do")
    public ModelAndView test5(@PathVariable("name") String name, @PathVariable("userId") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "rest " + name + id);
        return mv;
    }

    //@GetMapping("show6)
    @RequestMapping(value = "show6", method = RequestMethod.POST)
    public ModelAndView test6() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "rest ");
        return mv;
    }

    @RequestMapping(value = "show7", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView test7() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "rest ");
        return mv;
    }

    @RequestMapping(value = "show8", params = {"userId"})
    public ModelAndView test8() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "rest ");
        return mv;
    }

    @RequestMapping(value = "show9", params = {"!userId"})
    public ModelAndView test9() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "rest ");
        return mv;
    }

    @RequestMapping(value = "show10", params = {"userId=100"})
    public ModelAndView test10() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "rest ");
        return mv;
    }

    @RequestMapping(value = "show11", params = {"userId!=100"})
    public ModelAndView test11() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "rest ");
        return mv;
    }

    @RequestMapping(value = "show12", params = {"userId!=100"})
    public ModelAndView test12() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "rest ");
        return mv;
    }

    @RequestMapping(value = "show13", params = {"name=7", "userId!=100"})
    public ModelAndView test13() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "rest ");
        return mv;
    }

    @RequestMapping("show17")
    public String test17(Model model) {
        model.addAttribute("msg", "string");
        return "hello";
    }

    @RequestMapping("show18")
    @ResponseStatus(HttpStatus.OK)
    public void test18() {
        System.out.println("返回值是void的方法");
    }

    @RequestMapping("show19")
    public String test19(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        model.addAttribute("msg", request + " <br/>" + response + "<br/>" + session);
        return "hello";

    }

    @RequestMapping("show20")
    public String test20(Model model, @RequestParam(value = "name", required = false, defaultValue = "zhangsan") String name) {
        model.addAttribute("msg", name);
        return "hello";
    }

    @RequestMapping("show21")
    public String test21(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    model.addAttribute("msg", cookie.getValue());
                }
            }
        }
        return "hello";
    }

    @RequestMapping("show22")
    public String test22(Model model, @CookieValue(value = "JSESSIONID", required = false) String jsessionid) {
        model.addAttribute("msg", jsessionid);
        return "hello";
    }

    @RequestMapping("show23")
    public String test23(Model model, @RequestParam("name") String name,
                         @RequestParam("age") Integer age,
                         @RequestParam("isMarry") Boolean isMarry,
                         @RequestParam("income") Float income,
                         @RequestParam("interests") String[] interests) {
        model.addAttribute("msg", name + "<br/>" + age + "<br/>" + isMarry + "<br/>" + income + "<br/>" + Arrays.toString(interests) + "<br/>");
        return "hello";
    }

    @RequestMapping("show24")
    public String test24(Model model, User user, @RequestParam("name") String name) {
        model.addAttribute("msg", user);
        return "hello";
    }

    @RequestMapping("show25")
    public String test25(Model model, User user, @RequestParam("ids") List<Integer> ids) {
        model.addAttribute("msg", ids);
        return "hello";
    }

    @RequestMapping("show26")
    public String test26(Model model, UserVO userVO) {
        model.addAttribute("msg", userVO);
        return "hello";
    }

    @RequestMapping("show27")
    public String test27(Model model) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(i + 1L);
            user.setUsername("admin" + i);
            user.setName("用戶" + i);
            user.setAge(18 + i);
            users.add(user);
        }
        model.addAttribute("users", users);
        return "userList";
    }

    @RequestMapping("show28")
    @ResponseBody
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

    @RequestMapping("show29")
    public String test29(Model model, @RequestBody User user) {
        model.addAttribute("msg", user);
        return "hello";
    }

    @RequestMapping("show30")
    public String test30(Model model, @RequestBody String user) {
        model.addAttribute("msg", user);
        return "hello";
    }

    @RequestMapping("show31")
    public String test31(Model model, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            file.transferTo(new File("d://fff/" + file.getOriginalFilename()));
        }
        model.addAttribute("msg", "上传成功");
        return "hello";
    }

    @RequestMapping("show32")
    public String test32() throws IOException {

        System.out.println(32);
        return "forward:show34.do?type=forward";
    }

    @RequestMapping("show33")
    public String test33() throws IOException {

        System.out.println(33);
        return "redirect:show34.do?type=redirect";
    }

    @RequestMapping("show34")
    public String test34(Model model, @RequestParam("type") String type) throws IOException {

        System.out.println(34);
        model.addAttribute("msg", type);
        return "hello";
    }

    @RequestMapping("show35")
    public String test35() throws IOException {

        System.out.println(35);

        return "hello";
    }

    @RequestMapping("show36")
    public String test36(@RequestParam("name") String name) {

        System.out.println(name);

        return "hello";
    }
}
