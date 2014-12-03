
package com.lyods.beans;

import com.lyods.utils.Util;

/**
 * 
 * @author loginboot.vicp.net
 * 
 * @creator xiesw
 * @version 1.0.0
 * @date 2014-11-24
 * @description 反向工程的模式对象详细信
 *
 */

public class ModelDetail
{

	private String name;
	private String refObj;
	private String type;
	private String pkey;
	private String method;
	private String required;
	private String maxSize;
	private String search;
	private String pattern;

	public ModelDetail()
	{
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
		getMethod();
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getPkey()
	{
		return pkey;
	}

	public void setPkey(String pkey)
	{
		this.pkey = pkey;
	}

	public String getMethod()
	{
		if (method == null && !Util.isEmpty(name))
			method = (new StringBuilder(String.valueOf(name.substring(0, 1).toUpperCase()))).append(name.substring(1))
					.toString();
		return method;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	public String getRequired()
	{
		return required;
	}

	public void setRequired(String required)
	{
		this.required = required;
	}

	public String getMaxSize()
	{
		return maxSize;
	}

	public void setMaxSize(String maxSize)
	{
		this.maxSize = maxSize;
	}

	public String getSearch()
	{
		return search;
	}

	public void setSearch(String search)
	{
		this.search = search;
	}

	public String getRefObj()
	{
		return refObj;
	}

	public void setRefObj(String refObj)
	{
		this.refObj = refObj;
	}

	public String getPattern()
	{
		return pattern;
	}

	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}

}
