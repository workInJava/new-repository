package com.ry.pfb.core;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

public class WxMpServerUtil extends ContextLoaderListener{
	
	public static WxMpConfigStorage wxMpConfigStorage;
	public static WxMpService wxMpService;
	public static WxMpMessageRouter wxMpMessageRouter;
	
	@Override
	public void contextInitialized(ServletContextEvent event){
		this.initWxMpServer();
		super.contextInitialized(event);
	}
	

	private void initWxMpServer(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream is1 = classLoader.getResourceAsStream("test-config.xml");
	    WxMpDemoInMemoryConfigStorage config = WxMpDemoInMemoryConfigStorage.fromXml(is1);

	    wxMpConfigStorage = config;
	    wxMpService = new WxMpServiceImpl();
	    wxMpService.setWxMpConfigStorage(config);
	    
	    // handler
	    WxMpMessageHandler subScribeHander = new DemoTextHandler("你好，欢迎关注peifubao!");
	    WxMpMessageHandler logHandler = new DemoLogHandler();
	    WxMpMessageHandler textHandler = new DemoTextHandler("测试成功．．．");
	    WxMpMessageHandler imageHandler = new DemoImageHandler();
	    WxMpMessageHandler oauth2handler = new DemoOAuth2Handler();

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
	    button21.setUrl("http://baidu.com");
	    
	    WxMenuButton button22 = new WxMenuButton();
	    button22.setType("view");
	    button22.setName("在线咨询");
	    button22.setUrl("http://baidu.com");
	    
	    WxMenuButton button23 = new WxMenuButton();
	    button23.setType("view");
	    button23.setName("关于我们");
	    button23.setUrl("http://baidu.com");
	    
	    button2.getSubButtons().add(button21);
	    button2.getSubButtons().add(button22);
	    button2.getSubButtons().add(button23);
	    
	    try {
			wxMpService.menuCreate(menu);
		} catch (WxErrorException e) {
			System.out.println("创建菜单失败...");
			e.printStackTrace();
		}
	    
	    // 微信路由
	    wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
	      wxMpMessageRouter
	          .rule().handler(logHandler).next()
	          .rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_SUBSCRIBE).eventKey("").handler(subScribeHander).end()
	          .rule().async(false).content("哈哈").handler(textHandler).end()
	          .rule().async(false).content("图片").handler(imageHandler).end()
	          .rule().async(false).content("oauth").handler(oauth2handler).end();
	}
}
