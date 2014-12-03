// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ServiceDetail.java

package com.lyods.beans;


public class ServiceDetail
{

	private String name;
	private String params;
	private String type;
	private String resutl;

	public ServiceDetail()
	{
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getParams()
	{
		return params;
	}

	public void setParams(String params)
	{
		this.params = params;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getResutl()
	{
		return resutl;
	}

	public void setResutl(String resutl)
	{
		this.resutl = resutl;
	}
}
