package com.ry.pfb.core;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

public class WxMpServerListener extends ContextLoaderListener {
	
	public static WxMpConfigStorage wxMpConfigStorage;
    public static WxMpService wxMpService;
    public static WxMpMessageRouter wxMpMessageRouter;

	@Override
	public void contextInitialized(ServletContextEvent event){
		this.init();
		super.contextInitialized(event);
	}
	
	private void init(){
		// 读取公众号信息
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

	    WxMenu menu = WxMpMenu.menuCreate();
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
	          .rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_CLICK).eventKey("ABOUTAS").handler(oauth2handler).end()
	          .rule().async(false).content("哈哈").handler(textHandler).end()
	          .rule().async(false).content("图片").handler(imageHandler).end()
	          .rule().async(false).content("oauth").handler(oauth2handler).end();
	}
}
