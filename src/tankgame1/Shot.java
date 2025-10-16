package tankgame1;

public class Shot implements Runnable{
    int x;
    int y;
    int direct;
    int speed = 5;
    boolean live = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while(live){
            //利用休眠对坐标的改变产生动态效果
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //设置射击方向
            switch (direct) {
                case 0://向上
                    y -= speed;
                    break;
                case 1://向右
                    x += speed;
                    break;
                case 2://向下
                    y += speed;
                    break;
                case 3://向左
                    x -= speed;
                    break;
            }
            //1、判断是否到达边界
            //2、判断是否击中敌人（即判断live状态有没有改变）
            if(!(x>=0 && x<=1000 && y>=0 && y<=800 && live)){
                live = false;//结束线程，让射击死亡
            }
        }
    }
}
