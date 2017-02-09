package com.yx.wx.platform.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.wx.platform.model.User;
import com.yx.wx.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(produces = "text/plain;charset=utf-8")
    public ModelAndView view() {
        ModelAndView view = new ModelAndView("user");
        return view;
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> getUserList(User user,
                                           @RequestParam("searchType") String searchType,
                                           @RequestParam("searchValue") String searchValue) {

        Map<String, Object> result = new HashMap<>();
        PageInfo<User> pageInfo = null;
        if (user.getPageNum() != null && user.getPageSize() != null) {
            PageHelper.startPage(user.getPageNum(), user.getPageSize());
        }
        // 增加查询条件
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        if (!StringUtils.isEmpty(searchType) && !StringUtils.isEmpty(searchValue)) {
            criteria.andLike(searchType, "%" + searchValue + "%");
        }
        List<User> list = userService.selectByCondition(condition);
        if (!CollectionUtils.isEmpty(list)) {
            pageInfo = new PageInfo<>(list);
        }
        result.put("pageInfo", pageInfo);
        result.put("success", true);
        result.put("msg", "ok");
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam("id") Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.deleteById(id);
            result.put("success", true);
            result.put("msg", "ok");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "删除用户失败！");
        }

        return result;
    }

    @RequestMapping("/{id}")
    public ModelAndView getUser(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("user-edit");
        User user = userService.selectById(id);
        if (user==null) {
            user = new User();
        }
        view.addObject("user", user);
        return view;
    }

    @RequestMapping("/save")
    public ModelAndView save(User user) {
        if (user.getId()==null) {
            userService.save(user);
        } else {
            userService.updateNotNull(user);
        }
        return view();
    }
}
