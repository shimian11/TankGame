package tankgame1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//将画板设置成线程，使其不断的重绘画板，产生动态效果
public class MyPanel extends JPanel implements KeyListener,Runnable {
    //敌方坦克
    Vector<EnemyTank> enemyTanks =  new Vector<>();
    int enemyNum = 3;
    //我方坦克
    private MyTank myTank = null;
    //定义存放Node的Vector对象，用于恢复上局游戏的信息
    Vector<Node> nodes = new Vector<>();
    //定义爆炸效果
    Vector<Bomb> bombs = new Vector<>();
    //定义三张爆炸的图片
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;


    public MyPanel(String key) {
        //初始化己方坦克
        myTank = new MyTank(380,600);
        myTank.setSpeed(4);
        //给myTank传入enemyTanks
        myTank.setEnemyTanks(enemyTanks);

        //根据传入的key值判断是否进行新游戏
        switch (key){
            case "1":
                //重置击毁敌方坦克的数量
                Recorder.setAllEnemyTankNum(0);
                //初始化敌方坦克
                for (int i = 0; i < enemyNum; i++) {
                    //创建敌人坦克
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 20);
                    //设置Vector<Tank>otherTanks
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirect(2);
                    //启动线程
                    new Thread(enemyTank).start();
                    //为敌方坦克添加子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    //启动shot线程,对坐标产生变化
                    new Thread(shot).start();
                    //将单个坦克添加到集合
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                //恢复的上局信息
                nodes = Recorder.getEnemyTankNodes();
                //用上局游戏的数据初始化敌方坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //创建敌人坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //设置方向
                    enemyTank.setDirect(node.getDirect());
                    //设置Vector<Tank>otherTanks
                    enemyTank.setEnemyTanks(enemyTanks);
                    //启动线程
                    new Thread(enemyTank).start();
                    //为敌方坦克添加子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    //启动shot线程,对坐标产生变化
                    new Thread(shot).start();
                    //将单个坦克添加到集合
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("你的输入有误");
        }

        //将enemyTanks传入Recorder中
        Recorder.setEnemyTanks(enemyTanks);

        //对爆炸图片进行初始化
        img1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
        img3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));

        //播放指定的音乐
        new AePlayWave("src\\111.wav").start();

    }

    //编写方法，显示我方击毁坦克的数量
    public void showInfo(Graphics g) {
        //编写显示玩家总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        //画出图标和文字
        g.drawString("您累计击毁敌方坦克",1020,30);
        paintTank(1020,60,g,0,0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);
    }

    //画板本体
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //初始背景为黑色
        g.fillRect(0,0,1000,800);

        //画出计分板
        showInfo(g);

        //对画出主角坦克的方法进行封装（防止每次画坦克都要写在方法体中）
        if (myTank.isLive() && myTank != null){
            paintTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(),0);
            //画出主角坦克射击的子弹
        /*
        if(myTank.shot != null && myTank.shot.live){
            g.setColor(Color.CYAN);
            g.draw3DRect(myTank.shot.x,myTank.shot.y,2,2,false);
        }
        */
            //对主角坦克发射子弹进行优化，使其能够射出多个子弹
            for (int i = 0; i < myTank.shots.size(); i++) {
                Shot shot =  myTank.shots.get(i);
                if (shot != null && shot.live){
                    g.setColor(Color.CYAN);
                    g.draw3DRect(shot.x,shot.y,2,2,false);
                }else{
                    //当子弹已经销毁死亡时
                    myTank.shots.remove(shot);
                }
            }
        }

        //画出敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //当坦克还存活时才进行绘制
            if(enemyTank.isLive()){
                paintTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
                //画出敌方坦克射出的子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if(shot.live) {
                        g.draw3DRect(enemyTank.shots.get(j).x,enemyTank.shots.get(j).y,2,2,false);
                    }else{
                        //将死亡的子弹清除
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

        //当Bombs中有对象时需要画出爆炸
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if(bomb.life>6){
                g.drawImage(img1,bomb.x,bomb.y,60,60,this);
            }else if(bomb.life>3){
                g.drawImage(img2, bomb.x, bomb.y, 60,60,this);
            }else {
                g.drawImage(img3,bomb.x,bomb.y,60,60,this);
            }
            bomb.lifeDown();
            //将已经死亡的爆炸删去
            if(bomb.life == 0){
                bombs.remove(bomb);
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

    //实现敌方坦克击中主角坦克后，主角坦克被销毁
    public void hitMyTank(){
        //遍历所有敌人并将其子弹取出
        for (EnemyTank enemyTank:enemyTanks){
            //判断是否击中主角坦克
            if(myTank.isLive()){
                hitTank(enemyTank.shots,myTank);
            }
        }
    }

    //实现击中敌方坦克后，敌方坦克被销毁
    public void hitEnemyTank(){
        //当主角坦克的子弹被发射并且还存活着
        if(myTank.shot!=null && myTank.shot.live){
            for (int i = 0; i < enemyTanks.size(); i++) {
                hitTank(myTank.shots,enemyTanks.get(i));//将子弹和敌方坦克都设置为死亡，不再绘制
            }
        }
    }

    //实现子弹击中坦克后，坦克销毁
    public void hitTank(Vector<Shot> shots, Tank tank){
            for(Shot shot:shots){
                switch(tank.getDirect()){
                case 0:
                case 2://朝向为上和朝向为下的判断是一样的
                    if(shot.x> tank.getX() && shot.x< tank.getX()+40 && shot.y> tank.getY() && shot.y< tank.getY()+60){
                        shot.live = false;
                        tank.setLive(false);
                        //将被击中的坦克销毁
                        enemyTanks.remove(tank);

                        //对被击中的坦克进行判断，如果是敌方坦克就修改Recorder中的allEnemyTankNum
                        if(tank instanceof EnemyTank){
                            Recorder.changeNum();
                        }

                        //创建一个Bomb对象加入到bombs
                        Bomb bomb= new Bomb(tank.getX(), tank.getY());
                        bombs.add(bomb);
                    }
                    break;
                case 1:
                case 3://朝向为左右判断是一样的
                    if(shot.x> tank.getX() && shot.x< tank.getX()+60 && shot.y> tank.getY() && shot.y< tank.getY()+40){
                        shot.live = false;
                        tank.setLive(false);
                        //将被击中的坦克销毁
                        enemyTanks.remove(tank);

                        //对被击中的坦克进行判断，如果是敌方坦克就修改Recorder中的allEnemyTankNum
                        if(tank instanceof EnemyTank){
                            Recorder.changeNum();
                        }

                        //创建Bomb对象
                        Bomb bomb = new Bomb(tank.getX(), tank.getY());
                        bombs.add(bomb);
                    }
                    break;
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //当主角坦克死亡时退出
        if(!myTank.isLive()){
            return;
        }

        //保存当前位置，用于碰撞时进行回退
        int oldX = myTank.getX();
        int oldY = myTank.getY();

        //主角坦克的移动
        if(e.getKeyCode() == KeyEvent.VK_W){
            myTank.setDirect(0);
            //对移动进行限制
            if(myTank.getY()>0 ){
                myTank.MoveUp();
            }
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            myTank.setDirect(1);
            //对移动进行限制
            if(myTank.getX()+60<1000){
                myTank.MoveRight();
            }
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            myTank.setDirect(2);
            //对移动进行限制
            if(myTank.getY()+60<800){
                myTank.MoveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDirect(3);
            //对移动进行限制
            if(myTank.getX()>0){
                myTank.MoveLeft();
            }
        }


        //实现己方坦克的射击事件
        if(e.getKeyCode() == KeyEvent.VK_J){
            //发射一颗子弹的情况 判断子弹是否销毁
            /**
             * 我们可以发现尽管子弹已经死亡，线程销毁了，但按J时仍然不会发射子弹
             * 因为这里的判断条件是子弹是否为空，但是尽管线程销毁了，之前创建的子弹仍然不会清空
             * 所以要加入判断条件，子弹是否死亡
             */
//            if(myTank.shot==null || !myTank.shot.live){
//                //创建子弹并发射
//                myTank.shotTank();
//            }
           myTank.shotTank();
        }

        //检测是否与其他坦克进行了碰撞，如果发生就回退到原位置
        if(myTank.TouchOther()){
            myTank.setX(oldX);
            myTank.setY(oldY);
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
            //判断是否击中敌方坦克
            hitEnemyTank();

            //判断是否击中了主角坦克
            hitMyTank();

            this.repaint();
        }
    }
}
