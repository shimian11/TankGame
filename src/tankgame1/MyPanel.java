package tankgame1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//将画板设置成线程，使其不断的重绘画板，产生动态效果
public class MyPanel extends JPanel implements KeyListener,Runnable {
    Vector<EnemyTank> enemyTanks =  new Vector<>();
    int enemyNum = 3;
    private MyTank myTank = null;

    public MyPanel() {
        //初始化己方坦克
        myTank = new MyTank(380,600);
        myTank.setSpeed(3);

        //初始化敌方坦克
        for (int i = 0; i < enemyNum; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 20);
            //设置方向
            enemyTank.setDirect(2);
            //为敌方坦克添加子弹
            Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
            enemyTank.shots.add(shot);
            //启动shot线程,对坐标产生变化
            new Thread(shot).start();
            //将单个坦克添加到集合
            enemyTanks.add(enemyTank);
        }
    }
    //画板本体
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //初始背景为黑色
        g.fillRect(0,0,1000,800);

        //画出主角坦克射击的子弹
        if(myTank.shot != null && myTank.shot.live){
            g.setColor(Color.CYAN);
            g.draw3DRect(myTank.shot.x,myTank.shot.y,2,2,false);
        }

        //对画出坦克的方法进行封装（防止每次画坦克都要写在方法体中）
        paintTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(),0);

        //画出敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            paintTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
            //画出敌方坦克射出的子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if(shot.live) {
                    g.draw3DRect(enemyTank.shots.get(j).x,enemyTank.shots.get(j).y,2,2,false);
                }
            }
        }
    }

    /**
     * 画坦克的方法
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
        //坦克的移动
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
        //实现己方坦克的射击事件
        if(e.getKeyCode() == KeyEvent.VK_J){
            myTank.shotTank();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //设置成线程，对画板重绘，产生动态效果，即将坐标的变化表现出来
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }
}
