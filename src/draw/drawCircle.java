package draw;

/**
 * Component类提供了两个和绘图相关的方法：
 * 1、paint（Graphics g）绘制组件外观
 * 2、repaint刷新组件外观
 * paint方法被调用的情况：
 * 1、当组件第一次显式在屏幕上时，会自动调用paint来绘制组件
 * 2、当窗口最小化后将其复位时
 * 3、窗口的大小发生改变
 * 4、调用repaint方法时
 */

import javax.swing.*;
import java.awt.*;
@SuppressWarnings("all")
public class drawCircle extends JFrame{//JFrame即是一个窗口
    //定义一个面板，用于画图
    private MyPanel mp = null;

    public drawCircle(){
        mp = new MyPanel();
        //把面板放入到画框中
        this.add(mp);
        //设置窗口大小
        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//可视化
    }

    public static void main(String[] args) {
        new drawCircle();
    }
}

//定义一个MyPanel继承自JPanel，画图形
class MyPanel extends JPanel{
    @Override
    public void paint(Graphics g) {//将Graphics理解为一个画笔
        System.out.println("paint方法被调用");
        super.paint(g);//调用父类的方法进行初始化
        //画圆
        g.drawOval(20,20,100,100);
        //
    }
}