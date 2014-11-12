package com.fc.action;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fc.config.Config;
import com.fc.constant.Constant;
import com.fc.frame.Console;
import com.fc.utils.Util;

public class CrawlerAction
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CrawlerAction.class);
	
	private Console cosole = Console.getInstance();
	
	private Config cfg = Config.getInstance();
	
	private ExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public CrawlerAction()
	{
		String threadStr = cfg.getPropStr(Constant.PROP_THREAD_SIZE);
		if(!Util.isEmpty(threadStr))
		{
			try
			{
				scheduler = Executors.newScheduledThreadPool(Integer.parseInt(threadStr));
			} catch (Exception e)
			{
				logger.info("Create Thread Pool Error:",e);
			}
		}
	}
	
	
	public void startup()
	{
		
	}
	
}
