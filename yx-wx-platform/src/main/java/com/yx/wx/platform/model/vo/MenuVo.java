package com.yx.wx.platform.model.vo;

import com.yx.wx.platform.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuVo extends Menu {

    private List<Menu> children = new ArrayList<>();

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
