package com.yx.wx.platform.controller;

import com.yx.wx.platform.service.MenuService;
import com.yx.wx.rpc.server.RpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RpcServer rpcServer;

    @RequestMapping(produces = "text/plain;charset=utf-8")
    public ModelAndView view() {
        rpcServer.getPort();
        ModelAndView view = new ModelAndView("menu");
        return view;
    }

}
