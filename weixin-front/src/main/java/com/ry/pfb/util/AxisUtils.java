package com.ry.pfb.util;

import java.util.Arrays;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.ry.pfb.common.Result;

import me.chanjar.weixin.common.util.StringUtils;


public class AxisUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AxisUtils.class);

	static final String Qname = "http://weixin.inter.camel.nfsschina.com/";

	static final String targetUrl = "http://test-service.peifubao.com/WeiXinWebServiceBeanService/WeiXinWebServiceBean?WSDL";

	static String privateKey = "a*jal8czxjavapfb";
	
	public static Object axisClient(List<String> param,String method){
		Object result = null;
		if(CollectionUtils.isEmpty(param) || StringUtils.isBlank(method)){
			return null;
		}
		// 使用RPC方式调用WebService
		RPCServiceClient serviceClient;
		try {
			serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			// 指定调用WebService的URL
			EndpointReference targetEPR = new EndpointReference(targetUrl);
			options.setTo(targetEPR);
			options.setTimeOutInMilliSeconds(new Long(1000000000));
			Object[] entryArgs = new Object[param.size()];
			for(int i=0;i<param.size();i++){
				entryArgs[i] = AesUtil.encrypt(param.get(i), privateKey);
			}
			Class[] classes = new Class[] { String.class };
			// 方法
			QName opName = new QName(Qname, method);
			result = serviceClient.invokeBlocking(opName, entryArgs, classes)[0];
			LOGGER.info("返回值："+result);
		} catch (AxisFault e) {
			LOGGER.error("接口读取异常：",e);
		}
		return result;
	}
	
	public static void main(String args[]){
	//	axisClient(Arrays.asList("zsbr","123456"),"checkLogin");
		
		
	}
}
