package com.ry.pfb.core;

import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;

public class WxMpMenu {
	public static WxMenu menuCreate() {
		// 设置菜单
	    WxMenu menu = new WxMenu();
	    WxMenuButton button1 = new WxMenuButton();
	    button1.setName("保险管理");
	    
	    WxMenuButton button2 = new WxMenuButton();
	    button2.setName("个人中心");
	    
	    menu.getButtons().add(button1);
	    menu.getButtons().add(button2);
	    
	    WxMenuButton button11 = new WxMenuButton();
	    button11.setType("view");
	    button11.setName("我的保单");
	    button11.setUrl("http://baidu.com");
	    
	    WxMenuButton button12 = new WxMenuButton();
	    button12.setType("view");
	    button12.setName("自助理赔");
	    button12.setUrl("http://baidu.com");
	    
	    WxMenuButton button13 = new WxMenuButton();
	    button13.setType("view");
	    button13.setName("理赔查询");
	    button13.setUrl("http://baidu.com");
	    
	    button1.getSubButtons().add(button11);
	    button1.getSubButtons().add(button12);
	    button1.getSubButtons().add(button13);
	    
	    WxMenuButton button21 = new WxMenuButton();
	    button21.setType("view");
	    button21.setName("绑定账户");
	    button21.setUrl("http://junlihappy.6655.la/weixin-front/customer/gologin");
	    
	    WxMenuButton button22 = new WxMenuButton();
	    button22.setType("view");
	    button22.setName("在线咨询");
	    button22.setUrl("http://junlihappy.6655.la/weixin-front/customer/ask");
	    
	    WxMenuButton button23 = new WxMenuButton();
	    button23.setType("view");
	    button23.setName("关于我们");
	    button23.setUrl("http://junlihappy.6655.la/weixin-front/customer/aboutus");
	    
	    button2.getSubButtons().add(button21);
	    button2.getSubButtons().add(button22);
	    button2.getSubButtons().add(button23);
	    
	    return menu;
	}
}
