package tankgame1;

import javax.swing.*;
import java.awt.*;

//画板
public class MyPanel extends JPanel {
    private MyTank myTank = null;

    public MyPanel() {
        myTank = new MyTank(100,100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,800);
        //对画出坦克的方法进行封装（防止每次画坦克都要写在方法体中）
        paintTank(myTank.getX(), myTank.getY(), g,0,0);
    }

    /**
     * @param x 坦克的原点x坐标
     * @param y 坦克的远点y坐标
     * @param g 画笔
     * @param direct 坦克朝向
     * @param type 坦克类型
     */
    public void paintTank(int x,int y,Graphics g,int direct,int type){
        //根据类型来设置颜色
        switch (type){
            case 0://己方
                g.setColor(Color.CYAN);
                break;
            case 1://敌方
                g.setColor(Color.RED);
                break;
        }
        //根据朝向画出坦克样子
        switch(direct){
            case 0://朝上
                g.fill3DRect(x,y,10,60,false);//左轮
                g.fill3DRect(x+30,y,10,60,false);//右轮
                g.fill3DRect(x+10,y+10,20,40,false);//机体
                g.fillOval(x+10,y+20,20,20);//驾驶舱
                g.drawLine(x+20,y+30,x+20,y);//炮筒
                break;
            default:
                System.out.println("NULL SOLUTION");
        }

    }
}
