package com.yx.wx.platform.service;

import com.yx.wx.platform.mapper.MenuMapper;
import com.yx.wx.platform.model.Menu;
import com.yx.wx.platform.model.vo.MenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService extends BaseService<Menu> {

    @Autowired
    private MenuMapper menuMapper;

    public List<MenuVo> getNavigateMenus() {
        List<MenuVo> menus = new ArrayList<>();
        List<Menu> menuList = menuMapper.selectAllOrder();
        if (!CollectionUtils.isEmpty(menuList)) {
            for (Menu menu : menuList) {
                MenuVo menuVo = new MenuVo();
                BeanUtils.copyProperties(menu, menuVo);
                if (menu.getParentId() == -1) {
                    menus.add(menuVo);
                } else {
                    for (MenuVo m : menus) {
                        if(m.getId() == menuVo.getParentId()) {
                            m.getChildren().add(menuVo);
                        }
                    }
                }
            }
        }
        return menus;
    }
}
