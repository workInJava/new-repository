package com.ry.pfb.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ry.pfb.controller.CustomerController;

import me.chanjar.weixin.common.util.StringUtils;

public class WebServiceClient {

	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	/**
	 * 访问服务
	 * @param wsdl wsdl地址
	 * @param ns 命名空间
	 * @param method 方法名
	 * @param params 参数
	 * @return
	 * @throws Exception
	 */
	public synchronized static String accessService(String wsdl,String ns,String method,Map<String,String> params)throws Exception{
		//拼接参数
		String param = getParam(params);
		String soapResponseData = "";
		//拼接SOAP
		StringBuffer soapRequestData = new StringBuffer("");
		soapRequestData.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		soapRequestData.append("<soap:Body>");
		soapRequestData.append("<ns1:"+method+" xmlns:ns1=\""+ns+"\">");
		soapRequestData.append(param);
		soapRequestData.append("</ns1:"+method+">");
		soapRequestData.append("</soap:Body>" + "</soap:Envelope>");
		PostMethod postMethod = new PostMethod(wsdl);
		// 然后把Soap请求数据添加到PostMethod中
		byte[] b = null;
		InputStream is = null;
		try {
			//b =encrypt(soapRequestData.toString());
			b=soapRequestData.toString().getBytes("utf-8"); 
			is = new ByteArrayInputStream(b, 0, b.length);
			RequestEntity re = new InputStreamRequestEntity(is, b.length,"text/xml; charset=UTF-8");
			postMethod.setRequestEntity(re);
			HttpClient httpClient = new HttpClient();
			int status = httpClient.executeMethod(postMethod);
			LOGGER.info("连接请求结果："+status);
			if(status==200){
				soapResponseData = getMesage(postMethod.getResponseBodyAsString());
			}
		} catch (Exception e) {
			LOGGER.error("客户端请求出错",e);
		} finally{
			if(is!=null){
				is.close();
			}
		}
		return soapResponseData;
	}
	
	public static String getParam(Map<String,String> params){
		String param = "";
		if(params!=null){
			Iterator<String> it  = params.keySet().iterator();
			while(it.hasNext()){
				String str = it.next();
				param+="<"+str+">";
				param+=params.get(str);
				param+="</"+str+">";
			}
		}
		return param;
	}
	
    /**
     * 加密
     * 
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    public static byte[] encrypt(String content) {
    	 String password = "a*jal8czxjavapfb";
            try {           
                    KeyGenerator kgen = KeyGenerator.getInstance("AES");
                    kgen.init(128, new SecureRandom(password.getBytes()));
                    SecretKey secretKey = kgen.generateKey();
                    byte[] enCodeFormat = secretKey.getEncoded();
                    SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                    Cipher cipher = Cipher.getInstance("AES");// 创建密码器
                    byte[] byteContent = content.getBytes("utf-8");
                    cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
                    byte[] result = cipher.doFinal(byteContent);
                    return result; // 加密
            } catch (Exception e) {
           
            }
            return null;
    }
	/**
	 * soapAttachment:
	 * <?xml version='1.0' encoding='UTF-8'?>
	 * 	<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
	 * 		<S:Body>
	 * 			<ns2:sendActiveMessageResponse xmlns:ns2="http://weixin.inter.camel.nfsschina.com/">
	 * 				<return>
	 * 					{&quot;result&quot;:false,&quot;resultMessage&quot;:&quot;没有对应账户&quot;}
	 * 				</return>
	 * 			</ns2:sendActiveMessageResponse>
	 * 		</S:Body>
	 * </S:Envelope>
	 * @param soapAttachment
	 * @return
	 */
	public static String getMesage(String soapAttachment){
		String result = soapAttachment;
		if(StringUtils.isBlank(soapAttachment)){
			return result;
		}
		//String 
		
		
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		try {
			Map<String,String> param = new HashMap<String,String>();
			param.put("username", "www");
			param.put("password", "11111");
			param.put("iccode", "342224197308261623");
			String wsdl="http://test-service.peifubao.com/WeiXinWebServiceBeanService/WeiXinWebServiceBean?WSDL";
			String ns = "http://weixin.inter.camel.nfsschina.com/";
			String method="activeAccount";
			String response =accessService(wsdl,ns,method,param);
			System.out.println("response:"+response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
