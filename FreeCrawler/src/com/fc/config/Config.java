package com.fc.config;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.fc.constant.Constant;

public class Config
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Config.class);

	private static Config config = null;

	// 配置参数文件加载
	private Properties prop = new Properties();

	static
	{
		PropertyConfigurator.configure(Constant.LOG4J_PATH_FILE);
	}

	private Config()
	{
		load();
	}

	public synchronized static Config getInstance()
	{
		if (config == null)
		{
			config = new Config();
		}
		return config;
	}

	/**
	 * 初始化配置文件
	 */
	private void load()
	{
		try
		{
			InputStream in = new FileInputStream(Constant.PROP_PATH_FILE);
			prop.load(in);
			in.close();
			logger.info("Loading Parameter successful...");
		} catch (Exception e)
		{
			logger.error("Loading Parameter Error:", e);
		}
	}

	/**
	 * 取得配置文件参数
	 * 
	 * @param key
	 * @return
	 */
	public String getPropStr(String key)
	{
		return this.prop.getProperty(key);
	}

}
