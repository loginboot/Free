package com.fc.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.fc.action.LicenseAction;
import com.fc.constant.Constant;
import com.fc.utils.Util;

public class LoginFrame extends JFrame {

    /**
     * LF ID
     */
    private static final long serialVersionUID = 1L;

    private JLabel ulabel;
    private JLabel plabel;
    private JLabel uvalid;
    private JLabel pvalid;
    private JLabel title;

    private JTextField username;
    private JPasswordField password;

    private JButton submit;
    private JButton logout;

    private JPanel console;

    @SuppressWarnings("all")
    public LoginFrame() {
        this.setTitle("Free Crawler");
        this.setSize(400, 180);
        // 更改默认图标
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon test = new ImageIcon(Constant.CRAWLER_STEP);
        this.setIconImage(test.getImage());
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(this.getOwner());// 弹出窗口居中
        this.setResizable(false);

        init();
    }

    public void startup() {
        this.setVisible(true);
    }

    private void init() {
        this.getContentPane().add(getConsole());
    }

    private JPanel getConsole() {
        if (console == null) {
            console = new LoginPanel(new BorderLayout());
            TitledBorder tb = BorderFactory.createTitledBorder("Free Crawler");
            tb.setTitleColor(Color.WHITE);
            tb.setBorder(new LineBorder(Color.GRAY, 1));
            console.setBorder(tb); // 设置面板边框
            // console.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            title = new JLabel("  User Login");
            title.setFont(new Font("Arial", 1, 24));
            title.setForeground(Color.WHITE);

            console.add(title, BorderLayout.NORTH);
            // console.add(ulabel,BorderLayout.NORTH);

            JPanel tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
            ulabel = new JLabel("User Name:");
            ulabel.setPreferredSize(new Dimension(120, 24));
            ulabel.setHorizontalAlignment(JLabel.RIGHT);
            ulabel.setForeground(Color.WHITE);

            uvalid = new JLabel("File Required.");
            uvalid.setForeground(Color.RED);
            uvalid.setVisible(false);

            tmp.setOpaque(false);
            tmp.add(ulabel);
            tmp.add(getUserName());
            tmp.add(uvalid);
            JPanel grid = new JPanel(new GridLayout(3, 1));
            grid.setOpaque(false);
            grid.add(tmp);

            plabel = new JLabel("Password:");
            plabel.setPreferredSize(new Dimension(120, 24));
            plabel.setHorizontalAlignment(JLabel.RIGHT);
            plabel.setForeground(Color.WHITE);

            pvalid = new JLabel("File Required.");
            pvalid.setForeground(Color.RED);
            pvalid.setVisible(false);
            tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
            tmp.setOpaque(false);
            tmp.add(plabel);
            tmp.add(getPassword());
            tmp.add(pvalid);

            grid.add(tmp);

            tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
            tmp.setOpaque(false);
            JLabel pad = new JLabel("");
            pad.setPreferredSize(new Dimension(120, 24));
            tmp.add(pad);
            tmp.add(getSubmit());
            tmp.add(getLogout());
            grid.add(tmp);

            console.add(grid, BorderLayout.CENTER);
        }
        return console;
    }

    private JTextField getUserName() {
        if (username == null) {
            username = new JTextField();
            username.setPreferredSize(new Dimension(150, 24));
            username.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    uvalid.setVisible(false);
                    String name = Util.trim(username.getText());
                    if (Util.isEmpty(name)) {
                        uvalid.setVisible(true);
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
        }
        return username;
    }

    private JPasswordField getPassword() {
        if (password == null) {
            password = new JPasswordField();
            password.setPreferredSize(new Dimension(150, 24));
            password.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent arg0) {
                }

                @Override
                public void keyReleased(KeyEvent arg0) {
                    pvalid.setVisible(false);
                    String pwd = Util.trim(Util.toStrFromCharArr(password.getPassword()));
                    if (Util.isEmpty(pwd)) {
                        pvalid.setVisible(true);
                    }
                }

                @Override
                public void keyPressed(KeyEvent arg0) {
                }
            });
        }
        return password;
    }

    private JButton getSubmit() {
        if (submit == null) {
            submit = new JButton("Submit");
            submit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    boolean isValid = true;
                    uvalid.setVisible(false);
                    pvalid.setVisible(false);
                    String name = Util.trim(username.getText());
                    if (Util.isEmpty(name)) {
                        uvalid.setVisible(true);
                        isValid = false;
                    }

                    String pwd = Util.trim(Util.toStrFromCharArr(password.getPassword()));
                    if (Util.isEmpty(pwd)) {
                        pvalid.setVisible(true);
                        isValid = false;
                    }
                    //校验通过
                    if (isValid) {
                        LicenseAction la = new LicenseAction();
                        if (la.check(name, pwd)) {
                            LoginFrame.this.dispose();
                            CrawlerFrame cf = new CrawlerFrame();
                            cf.startup();
                        } else {
                            JOptionPane.showMessageDialog(null, "User Name Or Password Error!", "Error Message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
        }
        return submit;
    }

    private JButton getLogout() {
        if (logout == null) {
            logout = new JButton("Close");
            logout.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
        return logout;
    }

    class LoginPanel extends JPanel {

        /**
         * LP 
         */
        private static final long serialVersionUID = 1L;

        private ImageIcon icon = null;

        public LoginPanel(BorderLayout layout) {
            super(layout);
            icon = new ImageIcon(Constant.CRAWLER_LOGIN_BG);
        }

        @Override
        public void paintComponent(Graphics g) {
            Image img = icon.getImage();
            g.drawImage(img, 0, 0, 400, 180, null);
        }

    }

}
