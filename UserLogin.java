package com.userlogin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

//两个标签,两个文本框,两个button
public class UserLogin extends JPanel{
    static int count;
    JLabel l1=new JLabel(" Account:");
    JLabel l2=new JLabel("Password:");
    JButton b1=new JButton("登录");
    JButton b2=new JButton("取消");
    JTextField t1=new JTextField();
    JPasswordField t2=new JPasswordField();
    JFrame frame;
    public UserLogin(){

        count=0;
        //先设置布局
        frame=new JFrame();
        setLayout(null);

        l1=new JLabel(" Account:");
        l2=new JLabel("Password:");
        b1=new JButton();
        t1=new JTextField();
        t2=new JPasswordField();

        add(l1);
        l1.setBounds(120,80,160,30);
        l1.setFont(new Font("Arial",Font.BOLD,25));

        add(t1);
        t1.setBounds(240,80,200,30);
        t1.setFont(new Font("Arial",Font.BOLD,18));
        add(l2);
        l2.setBounds(115,150,160,30);
        l2.setFont(new Font("Arial",Font.BOLD,25));
        add(t2);
        t2.setFont(new Font("Arial",Font.BOLD,18));
        t2.setBounds(240,150,200,30);
        add(b1);
        b1.setBounds(240,220,200,40);
        b1.setIcon(new ImageIcon(UserLogin.class.getResource("statics/login.jpg")));

        JLabel label=new JLabel();
        this.add(label);
        label.setBounds(0,0,600,500);
        URL url=UserLogin.class.getResource("statics/background.jpg");
        ImageIcon icon=new ImageIcon(url);
        label.setIcon(icon);

        t1.addActionListener(new MyActionListener());
        t2.addActionListener(new MyActionListener());
        b1.addActionListener(new MyActionListener());

        frame.add(this);
        frame.setTitle("User Login");
        this.setBackground(Color.WHITE);
        frame.setBounds(600,300,600,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private class MyActionListener implements ActionListener{
        private String userName="ikun";
        private String password="ji";
        @Override
        public void actionPerformed(ActionEvent e) {
            if(userName.equals(t1.getText())&&password.equals(t2.getText())&&count<5) {
                System.out.println("登录成功");
                count=0;

                JFrame framesnake=new JFrame();
                framesnake.add(new GamePanel());
                framesnake.setResizable(false);
                framesnake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                framesnake.setBounds(200,200,900,720);
                framesnake.setVisible(true);
                frame.setVisible(false);

            }
            else if(count<5){
                System.out.println("用户名或者密码错误!");
                count++;
            }
            else {
                System.out.println("输入"+count+"次用户名或者密码错误,禁止输入!");
            }

            t2.setText("");
        }
    }
}
