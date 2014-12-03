// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Jsp.java

package com.lyods.beans;

import java.util.ArrayList;
import java.util.List;

import com.lyods.utils.Util;

public class Jsp
{

	private String pkg;
	private String name;
	private String tableName;
	private String auto;
	private String singlePage;
	private String title;
	private String home;
	private String base;
	private String baseUrl;
	private String beanName;
	private String beanType;
	private String beanId;
	private List jdlst;

	public Jsp()
	{
		beanId = "id";
		jdlst = new ArrayList();
	}

	public String getPkg()
	{
		return pkg;
	}

	public void setPkg(String pkg)
	{
		this.pkg = pkg;
	}

	public String getName()
	{
		if (name == null && !Util.isEmpty(tableName))
		{
			String s[] = Util.trim(tableName).toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < s.length; i++)
				if (Util.isEmpty(tmp))
					tmp = s[i];
				else
					tmp = (new StringBuilder(String.valueOf(tmp))).append(s[i].substring(0, 1).toUpperCase()).append(s[i].substring(1)).toString();

			name = tmp;
		}
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = Util.trim(tableName);
	}

	public String getAuto()
	{
		return auto;
	}

	public void setAuto(String auto)
	{
		this.auto = Util.trim(auto);
	}

	public String getSinglePage()
	{
		return singlePage;
	}

	public void setSinglePage(String singlePage)
	{
		this.singlePage = Util.trim(singlePage);
	}

	public String getTitle()
	{
		if (title == null)
			title = (new StringBuilder(String.valueOf(getName()))).append(".title").toString();
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getHome()
	{
		return home;
	}

	public void setHome(String home)
	{
		this.home = Util.trim(home);
	}

	public String getBase()
	{
		if (base == null && !Util.isEmpty(tableName))
		{
			String s[] = Util.trim(tableName).toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < s.length; i++)
				if (Util.isEmpty(tmp))
					tmp = s[i];
				else
					tmp = (new StringBuilder(String.valueOf(tmp))).append(s[i].substring(0, 1).toUpperCase()).append(s[i].substring(1)).toString();

			base = tmp;
		}
		return base;
	}

	public void setBase(String base)
	{
		this.base = base;
	}

	public String getBaseUrl()
	{
		if (baseUrl == null && !Util.isEmpty(tableName))
		{
			String s[] = Util.trim(tableName).toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < s.length; i++)
				if (Util.isEmpty(tmp))
					tmp = s[i];
				else
					tmp = (new StringBuilder(String.valueOf(tmp))).append(s[i].substring(0, 1).toUpperCase()).append(s[i].substring(1)).toString();

			baseUrl = (new StringBuilder(String.valueOf(tmp))).append(".do").toString();
		}
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}

	public String getBeanName()
	{
		if (beanName == null && !Util.isEmpty(tableName))
		{
			String sls[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < sls.length; i++)
				if (Util.isEmpty(tmp))
					tmp = sls[i];
				else
					tmp = (new StringBuilder(String.valueOf(tmp))).append(sls[i].substring(0, 1).toUpperCase()).append(sls[i].substring(1)).toString();

			beanName = tmp;
		}
		return beanName;
	}

	public void setBeanName(String beanName)
	{
		this.beanName = beanName;
	}

	public String getBeanType()
	{
		if (beanType == null && !Util.isEmpty(tableName))
		{
			String sls[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < sls.length; i++)
				tmp = (new StringBuilder(String.valueOf(tmp))).append(sls[i].substring(0, 1).toUpperCase()).append(sls[i].substring(1)).toString();

			beanType = tmp;
		}
		return beanType;
	}

	public void setBeanType(String beanType)
	{
		this.beanType = beanType;
	}

	public String getBeanId()
	{
		return beanId;
	}

	public void setBeanId(String beanId)
	{
		this.beanId = beanId;
	}

	public List getJdlst()
	{
		return jdlst;
	}

	public void setJdlst(List jdlst)
	{
		this.jdlst = jdlst;
	}
}
