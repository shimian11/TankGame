package tankgame1;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("all")
public class EnemyTank extends Tank implements Runnable{
    //定义敌方坦克的子弹（不止一个）
    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while(true){
            //判断当子弹小于限定值时，补充子弹
            if(isLive() && shots.size()<6){
                Shot shot = null;

                switch(getDirect()){
                    case 0:
                        shot = new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        shot = new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        shot = new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        shot = new Shot(getX(),getY()+60,3);
                        break;
                }
                //将子弹添加入集合，并启动线程
                shots.add(shot);
                new Thread(shot).start();
            }

            //产生改变方向的效果(0-3)
            setDirect(ThreadLocalRandom.current().nextInt(4));

            //产生移动的效果
            switch(getDirect()){
                case 0:
                    //产生变换方向后的连续移动效果
                    for (int i = 0; i < 30; i++) {
                        //对坦克的移动范围进行约束
                        if(getY()>0 && !TouchOther()){
                            MoveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    //产生变换方向后的连续移动效果
                    for (int i = 0; i < 30; i++) {
                        //对坦克的移动范围进行约束
                        if(getX()+60<1000 && !TouchOther()){
                            MoveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    //产生变换方向后的连续移动效果
                    for (int i = 0; i < 30; i++) {
                        //对坦克的移动范围进行约束
                        if(getY()+60<800 && !TouchOther()){
                            MoveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    //产生变换方向后的连续移动效果
                    for (int i = 0; i < 30; i++) {
                        //对坦克的移动范围进行约束
                        if(getX()>0 && !TouchOther()){
                            MoveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }

            //当坦克死亡退出run
            if(!isLive()){
                break;
            }
        }
    }
}