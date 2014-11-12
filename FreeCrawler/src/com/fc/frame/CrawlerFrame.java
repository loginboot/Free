package com.fc.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fc.config.Config;

/**
 * 
 * @author lyodssoft.com
 *
 * @creator xiesw
 * @version 1.0.0
 * @date 2014-11-11
 * @description 网页爬虫主界面类 - 创建
 *
 */

public class CrawlerFrame extends JFrame
{
	/**
	 *  CW ID
	 */
	private static final long serialVersionUID = 1L;
	
	//配置文件
	private static Config cfg = Config.getInstance();
	
	private JLabel lab1;
	private JLabel lab2;
	private JLabel lab3;
	private JButton cbtn;
	private ParamFrame pframe;
	
	private JPanel sc;
	
	public CrawlerFrame()
	{
		
	}
	
	public void startup()
	{
		this.setSize(800, 600);
		setLocationByPlatform(true);
		this.getContentPane().add(getSc(),BorderLayout.NORTH);
		
		this.setVisible(true);
	}
	
	private JPanel getSc()
	{
		if(sc==null)
		{
			sc = new JPanel();
			sc.add(getCbtn());
		}
		return sc;
	}
	
	
	private JLabel getLab(String val)
	{
		JLabel tmp = new JLabel(val);
		
		return tmp;
	}
	
	
	private JButton getCbtn()
	{
		if(cbtn==null)
		{
			cbtn = new JButton("Setting");
			cbtn.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					System.err.println("--->"+pframe);
					if(pframe==null){
						pframe = new ParamFrame();
					}else
					{
						pframe.show();
					}
				}
			});
		}
		return cbtn;
	}
	
	
	public static void main(String[] args)
	{
		CrawlerFrame cf = new CrawlerFrame();
		cf.startup();
	}
	
}
