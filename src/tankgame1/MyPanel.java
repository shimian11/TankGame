package tankgame1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//画板
public class MyPanel extends JPanel implements KeyListener {
    private MyTank myTank = null;

    public MyPanel() {
        myTank = new MyTank(100,100);
        myTank.setSpeed(3);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,800);
        //对画出坦克的方法进行封装（防止每次画坦克都要写在方法体中）
        paintTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(),0);
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
            case 1://朝右
                g.fill3DRect(x,y,60,10,false);//左轮
                g.fill3DRect(x,y+30,60,10,false);//右轮
                g.fill3DRect(x+10,y+10,40,20,false);//机体
                g.fillOval(x+20,y+10,20,20);//驾驶舱
                g.drawLine(x+30,y+20,x+60,y+20);//炮筒
                break;
            case 2://朝下
                g.fill3DRect(x,y,10,60,false);//左轮
                g.fill3DRect(x+30,y,10,60,false);//右轮
                g.fill3DRect(x+10,y+10,20,40,false);//机体
                g.fillOval(x+10,y+20,20,20);//驾驶舱
                g.drawLine(x+20,y+30,x+20,y+60);//炮筒
                break;
            case 3://朝左
                g.fill3DRect(x,y,60,10,false);//左轮
                g.fill3DRect(x,y+30,60,10,false);//右轮
                g.fill3DRect(x+10,y+10,40,20,false);//机体
                g.fillOval(x+20,y+10,20,20);//驾驶舱
                g.drawLine(x+30,y+20,x,y+20);//炮筒
                break;
            default:
                System.out.println("NULL SOLUTION");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            myTank.setDirect(0);
            myTank.MoveUp();
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            myTank.setDirect(1);
            myTank.MoveRight();
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            myTank.setDirect(2);
            myTank.MoveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDirect(3);
            myTank.MoveLeft();
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
