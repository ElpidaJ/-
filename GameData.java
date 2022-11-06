package com.userlogin;

import javax.swing.*;
import java.net.URL;

//数据中心
public class GameData {
    public static URL bodyURL=GameData.class.getResource("statics/body.jpg");
    public static ImageIcon body=new ImageIcon(bodyURL);

    public static URL downURL=GameData.class.getResource("statics/down.jpg");
    public static ImageIcon down=new ImageIcon(downURL);

    public static URL foodURL=GameData.class.getResource("statics/food.jpg");
    public static ImageIcon food=new ImageIcon(foodURL);

    public static URL headerURL=GameData.class.getResource("statics/header.jpg");
    public static ImageIcon header=new ImageIcon(headerURL);

    public static URL leftURL=GameData.class.getResource("statics/left.jpg");
    public static ImageIcon left=new ImageIcon(leftURL);

    public static URL rightURL=GameData.class.getResource("statics/right.jpg");
    public static ImageIcon right=new ImageIcon(rightURL);

    public static URL upURL=GameData.class.getResource("statics/up.jpg");
    public static ImageIcon up=new ImageIcon(upURL);


}
