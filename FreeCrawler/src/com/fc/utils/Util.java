package com.fc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
	 * Convert byte[] to hex
	 * string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 * 
	 * @param src
	 *            byte[] data
	 * 
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src)
	{
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0)
		{
			return null;
		}
		for (int i = 0; i < src.length; i++)
		{
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2)
			{
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString)
	{
		if (hexString == null || hexString.equals(""))
		{
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++)
		{
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c)
	{
		return (byte) "0123456789ABCDEF".indexOf(c);
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
	
	/**
	 * char转字符串
	 * @param acs
	 * @return
	 */
	public static String toStrFromCharArr(char[] acs)
	{
		StringBuilder buf = new StringBuilder();
		if(acs!=null)
		{
			for(char c : acs)
			{
				buf.append(c);
			}
		}
		return buf.toString();
	}
	
	
	/**
	 * 读取指定文件中所有内容
	 * 
	 * @param file
	 *            文件
	 * @return null失败
	 */
	public static String readFileContent(String file)
	{
		return readFileContent(new File(file));
	}

	/**
	 * 读取指定文件中所有内容
	 * 
	 * @param file
	 *            文件
	 * @return null失败
	 */
	public static String readFileContent(File file)
	{
		FileInputStream logf = null;
		StringBuffer strbuf = new StringBuffer();
		try
		{
			logf = new FileInputStream(file);
			byte[] logbuf = new byte[4096];
			int rlen = 0;
			while ((rlen = logf.read(logbuf)) != -1)
			{
				strbuf.append(new String(logbuf, 0, rlen));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			if(logf!=null)
			{
				try
				{
					logf.close();
				} catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		return strbuf.toString();
	}

	/**
	 * 日期格式转换
	 * 
	 * @param sdate
	 *            字符串日期
	 * @param srcfmt
	 *            源日期格式
	 * @param desfmt
	 *            目标日期格式
	 * @return
	 */
	public static String dateFormatChg(String sdate, String srcfmt, String desfmt)
	{
		String outdate = "";
		SimpleDateFormat sdf = new SimpleDateFormat(srcfmt, Locale.ENGLISH);
		SimpleDateFormat desf = new SimpleDateFormat(desfmt, Locale.ENGLISH);
		try
		{
			Date dt = sdf.parse(sdate);
			return desf.format(dt);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return outdate;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param time
	 *            Date
	 * @return yyyy-MM-dd
	 */
	public static String dateToStr(Date time)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(time);
	}

	public static String dateToStr(Date time, String fmtptn)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(fmtptn);
		return sdf.format(time);
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param times
	 *            YYYY-MM-DD
	 * @return Date
	 * @throws ParseException
	 */
	public static Date strToDate(String times) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(times);
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param times
	 * @param format
	 *            日期格式,eg: dd/MM/yyyy
	 * @return Date
	 * @throws ParseException
	 */
	public static Date strToDate(String times, String format) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		return sdf.parse(times);
	}

	
}
