package com.fc.constant;

import java.io.File;

/**
 * 
 * @author loginboot.vicp.net
 *
 * @creator log boy
 * @version 1.0.0
 * @date 2014-10-22
 * @description 常量类 - 创建
 * 
 */

public class Constant
{
	// 配置文件目录
	public static final String CONFIG_PATH = "./config";
	// Log4j配置文件
	public static final String LOG4J_PATH_FILE = CONFIG_PATH + File.separator + "log4j.properties";
	// 参数配置文件
	public static final String PROP_PATH_FILE = CONFIG_PATH + File.separator + "Crawler.ini";
	// License
	public static final String LICENSE_PATH_FILE = CONFIG_PATH + File.separator + "license.key";
	// 日志对象面板
	public static final String LOG_PANEL_CRAWLER = "CRAWLER";

	// 抓取路径
	public static final String PROP_URL = "URL";
	// 保存目录
	public static final String PROP_SAVE_PATH = "SAVE_PATH";
	// 线程个数
	public static final String PROP_THREAD_SIZE = "THREAD_SIZE";
	// 是否自动生成名称
	public static final String PROP_STOP_POS = "STOP_POS";
	// 是否按路径生成子目录
	public static final String PROP_SUB_FOLDER = "SUB_FOLDER";
	// 按哪种类生文件名称
	public static final String PROP_SEQ_UUID = "SEQ_UUID";
	// 抓取范围
	public static final String PROP_BODY_CLASS = "BODY_CLASS";
	// 页码范围
	public static final String PROP_PAGE_CLASS = "PAGE_CLASS";
	// 全页查找层级
	public static final String PROP_GET_LEVEL = "GET_LEVEL";

}
