package com.ry.pfb.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ry.pfb.fiter.SessionFilter;

public class PropertiesUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);
	public static String getValue(String key){
		String rt = null;
		Properties prop = new Properties();
		InputStream fis = SessionFilter.class.getResourceAsStream("/properties/web.properties");
		try {
			if(fis != null){
				prop.load(fis);
				rt = prop.getProperty(key);
			}
		} catch (IOException e) {
			LOGGER.error("读取配置出错",e);
		}
		return rt;
	}
}
