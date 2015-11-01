package com.ry.pfb.core;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import java.util.Map;

/**
 * @author junli
 */
public class DemoOAuth2Handler implements WxMpMessageHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
      WxMpService wxMpService, WxSessionManager sessionManager) {
    String href = "<a href=\"" + wxMpService.oauth2buildAuthorizationUrl("snsapi_base", null)
        + "\">测试oauth2</a>";
    return WxMpXmlOutMessage
        .TEXT()
        .content(href)
        .fromUser(wxMessage.getToUserName())
        .toUser(wxMessage.getFromUserName()).build();
  }
}
