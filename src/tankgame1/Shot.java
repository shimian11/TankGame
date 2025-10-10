package tankgame1;

public class Shot implements Runnable{
    int x;
    int y;
    int direct;
    int speed = 2;
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
            System.out.println("x="+x+",y="+y);
            //判断是否到达边界
            if(!(x>=0 && x<=1000 && y>=0 && y<=800)){
                live = false;//结束线程，让射击死亡
                System.out.println(Thread.currentThread().getName()+"子弹死亡");
            }
        }
    }
}
