package com.lyods.exception;

import com.lyods.utils.Util;

/**
 * 
 * @author lyodssoft.com
 * 
 * @creator loginboot
 * @version 1.0.0
 * @date 2014-08-24
 * @description 系统统一异常处理
 *              <p>
 *              LyodsException(String errcode){构造函数1,以字符串为错误码}
 *              </p>
 *              <p>
 *              LyodsException(int ierrcode){构造函数2,以INTEGER为参数的错误码}
 *              </p>
 */
public class LyodsException extends Exception
{
	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public LyodsException(String errcode)
	{
		super(errcode);
	}

	public LyodsException(int ierrcode)
	{
		super("ERRCODE." + Util.leftFillZero("" + ierrcode, 4));
	}
}
