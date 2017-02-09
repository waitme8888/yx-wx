package com.yx.wx.platform.controller;

import com.yx.wx.platform.model.vo.MenuVo;
import com.yx.wx.platform.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class IndexControllor {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/index")
    public ModelAndView index() {
        List<MenuVo> menuVoList = menuService.getNavigateMenus();
        ModelAndView view = new ModelAndView("index");
        view.addObject("menus", menuVoList);
        return view;
    }

}
