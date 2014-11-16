package com.fc.action;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

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
	
	private Console console = Console.getInstance();
	
	private Config cfg = Config.getInstance();
	
	private ExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private Map<String,String> pageList = new HashMap<String,String>();
	
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
	
	public void shutdown()
	{
		scheduler.shutdownNow();
	}
}
