package com.fc.action;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
	
	private ExecutorService scheduler = Executors.newScheduledThreadPool(2);
	
	//存储页面信息 一页一条线程
	//private Map<String,String> pageList = new LinkedHashMap<String,String>();
	
	
	
	public CrawlerAction()
	{
		String threadStr = cfg.getPropStr(Constant.PROP_THREAD_SIZE);
		if(!Util.isEmpty(threadStr))
		{
			try
			{
				int threadSize = Integer.parseInt(threadStr);
				if(threadSize>2)
				{
					scheduler = Executors.newScheduledThreadPool(threadSize);
				}
			} catch (Exception e)
			{
				console.log(Constant.LOG_PANEL_CRAWLER, "Creat Thread Pool Error...");
				logger.info("Create Thread Pool Error:",e);
			}
		}
		
	}
	
	
	public void startup()
	{
		NextPage np = new NextPage();
		scheduler.execute(np);
	}
	
	public void shutdown()
	{
		scheduler.shutdownNow();
	}
	
	
	/**
	 * 线程取得下一页的数据列表
	 * @author lenovo
	 *
	 */
	class NextPage implements Runnable
	{
		
		
		@Override
		public void run()
		{
			try
			{
				getNextPage();
			} catch (Exception e)
			{
				console.log(Constant.LOG_PANEL_CRAWLER, "Get Page List Error...");
				logger.error("Get Page List Error,",e);
			} 
		}
		
		public synchronized void getNextPage() throws ClientProtocolException, IOException
		{
			String path = cfg.getPropStr(Constant.PROP_URL);
			String level = cfg.getPropStr(Constant.PROP_GET_LEVEL);
			path = path+level;
			CloseableHttpClient hclient = HttpClients.createDefault();
			HttpGet hget = new HttpGet(path);
			logger.info("Loading url:[" + path + "] List For Pages...");
			
			HttpResponse response = hclient.execute(hget);
			HttpEntity entity = response.getEntity();
			
			String bodyStr = EntityUtils.toString(entity,"UTF-8");
			
			// JSONP HTML
			Document doc = Jsoup.parse(bodyStr);
			logger.debug("body:"+bodyStr);
			Elements elst = doc.getElementsByClass(cfg.getPropStr(Constant.PROP_PAGE_CLASS));
			int stop_pos = 0;
			if(!Util.isEmpty(cfg.getPropStr(Constant.PROP_STOP_POS)))
			{
				stop_pos = Integer.parseInt(cfg.getPropStr(Constant.PROP_STOP_POS));
			}
			if(elst!=null && elst.size()>0)
			{
				Elements plst = elst.get(0).getElementsByTag("a");
				
				for(Element p : plst)
				{
					String txt = p.text();
					if("尾页".equals(Util.trim(txt)))
					{
						String href = p.attr("href");
						int end = Integer.parseInt(href.split("\\.")[0]);
						for(int i=1;i<=end;i++)
						{
							//断点数据
							if(i<stop_pos)
							{
								continue;
							}
							//pageList.put(i+"", i+".html");
							ImageSource is = new ImageSource(path+"/"+i+".htm",i);
							scheduler.execute(is);
						}
					}
				}
			}
		}
	}
	
}
