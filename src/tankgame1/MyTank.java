package tankgame1;

import java.util.Vector;

//主角坦克
public class MyTank extends Tank {
    //定义射击的子弹
    Shot shot = null;

    //发射多个子弹
    Vector<Shot> shots = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
    }

    //射击动作
    public void shotTank(){
        //对子弹数量进行控制
        if(shots.size()>=5){
            return;
        }

        //根据方向创建子弹对象
        switch(getDirect()){
            case 0://向上
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1://向右
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2://向下
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3://向左
                shot = new Shot(getX(),getY()+20,3);
                break;
        }

        //将根据方向新建的子弹加入到集合中
        shots.add(shot);

        //启动shot线程,对坐标开始变化
        new Thread(shot).start();
    }
}
