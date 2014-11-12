package com.fc.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.fc.constant.Constant;
import com.fc.utils.Encrypt;
import com.fc.utils.Util;

public class LicenseAction
{
	
	/*
	 * 用户License -->USER:admin|PASSWORD:123456|EXPIRED:20140809
	 */
	
	private String license = "";
	
	public LicenseAction()
	{
		String hexStr = Util.readFileContent(Constant.LICENSE_PATH_FILE);
		String keyStr = new String(Util.hexStringToBytes(hexStr));
		try
		{
			license = Encrypt.decodeString(keyStr);
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		System.out.println("--->"+license);
	}
	
	public static void main(String[] args)
	{
		new LicenseAction();
	}
	
	/**
	 * 检测登录是否成功
	 * @param name
	 * @param pwd
	 * @return
	 */
	public boolean check(String name,String pwd)
	{
		boolean isOk = true;
		String cdate = Util.dateToStr(new Date(), "yyyyMMdd");
		String[] lkey = license.split("\\|");
		if(lkey!=null && lkey.length==3)
		{
			String wdate = lkey[2].split(":")[1];
			if(cdate.compareTo(wdate)>0)
			{
				isOk=false;
			}
			
			String wname = lkey[0].split(":")[1];
			if(!name.equals(wname))
			{
				isOk = false;
			}
			
			String wpwd = lkey[0].split(":")[1];
			if(!pwd.equals(wpwd))
			{
				isOk = false;
			}
		}else
		{
			isOk = false;
		}
		return isOk;
	}

}
