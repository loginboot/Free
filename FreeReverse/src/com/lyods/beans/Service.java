// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Service.java

package com.lyods.beans;

import java.util.ArrayList;
import java.util.List;

import com.lyods.utils.Util;

public class Service
{

	private String pkg;
	private String tableName;
	private String name;
	private String auto;
	private String daoType;
	private String daoName;
	private String beanName;
	private String beanType;
	private String beanId;
	private List sdlst;

	public Service()
	{
		beanId = "id";
		sdlst = new ArrayList();
	}

	public String getPkg()
	{
		return pkg;
	}

	public void setPkg(String pkg)
	{
		this.pkg = pkg;
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

	public String getName()
	{
		if (name == null && !Util.isEmpty(tableName))
		{
			String sls[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < sls.length; i++)
				tmp = (new StringBuilder(String.valueOf(tmp))).append(sls[i].substring(0, 1).toUpperCase()).append(sls[i].substring(1)).toString();

			name = (new StringBuilder(String.valueOf(tmp))).append("Service").toString();
		}
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDaoType()
	{
		if (daoType == null && !Util.isEmpty(tableName))
		{
			String sls[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < sls.length; i++)
				tmp = (new StringBuilder(String.valueOf(tmp))).append(sls[i].substring(0, 1).toUpperCase()).append(sls[i].substring(1)).toString();

			daoType = (new StringBuilder(String.valueOf(tmp))).append("Dao").toString();
		}
		return daoType;
	}

	public void setDaoType(String daoType)
	{
		this.daoType = daoType;
	}

	public String getDaoName()
	{
		if (daoName == null && !Util.isEmpty(tableName))
		{
			String sls[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < sls.length; i++)
				if (Util.isEmpty(tmp))
					tmp = sls[i];
				else
					tmp = (new StringBuilder(String.valueOf(tmp))).append(sls[i].substring(0, 1).toUpperCase()).append(sls[i].substring(1)).toString();

			daoName = (new StringBuilder(String.valueOf(tmp))).append("Dao").toString();
		}
		return daoName;
	}

	public void setDaoName(String daoName)
	{
		this.daoName = daoName;
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

	public List getSdlst()
	{
		return sdlst;
	}

	public void setSdlst(List sdlst)
	{
		this.sdlst = sdlst;
	}
}
