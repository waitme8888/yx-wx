package com.yx.wx.platform.test;

import com.yx.wx.Application;
import com.yx.wx.platform.model.vo.MenuVo;
import com.yx.wx.platform.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MenuTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void getMenusOrder() {
        List<MenuVo> menuList = menuService.getNavigateMenus();
        System.out.println(menuList);
    }
}
