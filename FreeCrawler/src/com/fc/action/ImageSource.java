package com.fc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
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

/**
 * 
 * @author loginboot.vicp.net
 * 
 * @creator xiesw
 * @version 1.0.0
 * @date 2014-11-24
 * @description 网页抓取图片信息
 *
 */

public class ImageSource implements Runnable
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ImageSource.class);
	private static Console console = Console.getInstance();

	public static Config cfg = Config.getInstance();

	private String uri;
	private CloseableHttpClient hclient;
	private RequestConfig reqCfg;
	private int socketTimeout = 30000; // 请求超时
	private int connTimeout = 60000; // 数据传输超时

	private String ext_app = ".jpg";// 文件后缀

	public ImageSource(String uri)
	{
		this.uri = uri;
		hclient = HttpClients.createDefault();
		// 设置请求和传输超时时间
		reqCfg = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connTimeout).build();
	}

	@Override
	public void run()
	{
		try
		{
			saveFile();
			hclient.close();
		} catch (Exception e)
		{
			console.log(Constant.LOG_PANEL_CRAWLER, "Get Image From:[" + uri + "] Error...");
			logger.error("Get Image From:[" + uri + "] Error:", e);
		}
	}

	/**
	 * 抓取网页图片列表信息
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private void saveFile() throws ClientProtocolException, IOException
	{
		HttpGet hget = new HttpGet(uri);
		logger.info("Loading url:[" + uri + "] List For Images...");
		console.log(Constant.LOG_PANEL_CRAWLER, "Loading url:[" + uri + "] List For Images...");
		hget.setConfig(reqCfg);
		HttpResponse response = hclient.execute(hget);
		HttpEntity entity = response.getEntity();
		String bodyStr = EntityUtils.toString(entity,"UTF-8");

		// JSONP HTML
		Document doc = Jsoup.parse(bodyStr);
		Elements elst = doc.getElementsByClass(cfg.getPropStr(Constant.PROP_BODY_CLASS));
		logger.debug("body list size:"+elst.size());
		String http = cfg.getPropStr(Constant.PROP_URL);
		if(http.endsWith("/"))
		{
			http = http.substring(0,http.length()-1);
		}
		for (Element el : elst)
		{
			Elements tmplst = el.getElementsByTag("li");
			for(Element tmp : tmplst)
			{
				Elements alst = tmp.getElementsByTag("a");
				if(alst!=null && alst.size()>0)
				{
					Element am = alst.get(0);
					String href = am.attr("href");
					if(!Util.isEmpty(href))
					{
						String url = http+href;
						parseImageHtml(url);
					}
				}
			}
		}

	}
	
	/**
	 * 抓取图片网页信息
	 * @param uri
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private void parseImageHtml(String uri) throws ClientProtocolException, IOException
	{
		HttpGet hget = new HttpGet(uri);
		logger.info("Loading image list url:[" + uri + "]...");
		console.log(Constant.LOG_PANEL_CRAWLER, "Loading image list url:[" + uri + "]...");
		hget.setConfig(reqCfg);
		HttpResponse response = hclient.execute(hget);
		HttpEntity entity = response.getEntity();
		String bodyStr = EntityUtils.toString(entity,"UTF-8");

		
		String path = cfg.getPropStr(Constant.PROP_SAVE_PATH);
		String folder = cfg.getPropStr(Constant.PROP_SUB_FOLDER);
		
		// JSONP HTML
		Document doc = Jsoup.parse(bodyStr);
		Elements melst = doc.getElementsByTag("title");
		String title = "";
		if(melst!=null && melst.size()>0)
		{
			Element me = melst.get(0);
			String txt = me.text();
			title = Util.trim( Util.trim(txt).split("-")[0]);
		}
		if ("true".equalsIgnoreCase(folder) && !Util.isEmpty(title))
		{
			File fp = new File(path+File.separator+title);
			if(!fp.exists())
			{
				fp.mkdirs();
			}
			path = fp.getAbsolutePath();
		}
		
		Elements elst = doc.getElementsByTag("img");
		for(Element el : elst)
		{
			String src = el.attr("src");
			if(Util.isEmpty(src))
			{
				continue;
			}
			parseImage(src,path);
		}
		
	}
	

	/**
	 * 逐个图片保存
	 * @param uri
	 * @param path
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private void parseImage(String uri, String path) throws ClientProtocolException, IOException
	{
		HttpGet hget = new HttpGet(uri);
		logger.debug("Get Image Source uri:[" + uri + "]...");
		hget.setConfig(reqCfg);
		HttpResponse response = hclient.execute(hget);
		HttpEntity entity = response.getEntity();

		String name = "";
		if ("UUID".equalsIgnoreCase(cfg.getPropStr(Constant.PROP_SEQ_UUID)))
		{
			name = UUID.randomUUID().toString() + ext_app;
		} else
		{
			File fpath = new File(path);
			File[] flst = fpath.listFiles();
			if (flst == null)
			{
				name = Util.leftFillZero(String.valueOf(0), 5) + ext_app;
			} else
			{
				name = Util.leftFillZero(String.valueOf(flst.length), 5) + ext_app;
			}
		}
		logger.debug("The image name is:[" + name + "]....");
		// 文件输出
		OutputStream out = new FileOutputStream(path + File.separator + name);
		byte[] tmp = new byte[1024 * 4];
		int len = 0;
		InputStream in = entity.getContent();
		while ((len = in.read(tmp)) != -1)
		{
			out.write(tmp, 0, len);
		}
		in.close();
		out.close();
	}

}
