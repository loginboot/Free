package com.fc.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import com.fc.action.CrawlerAction;
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

public class CrawlerFrame extends JFrame {
    
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(CrawlerAction.class);
    
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
    private JButton stop;
    private ParamFrame pframe;

    private JPanel sc;

    private JScrollPane scroll;

    public CrawlerFrame() {

    }

    @SuppressWarnings("all")
    public void startup() {
        this.setTitle("Crawler Frame");
        this.setSize(800, 600);
        // 更改默认图标
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon test = new ImageIcon(Constant.CRAWLER_STEP);
        this.setIconImage(test.getImage());
        setLocationByPlatform(true);
        this.getContentPane().add(getSc());

        this.setLocationRelativeTo(this.getOwner());// 弹出窗口居中
        this.setResizable(false);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logger.info("The crawler engine exit success...");
                System.exit(0);
            }
        });
    }

    private JPanel getSc() {
        if (sc == null) {
            sc = new JPanel(new BorderLayout());
            sc.add(getTop(), BorderLayout.NORTH);
            sc.add(getScroll(), BorderLayout.CENTER);
        }
        return sc;
    }

    private JPanel top;

    private JPanel getTop() {
        if (top == null) {
            top = new JPanel();
            GridLayout grid = new GridLayout(4, 1);
            top.setLayout(grid);
            String val = "  Http: " + cfg.getPropStr(Constant.PROP_URL) + "    Save Path: "
                    + cfg.getPropStr(Constant.PROP_SAVE_PATH);
            lab1 = getLab(val);
            top.add(lab1);
            val = "  Auto Create Name: " + cfg.getPropStr(Constant.PROP_STOP_POS) + "    Thread Size: "
                    + cfg.getPropStr(Constant.PROP_THREAD_SIZE) + "    Sub Folder: "
                    + cfg.getPropStr(Constant.PROP_SUB_FOLDER) + "    UUID_SEQ: "
                    + cfg.getPropStr(Constant.PROP_SEQ_UUID);
            lab2 = getLab(val);
            top.add(lab2);
            val = "  Body Class: " + cfg.getPropStr(Constant.PROP_BODY_CLASS) + "    Page Class: "
                    + cfg.getPropStr(Constant.PROP_PAGE_CLASS) + "    Get Level: "
                    + cfg.getPropStr(Constant.PROP_GET_LEVEL);
            lab3 = getLab(val);
            top.add(lab3);

            JPanel tp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            tp.add(getCbtn());
            tp.add(getStart());
            tp.add(getStop());
            top.add(tp);
        }
        return top;
    }

    private JLabel getLab(String val) {
        JLabel tmp = new JLabel(val);
        return tmp;
    }

    private JButton getCbtn() {
        if (cbtn == null) {
            cbtn = new JButton("Setting");
            cbtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pframe == null) {
                        pframe = new ParamFrame(CrawlerFrame.this);
                    }
                    pframe.getJd().setVisible(true);
                }
            });
        }
        return cbtn;
    }

    private CrawlerAction ca;

    private JButton getStart() {
        if (start == null) {
            start = new JButton("Start");
            start.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (ca != null) {
                        ca.shutdown();
                    }
                    ca = new CrawlerAction();
                    ca.startup();
                    Console.getInstance().log(Constant.LOG_PANEL_CRAWLER, "Start...");
                    logger.info("start...");
                }
            });
        }
        return start;
    }

    private JButton getStop() {
        if (stop == null) {
            stop = new JButton("Stop");
            stop.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if (ca != null) {
                        ca.shutdown();
                        Console.getInstance().log(Constant.LOG_PANEL_CRAWLER, "Stop...");
                        logger.info("Stop...");
                    }
                }
            });
        }
        return stop;
    }

    public void reflashParameter() {
        String val = "  Http: " + cfg.getPropStr(Constant.PROP_URL) + "    Save Path: "
                + cfg.getPropStr(Constant.PROP_SAVE_PATH);
        lab1.setText(val);
        val = "  Auto Create Name: " + cfg.getPropStr(Constant.PROP_STOP_POS) + "    Thread Size: "
                + cfg.getPropStr(Constant.PROP_THREAD_SIZE) + "    Sub Folder: "
                + cfg.getPropStr(Constant.PROP_SUB_FOLDER) + "    UUID_SEQ: " + cfg.getPropStr(Constant.PROP_SEQ_UUID);
        lab2.setText(val);
        val = "  Body Class: " + cfg.getPropStr(Constant.PROP_BODY_CLASS) + "    Page Class: "
                + cfg.getPropStr(Constant.PROP_PAGE_CLASS) + "    Get Level: "
                + cfg.getPropStr(Constant.PROP_GET_LEVEL);
        lab3.setText(val);
    }

    private JScrollPane getScroll() {
        if (scroll == null) {
            scroll = new JScrollPane(Console.getInstance().getArea(Constant.LOG_PANEL_CRAWLER));
        }
        return scroll;
    }
}
