// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Dao.java

package com.lyods.beans;

import java.util.ArrayList;
import java.util.List;

import com.lyods.utils.Util;

public class Dao
{

	private String tableName;
	private String name;
	private String pkg;
	private String page;
	private String beanName;
	private List ddlst;

	public Dao()
	{
		ddlst = new ArrayList();
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getName()
	{
		if (name == null && !Util.isEmpty(tableName))
		{
			String str = tableName.toLowerCase();
			String ls[] = str.split("_");
			String tmp = "";
			for (int i = 1; i < ls.length; i++)
				if (!Util.isEmpty(ls[i]))
					tmp = (new StringBuilder(String.valueOf(tmp))).append(ls[i].substring(0, 1).toUpperCase()).append(ls[i].substring(1)).toString();

			name = (new StringBuilder(String.valueOf(tmp))).append("Dao").toString();
		}
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPkg()
	{
		return pkg;
	}

	public void setPkg(String pkg)
	{
		this.pkg = pkg;
	}

	public String getPage()
	{
		return page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}

	public String getBeanName()
	{
		if (beanName == null)
		{
			String str = tableName.toLowerCase();
			String ls[] = str.split("_");
			String tmp = "";
			for (int i = 1; i < ls.length; i++)
				if (!Util.isEmpty(ls[i]))
					tmp = (new StringBuilder(String.valueOf(tmp))).append(ls[i].substring(0, 1).toUpperCase()).append(ls[i].substring(1)).toString();

			beanName = tmp;
		}
		return beanName;
	}

	public void setBeanName(String beanName)
	{
		this.beanName = beanName;
	}

	public List getDdlst()
	{
		return ddlst;
	}

	public void setDdlst(List ddlst)
	{
		this.ddlst = ddlst;
	}
}
