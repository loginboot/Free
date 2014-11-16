package com.fc.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.fc.config.Config;
import com.fc.constant.Constant;

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
	 * CW ID
	 */
	private static final long serialVersionUID = 1L;

	// 配置文件
	private static Config cfg = Config.getInstance();

	private JLabel lab1;
	private JLabel lab2;
	private JLabel lab3;
	private JButton cbtn;
	private JButton start;
	private ParamFrame pframe;

	private JPanel sc;

	private JScrollPane scroll;

	public CrawlerFrame()
	{

	}

	@SuppressWarnings("all")
	public void startup()
	{
		this.setTitle("Crawler Frame");
		this.setSize(800, 600);
		// 更改默认图标
		Toolkit tk = Toolkit.getDefaultToolkit();
		String path = LoginFrame.class.getClassLoader().getResource("img").getFile();
		ImageIcon test = new ImageIcon(path + File.separator + "/step.png");
		this.setIconImage(test.getImage());
		setLocationByPlatform(true);
		this.getContentPane().add(getSc());
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

		this.setLocationRelativeTo(this.getOwner());// 弹出窗口居中
		this.setResizable(false);
		
		this.setVisible(true);
	}

	private JPanel getSc()
	{
		if (sc == null)
		{
			sc = new JPanel(new BorderLayout());
			sc.add(getTop(), BorderLayout.NORTH);
			sc.add(getScroll(), BorderLayout.CENTER);
		}
		return sc;
	}

	private JPanel top;

	private JPanel getTop()
	{
		if (top == null)
		{
			top = new JPanel();
			GridLayout grid = new GridLayout(4, 1);
			top.setLayout(grid);
			String val = "  Http: " + cfg.getPropStr(Constant.PROP_URL) + "    Save Path: "
					+ cfg.getPropStr(Constant.PROP_SAVE_PATH);
			lab1 = getLab(val);
			top.add(lab1);
			val = "  Auto Create Name: " + cfg.getPropStr(Constant.PROP_AUTO_NAME) + "    Thread Size: "
					+ cfg.getPropStr(Constant.PROP_THREAD_SIZE)
					+ "    Sub Folder: " + cfg.getPropStr(Constant.PROP_SUB_FOLDER) + "    UUID_SEQ: "
					+ cfg.getPropStr(Constant.PROP_SEQ_UUID);
			lab2 = getLab(val);
			top.add(lab2);
			val = "  Body Class: " + cfg.getPropStr(Constant.PROP_BODY_CLASS) + "    Page Class: "
					+ cfg.getPropStr(Constant.PROP_PAGE_CLASS)
					+ "    Get Level: " + cfg.getPropStr(Constant.PROP_GET_LEVEL);
			lab3 = getLab(val);
			top.add(lab3);

			JPanel tp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			tp.add(getCbtn());
			tp.add(getStart());
			top.add(tp);
		}
		return top;
	}

	private JLabel getLab(String val)
	{
		JLabel tmp = new JLabel(val);
		return tmp;
	}

	private JButton getCbtn()
	{
		if (cbtn == null)
		{
			cbtn = new JButton("Setting");
			cbtn.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (pframe == null)
					{
						pframe = new ParamFrame(CrawlerFrame.this);
					}
					pframe.getJd().setVisible(true);
				}
			});
		}
		return cbtn;
	}

	private JButton getStart()
	{
		if (start == null)
		{
			start = new JButton("Start");
			start.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{

				}
			});
		}
		return start;
	}

	public void reflashParameter()
	{
		String val = "  Http: " + cfg.getPropStr(Constant.PROP_URL) + "    Save Path: "
				+ cfg.getPropStr(Constant.PROP_SAVE_PATH);
		lab1.setText(val);
		val = "  Auto Create Name: " + cfg.getPropStr(Constant.PROP_AUTO_NAME) + "    Thread Size: "
				+ cfg.getPropStr(Constant.PROP_THREAD_SIZE)
				+ "    Sub Folder: " + cfg.getPropStr(Constant.PROP_SUB_FOLDER) + "    UUID_SEQ: "
				+ cfg.getPropStr(Constant.PROP_SEQ_UUID);
		lab2.setText(val);
		val = "  Body Class: " + cfg.getPropStr(Constant.PROP_BODY_CLASS) + "    Page Class: "
				+ cfg.getPropStr(Constant.PROP_PAGE_CLASS)
				+ "    Get Level: " + cfg.getPropStr(Constant.PROP_GET_LEVEL);
		lab3.setText(val);
	}

	private JScrollPane getScroll()
	{
		if (scroll == null)
		{
			scroll = new JScrollPane(Console.getInstance().getArea(Constant.LOG_PANEL_CRAWLER));
		}
		return scroll;
	}

	public static void main(String[] args)
	{
		CrawlerFrame cf = new CrawlerFrame();
		cf.startup();
	}

}
