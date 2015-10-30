package com.ry.pfb.core;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

import java.util.Map;

/**
 * @author junli
 */
public class DemoTextHandler implements WxMpMessageHandler {

  private String content;
  
  public DemoTextHandler(String content) {
	this.content = content;
  }
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
      WxMpService wxMpService, WxSessionManager sessionManager) {
    WxMpXmlOutTextMessage m
        = WxMpXmlOutMessage.TEXT().content(content).fromUser(wxMessage.getToUserName())
        .toUser(wxMessage.getFromUserName()).build();
    return m;
  }

}
