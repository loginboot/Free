// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Model.java

package com.lyods.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lyods.utils.Util;

public class Model
{

	private String pkg;
	private String tableName;
	private String name;
	private String hasUser; //是否有用户
	private String menu; //菜单名称
	private String sql; //是否要创建sql语句
	private String rtf; //是否要生成rtf文档
	private String modeType; //表模式
	
	private List<ModelDetail> mdlst = new ArrayList<ModelDetail>();
	
	private String[] tables;

	public Model()
	{
		
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

			name = tmp;
		}
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 根据表名返回对应的对象数
	 * @param table
	 * @return
	 */
	public List<ModelDetail> getMdlst(String table)
	{
		Map<Integer,List<ModelDetail>> map = new HashMap<Integer,List<ModelDetail>>();
		int i=0;
		List<ModelDetail> tmplst = new ArrayList<ModelDetail>();
		for(ModelDetail md : this.mdlst)
		{
			if(Util.trim(md.getName()).startsWith("#end"))
			{
				map.put(i, tmplst);
				tmplst = new ArrayList<ModelDetail>();
				i++;
			}else
			{
				tmplst.add(md);
			}
		}
		if(tmplst.size()!=0)
		{
			map.put(i, tmplst);
		}
		i=0;
		for(String tab : this.tables)
		{
			if(Util.trim(table).equals(Util.trim(tab)))
			{
				tmplst = map.get(i);
				break;
			}
			i++;
		}
		return tmplst;
	}
	
	public List<ModelDetail> getMdlst()
	{
		return this.mdlst;
	}

	public void setMdlst(List<ModelDetail> mdlst)
	{
		this.mdlst = mdlst;
	}

	public String getHasUser()
	{
		return hasUser;
	}

	public String getMenu()
	{
		return menu;
	}

	public void setMenu(String menu)
	{
		this.menu = menu;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public String getRtf()
	{
		return rtf;
	}

	public void setRtf(String rtf)
	{
		this.rtf = rtf;
	}

	public String getModeType()
	{
		return modeType;
	}

	public void setModeType(String modeType)
	{
		this.modeType = modeType;
	}

	public String[] getTables()
	{
		return tables;
	}

	public void setTables(String[] tables)
	{
		this.tables = tables;
	}

	public void setHasUser(String hasUser)
	{
		this.hasUser = hasUser;
	}
	
	/**
	 * 判断是否只有一张表
	 * @return
	 */
	public boolean isSignle()
	{
		boolean flag = true;
		String[] strs = this.tableName.split(",|;");
		if(strs.length!=1)
		{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 克隆当前对象
	 * @param table
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Model getModel(String table) throws CloneNotSupportedException
	{
		Model mod = (Model) this.clone();
		mod.setTableName(table);
		mod.setMdlst(mod.getMdlst(table));
		
		return mod;
	}
	
}
