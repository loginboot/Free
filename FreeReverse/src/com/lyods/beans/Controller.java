// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Controller.java

package com.lyods.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position.Bias;

import com.lyods.utils.Util;

public class Controller
{

	private String pkg;
	private String tableName;
	private String name;
	private String home;
	private String auto;
	private String base;
	private String baseUrl;
	private String serviceType;
	private String serviceName;
	private String beanName;
	private String beanType;
	private String beanId;
	private List cdlst;


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

	public String getName()
	{
		if (name == null && !Util.isEmpty(tableName))
		{
			String sls[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < sls.length; i++)
				tmp = (new StringBuilder(String.valueOf(tmp))).append(sls[i].substring(0, 1).toUpperCase()).append(sls[i].substring(1)).toString();

			name = (new StringBuilder(String.valueOf(tmp))).append("Controller").toString();
		}
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getHome()
	{
		return home;
	}

	public void setHome(String home)
	{
		this.home = Util.trim(home);
	}

	public String getAuto()
	{
		return auto;
	}

	public void setAuto(String auto)
	{
		this.auto = Util.trim(auto);
	}

	public String getBase()
	{
		if (base == null && !Util.isEmpty(tableName))
		{
			String tps[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < tps.length; i++)
				if (Util.isEmpty(tmp))
					tmp = tps[i];
				else
					tmp = (new StringBuilder(String.valueOf(tmp))).append(tps[i].substring(0, 1).toUpperCase()).append(tps[i].substring(1)).toString();

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
			String tps[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < tps.length; i++)
				if (Util.isEmpty(tmp))
					tmp = tps[i];
				else
					tmp = (new StringBuilder(String.valueOf(tmp))).append(tps[i].substring(0, 1).toUpperCase()).append(tps[i].substring(1)).toString();

			baseUrl = (new StringBuilder(String.valueOf(tmp))).append(".do").toString();
		}
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}

	public String getServiceType()
	{
		if (serviceType == null && !Util.isEmpty(tableName))
		{
			String sls[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < sls.length; i++)
				tmp = (new StringBuilder(String.valueOf(tmp))).append(sls[i].substring(0, 1).toUpperCase()).append(sls[i].substring(1)).toString();

			serviceType = (new StringBuilder(String.valueOf(tmp))).append("Service").toString();
		}
		return serviceType;
	}

	public void setServiceType(String serviceType)
	{
		this.serviceType = serviceType;
	}

	public String getServiceName()
	{
		if (serviceName == null && !Util.isEmpty(tableName))
		{
			String sls[] = tableName.toLowerCase().split("_");
			String tmp = "";
			for (int i = 1; i < sls.length; i++)
				if (Util.isEmpty(tmp))
					tmp = sls[i];
				else
					tmp = (new StringBuilder(String.valueOf(tmp))).append(sls[i].substring(0, 1).toUpperCase()).append(sls[i].substring(1)).toString();

			serviceName = (new StringBuilder(String.valueOf(tmp))).append("Service").toString();
		}
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
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

	public List getCdlst()
	{
		return cdlst;
	}

	public void setCdlst(List cdlst)
	{
		this.cdlst = cdlst;
	}
}
