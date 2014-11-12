package com.fc.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.fc.config.Config;
import com.fc.constant.Constant;

/**
 * 
 * @author loginboot.vicp.net
 * 
 * @creator xiesw
 * @version 1.0.0
 * @date 2014-10-22 
 * @description 配置文件设置类 - 创建
 *
 */

public class ParamFrame extends JFrame
{
	
	/**
	 * PF ID
	 */
	private static final long serialVersionUID = 1L;
	
	//配置信息
	private static Config cfg = Config.getInstance();
	
	private JLabel url_label;
	private JLabel save_path_label;
	private JLabel auto_name_label;
	private JLabel thread_size_label;
	private JLabel sub_folder_label;
	private JLabel seq_uuid_label;
	private JLabel body_class_label;
	private JLabel page_class_label;
	private JLabel get_level_label;
	
	private JTextField url_txt;
	private JTextField save_path_txt;
	private JRadioButton auto_name_true;
	private JRadioButton auto_name_false;
	private JRadioButton sub_folder_true;
	private JRadioButton sub_folder_false;
	private JTextField thread_size_txt;
	private JTextField seq_uuid_txt;
	private JTextField body_class_txt;
	private JTextField page_class_txt;
	private JTextField get_level_txt;
	
	private JButton save;
	private JButton cancel;
	
	private JPanel mypanel;

	@SuppressWarnings("all")
	public ParamFrame()
	{
		this.setTitle("Free Crawler");
		this.setSize(600, 320);
		// 更改默认图标
		Toolkit tk = Toolkit.getDefaultToolkit();
		String path = LoginFrame.class.getClassLoader().getResource("img").getFile();
		ImageIcon test = new ImageIcon(path + File.separator + "/step.png");
		this.setIconImage(test.getImage());

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

		this.setLocationRelativeTo(this.getOwner());// 弹出窗口居中
		init();
	}
	
	public void startup()
	{
		this.setVisible(true);
	}
	
	private void init()
	{
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(9,1));
		// url
		JPanel tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		url_label = cshow("Http Path:");
		tmp.add(url_label);
		tmp.add(getUrlTxt());
		grid.add(tmp);
		// save path
		tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		save_path_label = cshow("Save File Path:");
		tmp.add(save_path_label);
		tmp.add(getSavePathTxt());
		grid.add(tmp);
		// auto name
		tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		auto_name_label = cshow("Is Auto Create Name:");
		tmp.add(auto_name_label);
		tmp.add(getAutoNameTrue());
		tmp.add(getAutoNameFalse());
		grid.add(tmp);
		// thread size
		tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		thread_size_label = cshow("The Thread Size:");
		tmp.add(thread_size_label);
		tmp.add(getThreadSizeTxt());
		grid.add(tmp);
		//sub folder
		tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sub_folder_label = cshow("Is Sub Folder:");
		tmp.add(sub_folder_label);
		tmp.add(getSubFolderTrue());
		tmp.add(getSubFolderFalse());
		grid.add(tmp);
		//seq_uuid
		tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		seq_uuid_label = cshow("SEQ Or UUID:");
		tmp.add(seq_uuid_label);
		tmp.add(getSeqUuid());
		grid.add(tmp);
		//body class
		tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		body_class_label = cshow("Body Class:");
		tmp.add(body_class_label);
		tmp.add(getBodyClass());
		grid.add(tmp);
		//page class
		tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		page_class_label = cshow("Page Class:");
		tmp.add(page_class_label);
		tmp.add(getPageClass());
		grid.add(tmp);
		//get level
		tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		get_level_label = cshow("Select Level:");
		tmp.add(get_level_label);
		tmp.add(getLevel());
		grid.add(tmp);
		
		getMyPanbel().add(grid,BorderLayout.CENTER);
		
		
		
		tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pad = new JLabel("");
		pad.setPreferredSize(new Dimension(120, 24));
		tmp.add(pad);
		tmp.add(getSave());
		tmp.add(getCancel());
		getMyPanbel().add(tmp,BorderLayout.SOUTH);
		this.getContentPane().add(getMyPanbel());
	}
	
	private JPanel getMyPanbel()
	{
		if(mypanel==null)
		{
			mypanel = new JPanel(new BorderLayout());
		}
		return mypanel;
	}
	
	private JLabel cshow(String str)
	{
		JLabel jb = new JLabel(str);
		jb.setPreferredSize(new Dimension(120, 20));
		jb.setHorizontalAlignment(JLabel.RIGHT);
		return jb;
	}
	
	//text----------------
	private JTextField getUrlTxt()
	{
		if(url_txt==null)
		{
			url_txt = new JTextField(cfg.getPropStr(Constant.PROP_URL));
			url_txt.setPreferredSize(new Dimension(250, 20));
		}
		return url_txt;
	}
	
	private JTextField getSavePathTxt()
	{
		if(save_path_txt==null)
		{
			save_path_txt = new JTextField(cfg.getPropStr(Constant.PROP_SAVE_PATH));
			save_path_txt.setPreferredSize(new Dimension(250, 20));
		}
		return save_path_txt;
	}
	
	private JRadioButton getAutoNameTrue()
	{
		if(auto_name_true == null)
		{
			auto_name_true = new JRadioButton("Yes");
			if("true".equals(cfg.getPropStr(Constant.PROP_AUTO_NAME)))
			{
				auto_name_true.setSelected(true);
			}
		}
		return auto_name_true;
	}
	
	private JRadioButton getAutoNameFalse()
	{
		if(auto_name_false == null)
		{
			auto_name_false = new JRadioButton("No");
			if("false".equals(cfg.getPropStr(Constant.PROP_AUTO_NAME)))
			{
				auto_name_false.setSelected(true);
			}
		}
		return auto_name_false;
	}
	
	private JTextField getThreadSizeTxt()
	{
		if(thread_size_txt == null)
		{
			thread_size_txt = new JTextField(cfg.getPropStr(Constant.PROP_THREAD_SIZE));
			thread_size_txt.setPreferredSize(new Dimension(250, 20));
		}
		return thread_size_txt;
	}
	
	
	private JRadioButton getSubFolderTrue()
	{
		if(sub_folder_true == null)
		{
			sub_folder_true = new JRadioButton("Yes");
			if("true".equals(cfg.getPropStr(Constant.PROP_SUB_FOLDER)))
			{
				sub_folder_true.setSelected(true);
			}
		}
		return sub_folder_true;
	}
	
	private JRadioButton getSubFolderFalse()
	{
		if(sub_folder_false == null)
		{
			sub_folder_false = new JRadioButton("No");
			if("false".equals(cfg.getPropStr(Constant.PROP_SUB_FOLDER)))
			{
				sub_folder_false.setSelected(true);
			}
		}
		return sub_folder_false;
	}
	
	private JTextField getSeqUuid()
	{
		if(seq_uuid_txt == null)
		{
			seq_uuid_txt = new JTextField(cfg.getPropStr(Constant.PROP_SEQ_UUID));
			seq_uuid_txt.setPreferredSize(new Dimension(250, 20));
		}
		
		return seq_uuid_txt;
	}
	
	private JTextField getBodyClass()
	{
		if(body_class_txt == null)
		{
			body_class_txt = new JTextField(cfg.getPropStr(Constant.PROP_BODY_CLASS));
			body_class_txt.setPreferredSize(new Dimension(250, 20));
		}
		
		return body_class_txt;
	}
	
	private JTextField getPageClass()
	{
		if(page_class_txt == null)
		{
			page_class_txt = new JTextField(cfg.getPropStr(Constant.PROP_PAGE_CLASS));
			page_class_txt.setPreferredSize(new Dimension(250, 20));
		}
		
		return page_class_txt;
	}
	
	private JTextField getLevel()
	{
		if(get_level_txt == null)
		{
			get_level_txt = new JTextField(cfg.getPropStr(Constant.PROP_GET_LEVEL));
			get_level_txt.setPreferredSize(new Dimension(250, 20));
		}
		
		return get_level_txt;
	}
	
	//action---------------
	
	private JButton getSave()
	{
		if(save==null)
		{
			save = new JButton("Save");
			save.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
		}
		return save;
	}
	
	
	private JButton getCancel()
	{
		if(cancel==null)
		{
			cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
		}
		return cancel;
	}

	
	public static void main(String[] args)
	{
		new ParamFrame().startup();
	}
}
