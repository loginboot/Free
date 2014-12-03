package com.lyods.utils;

public class Util
{

	/**
	 * 字符串去两边空格
	 * 
	 * @param val
	 * @return
	 */
	public static String trim(String value)
	{
		if (value == null)
		{
			return "";
		} else
		{
			return value.trim();
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value)
	{
		if (value == null || "".equals(value.trim()))
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	/**
	 * 函数功能：如果源字符串的长度(不包括符号)小于指定长度，则在前面补零，使之达到指定长度
	 * 
	 * @param accno
	 *            输入字符串
	 * @param strLen
	 *            需要输出字符串长度，不包括符号，
	 * @return 补零后的字符串
	 *         <p>
	 *         <blockquote>
	 * 
	 *         <pre>
	 * 例如：
	 * 		leftFillZero(&quot;-1&quot;,3) 输出为-001，
	 * 		leftFillZero(&quot;1&quot;,3) 输出为001
	 * 		leftFillZero(&quot;&quot;,4)  输出0000
	 * leftFillZero(null,4) 输出null
	 * 
	 *         </p>
	 *         </blockquote></pre>
	 */
	public static String leftFillZero(String accno, int strLen)
	{
		if (accno == null)
		{
			return null;
		}
		int tempLen = accno.length();
		StringBuffer retVal = new StringBuffer(accno);
		if (tempLen == 0)
		{
			for (int i = 0; i < strLen; i++)
			{
				retVal.insert(0, "0");
			}
			return retVal.toString();
		}
		if (accno.charAt(0) == '-')
		{
			if (tempLen > strLen)
			{
				return accno;
			}
			for (int i = 0; i <= (strLen - tempLen); i++)
			{
				retVal.insert(1, "0");
			}
		} else
		{
			if (tempLen >= strLen)
			{
				return accno;
			}
			for (int i = 0; i < (strLen - tempLen); i++)
			{
				retVal.insert(0, "0");
			}
		}
		return retVal.toString();
	}
	
}
