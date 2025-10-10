package tankgame1;

import java.util.Vector;

public class EnemyTank extends Tank {
    //定义敌方坦克的子弹（不止一个）
    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }
}
