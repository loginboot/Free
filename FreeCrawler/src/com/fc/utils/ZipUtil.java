package com.fc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil
{
	/**
	 * 将Object对象转换为压缩后的Base64编码字符串
	 * @param obj  对象必须实现Serializable接口
	 * @return 压缩编码后的数据
	 */
	public static String gzipobj(Object obj)
	{
		ByteArrayOutputStream tmpbaos = new ByteArrayOutputStream();		
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(tmpbaos);
			out.writeObject(obj);
			out.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
		return gzip(Util.bytesToHexString(tmpbaos.toByteArray()));
	}
	/**
	 * 将压缩编码后的obj字符串对象，反传为原对象
	 * @param str 将压缩编码后的obj字符串
	 * @return Object 原对象
	 */
	public static Object gunzipobj(String str)
	{
		String objstr = gunzip(str);
		ByteArrayInputStream bais = new ByteArrayInputStream(Util.hexStringToBytes(objstr));		
		try
		{
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}		
	}
	/**
	 * 将Object对象转换为压缩后的Base64编码字符串
	 * @param obj  对象必须实现Serializable接口
	 * @return 压缩编码后的数据
	 */
	public static String zipobj(Object obj)
	{
		ByteArrayOutputStream tmpbaos = new ByteArrayOutputStream();		
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(tmpbaos);
			out.writeObject(obj);
			out.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
		return zip(Util.bytesToHexString(tmpbaos.toByteArray()));
	}
	/**
	 * 将压缩编码后的obj字符串对象，反传为原对象
	 * @param str 将压缩编码后的obj字符串
	 * @return Object 原对象
	 */
	public static Object unzipobj(String str)
	{
		String objstr = unzip(str);
		ByteArrayInputStream bais = new ByteArrayInputStream(Util.hexStringToBytes(objstr));		
		try
		{
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}		
	}
	/**
	 * 使用gzip进行压缩
	 */
	public static String gzip(String primStr)
	{
		if (primStr == null || primStr.length() == 0)
		{
			return primStr;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = null;
		try
		{
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes());
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (gzip != null)
			{
				try
				{
					gzip.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return new sun.misc.BASE64Encoder().encode(out.toByteArray()).replaceAll("\r\n", "").replace("=", "");
	}

	/**
	 * 
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 * 
	 * @param compressedStr
	 * @return
	 */
	public static String gunzip(String compressedStr)
	{
		if (compressedStr == null)
		{
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try
		{
			compressed = new sun.misc.BASE64Decoder()
					.decodeBuffer(compressedStr);
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1)
			{
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (ginzip != null)
			{
				try
				{
					ginzip.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (in != null)
			{
				try
				{
					in.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (out != null)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return decompressed;
	}

	/**
	 * 使用zip进行压缩
	 * 
	 * @param str
	 *            压缩前的文本
	 * @return 返回压缩后的文本
	 */
	public static final String zip(String str)
	{
		if (str == null)
			return null;
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try
		{
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes());
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr = new sun.misc.BASE64Encoder()
					.encodeBuffer(compressed);
		} catch (IOException e)
		{
			compressed = null;
			e.printStackTrace();
		} finally
		{
			if (zout != null)
			{
				try
				{
					zout.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (out != null)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return compressedStr.replaceAll("\r\n", "").replace("=", "");
	}

	/**
	 * 使用zip进行解压缩
	 * 
	 * @param compressed
	 *            压缩后的文本
	 * @return 解压后的字符串
	 */
	public static final String unzip(String compressedStr)
	{
		if (compressedStr == null)
		{
			return null;
		}
		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try
		{
			byte[] compressed = new sun.misc.BASE64Decoder()
					.decodeBuffer(compressedStr);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1)
			{
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e)
		{
			decompressed = null;
			e.printStackTrace();
		} finally
		{
			if (zin != null)
			{
				try
				{
					zin.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (in != null)
			{
				try
				{
					in.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (out != null)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return decompressed;
	}
	
	public static void main(String args[])
	{
		java.util.HashMap<String,Object> param = new java.util.HashMap<String,Object>();
		param.put("abc", "中国人");
		param.put("list", new String[]{"wel,'\"come to l<font>yods","欢迎来到中国，\r\n您好啊，我愛你!"});
		param.put("list1", new String[]{"wel,'\"come to l<font>yods","欢迎来到中国，\r\n您好啊，我愛你!"});
		param.put("list2", new String[]{"wel,'\"come to l<font>yods","欢迎来到中国，\r\n您好啊，我愛你!"});
		param.put("list3", "本文链接：http://www.itzhai.com/base64-encoding-in-java-using-a-url-as-a-url-parameter.html");
		param.put("list4", "为了能正常的传递参数，我们可以使用Base64对URL进行编码后再传递，接收到时再进行解码，这样就可以正确获取到传递的URL了。下面是基本的代码：");
		param.put("list5", "刘云山表示，不断巩固和发展中朝友好关系是中国党和政府的一贯立场。我们愿同朝方一道，加强沟通，扩大共识，推动中朝关系健康稳定发展。");
		param.put("list6", "刘云山指出，朝鲜半岛和平稳定，符合本地区各国共同利益，希望有关各方坚持半岛无核化目标，坚持维护半岛和平稳定，坚持通过对话协商解决问题，采取切实行动缓和紧张局势，积极对话协商，尽早重启六方会谈，为实现半岛无核化和东北亚地区持久和平稳定不懈努力。");
		param.put("list7", "崔龙海说，金正恩第一书记派我作为特使访华，目的就是改善、巩固并发展朝中关系。朝方愿同中方共同努力，推动朝中关系不断向前发展。崔龙海表示，朝鲜希望集中精力发展经济，改善民生，愿营造和平的外部环境。朝方高度赞赏中方为维护半岛和平稳定、推动半岛问题重回对话协商轨道所做的巨大努力，愿接受中方建议，同有关各方开展对话。");
		param.put("list8", "全国政协副主席、中联部部长王家瑞参加了会见。");
		param.put("list9", "5月23日，中共中央政治局常委、中央书记处书记刘云山在北京人民大会堂会见了正在北京访问的朝鲜最高领导人金正恩特使、朝人民军总政治局长崔龙海。中新社发 廖攀 摄");
		param.put("list10", "中共中央政治局常委、中央书记处书记刘云山23日下午在人民大会堂会见了朝鲜劳动党第一书记金正恩特使、朝鲜劳动党中央政治局常委崔龙海。");
		param.put("list11", "本文链接：http://www.itzhai.com/base64-encoding-in-java-using-a-url-as-a-url-parameter.html");
		param.put("list12", "刘云山表示，不断巩固和发展中朝友好关系是中国党和政府的一贯立场。我们愿同朝方一道，加强沟通，扩大共识，推动中朝关系健康稳定发展。");
		param.put("list13", "不同 ANSI 编码之间互不兼容，当信息在国际间交流时，无法将属于两种语言的文字，存储在同一段 ANSI 编码的文本中。");
		param.put("list14", "不同的国家和地区制定了不同的标准，由此产生了 GB2312, BIG5, JIS 等各自的编码标准。这些使用 2 个字节来代表一个字符的各种汉字延伸编码方式，称为 ANSI 编码。在简体中文系统下，ANSI 编码代表 GB2312 编码，在日文操作系统下，ANSI 编码代表 JIS 编码。");
		java.util.Date sdate = new java.util.Date();
		String gzipobj = gzipobj(param);
		java.util.Date edate = new java.util.Date();
		System.out.println("Gzip Object("+(edate.getTime()-sdate.getTime())+" millseconds):"+gzipobj);
		sdate = new java.util.Date();
		String zipobj = zipobj(param);
		edate = new java.util.Date();
		System.out.println("zip Object("+(edate.getTime()-sdate.getTime())+" millseconds):"+zipobj);
		sdate = new java.util.Date();
		@SuppressWarnings("unchecked")
		java.util.HashMap<String,Object> hmp =(java.util.HashMap<String,Object>)gunzipobj(gzipobj);
		edate = new java.util.Date();
		String[] list1=(String[]) hmp.get("list1");
		System.out.println("gunzip list1=("+(edate.getTime()-sdate.getTime())+" millseconds):"+list1.length+",list1[1]:"+list1[1]);
		sdate = new java.util.Date();
		@SuppressWarnings("unchecked")
		java.util.HashMap<String,Object> hmp1 =(java.util.HashMap<String,Object>)unzipobj(zipobj);
		edate = new java.util.Date();
		String[] list11=(String[]) hmp1.get("list1");
		System.out.println("unzip list1=("+(edate.getTime()-sdate.getTime())+" millseconds):"+list11.length+",list1[1]:"+list11[1]);
	}
}
