package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//游戏面板
public class GamePanel extends JPanel implements KeyListener , ActionListener {
    //定义蛇的数据结构
    int length;
    int[] snakeX=new int[600];
    int[] snakeY=new int[600];
    String fx;//方向
    //定义食物的坐标
    int foodX;
    int foodY;
    Random random=new Random();
    //定义分数
    int score;

    //游戏状态
    boolean isStart=false;
    boolean isFail=false;
    Timer timer=new Timer(100,this);//delay的单位是ms

    //初始化方法
    public  void init(){
        length=3;
        snakeX[0]=100;snakeY[0]=100;//脑袋的坐标
        snakeX[1]=75;snakeY[1]=100;//第一个身体的坐标
        snakeX[2]=50;snakeY[2]=100;//第二个身体的坐标
        score=0;
        fx="R";
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt( 24);
    }
    public GamePanel(){
        init();
        this.setFocusable(true);//获得焦点事件
        addKeyListener(this);//获得键盘的监听事件
        timer.start();//游戏一开始就启动
        //把食物随机分布在界面上
    }

    @Override
    //绘制面板,游戏中所有的东西都用画板画出来,此方法会在构造对象时自动调用
    protected void paintComponent(Graphics g) {
        //清屏
        super.paintComponent(g);
        //绘制静态的面板
        GameData.header.paintIcon(this,g,25,11);//放置header
        g.fillRect(25,75,850,600);//默认的游戏界面

        if("R".equals(fx)){
            GameData.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        else if ("L".equals(fx)){
            GameData.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        else if ("U".equals(fx)){
            GameData.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        else if("D".equals(fx)){
            GameData.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        //身体坐标
        for(int i=1;i<length;i++){
            GameData.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        //画食物
         GameData.food.paintIcon(this,g,foodX,foodY);
        //画积分
        g.setColor(Color.PINK);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度:"+length,750,35);
        g.drawString("分数:"+length,750,50);

        //游戏状态
        if(!isStart){
            g.setColor(Color.WHITE);
            g.setFont(new Font("楷体",Font.BOLD,30));
            g.drawString("按下空格开始游戏",300,300);
        }
        if(isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("楷体",Font.BOLD,30));
            g.drawString("失败,按下空格重新开始",300,300);
        }
        setBackground(Color.WHITE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode==KeyEvent.VK_SPACE){
                        isStart=!isStart;
                if(isFail){
                   isFail=false;
                    init();
                    repaint();
                }

                }
                //用键盘控制方向
                if(keyCode==KeyEvent.VK_DOWN&& !"U".equals(fx)){
                    fx="D";
                }
                else if(keyCode==KeyEvent.VK_UP&& !"D".equals(fx)){
                    fx="U";
                }
                else if(keyCode==KeyEvent.VK_LEFT&& !"R".equals(fx)){
                    fx="L";
                }
                else if(keyCode==KeyEvent.VK_RIGHT&& !"L".equals(fx)){
                    fx="R";
                }
    }
    //事件监听----需要通过固定事件来监听,一秒十次,要用到定时器
    @Override
    public void actionPerformed(ActionEvent e) {
            if(isStart&&isFail==false){
                for(int i=length-1;i>0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                //吃食物
                if(snakeX[0]==foodX&&snakeY[0]==foodY){
                    length++;//吃一个食物,长度加1
                    score++;//吃一个食物,分数加1
                    //再次把食物随机
                    foodX=25+25*random.nextInt(34);
                    foodY=75+25*random.nextInt( 24);
                }
                //走向
                if(fx.equals("R")){
                    snakeX[0]=snakeX[0]+25;
                    if(snakeX[0]>850){snakeX[0]=25;}
                }
                else if (fx.equals("L")){
                    snakeX[0]=snakeX[0]-25;
                    if(snakeX[0]<25){snakeX[0]=850;}
                }
                else if (fx.equals("U")){
                    snakeY[0]=snakeY[0]-25;
                    if(snakeY[0]<75){snakeY[0]=650;}
                }
                else if(fx.equals("D")){
                    snakeY[0]=snakeY[0]+25;
                    if(snakeY[0]>650){snakeY[0]=75;}
                }
                repaint();//刷新
                //失败判断,撞到自己就失败
                for(int i=1;i<length;i++){
                    if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                        isFail=true;
                    }
                }
            }
            timer.start();//定时器开始
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
