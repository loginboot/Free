package com.fc.frame;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

/**
 * 
 * @author loginboot.vicp.net
 * 
 * @creator log boy
 * @version 1.0.0
 * @date 2014-10-22
 * @description 输出日志 - 创建
 *
 */

public class Console
{

	private Map<String, JTextArea> conMap = new HashMap<String, JTextArea>();

	private static Console console = null;

	private Console()
	{

	}

	public static synchronized Console getInstance()
	{
		if (console == null)
		{
			console = new Console();
		}
		return console;
	}

	/**
	 * 获取输出日志面板
	 * 
	 * @param key
	 * @return
	 */
	public JTextArea getArea(String key)
	{
		JTextArea ja = conMap.get(key);
		if (ja == null)
		{
			ja = new JTextArea("");
			ja.setLineWrap(true); // 激活自动换行功能
			ja.setWrapStyleWord(true); // 激活断行不断字功能
			conMap.put(key, ja);
		}
		return ja;
	}

	/**
	 * 输出日志信息
	 * 
	 * @param key
	 * @param msg
	 */
	public void log(String key, String msg)
	{
		JTextArea ja = conMap.get(key);
		if (ja != null)
		{
			ja.append(msg + "\n");
		}
	}

	/**
	 * 清空输出日志
	 * 
	 * @param key
	 */
	public void clear(String key)
	{
		JTextArea ja = conMap.get(key);
		if (ja != null)
		{
			ja.setText("");
		}
	}

}
