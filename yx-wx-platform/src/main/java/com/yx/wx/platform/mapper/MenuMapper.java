package com.yx.wx.platform.mapper;

import com.yx.wx.platform.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu>{

    List<Menu> selectAllOrder();
}
