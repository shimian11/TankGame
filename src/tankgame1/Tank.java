package tankgame1;

import java.util.Vector;

//所有坦克的父类
public class Tank {
    private int x = 0;
    private int y = 0;
    private int direct = 0;
    private int speed = 1;
    private boolean isLive = true;

    //增加成员，使得Tank可以得到其他EnemyTank的信息，从而进行碰撞判断
    private Vector<EnemyTank> enemyTanks;

    //设置一个方法，获得MyPanel中的Vector<EnemyTank>
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法，判断当前这个坦克是否和其他坦克发生重叠或者碰撞
    public boolean TouchOther(){
        switch (this.direct){
            case 0://自己朝向向上
                //让当前坦克与其他坦克进行比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    Tank other = enemyTanks.get(i);
                    //不与自己进行比较
                    if(this == enemyTanks.get(i)){
                        continue;
                    }

                    //对其他坦克朝向分类
                    if(other.getDirect() == 0 || other.getDirect() == 2){//其他坦克朝向上下的情况
                        //根据当前坦克的左上角和右上角进行判断
                        if(this.getX()>other.getX()
                                && this.getX()< other.getX()+40
                                && this.getY()<other.getY()+60
                                && this.getY()>other.getY()){
                            return true;
                        }else if(this.getX()+40>other.getX()
                                && this.getX()+40< other.getX()+40
                                && this.getY()<other.getY()+60
                                && this.getY()>other.getY()){
                            return true;
                        }
                    }else if (other.getDirect() == 1 || other.getDirect() == 3){//其他坦克朝向左右的情况
                        //根据当前坦克的左上角和右上角进行判断
                        if(this.getX()>other.getX()
                                && this.getX()< other.getX()+60
                                && this.getY()<other.getY()+40
                                && this.getY()>other.getY()){
                            return true;
                        }else if(this.getX()+40>other.getX()
                                && this.getX()+40< other.getX()+60
                                && this.getY()<other.getY()+40
                                && this.getY()>other.getY()){
                            return true;
                        }
                    }
                }
                break;
            case 1://自己朝向向右
                //让当前坦克与其他坦克进行比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    Tank other = enemyTanks.get(i);
                    //不与自己进行比较
                    if(this == enemyTanks.get(i)){
                        continue;
                    }

                    //对其他坦克朝向分类
                    if(other.getDirect() == 0 || other.getDirect() == 2){//其他坦克朝向上下的情况
                        //根据当前坦克的左上角

                        if(this.getX()+60>other.getX()
                                && this.getX()+60< other.getX()+40
                                && this.getY()<other.getY()+60
                                && this.getY()>other.getY()){
                            return true;
                        }
                        //右上角进行判断
                        else if(this.getX()+60>other.getX()
                                && this.getX()+60< other.getX()+40
                                && this.getY()+40<other.getY()+60
                                && this.getY()+40>other.getY()){
                            return true;
                        }
                    }else if (other.getDirect() == 1 || other.getDirect() == 3){//其他坦克朝向左右的情况
                        //根据当前坦克的右上角进行判断
                        if(this.getX()+60>other.getX()
                                && this.getX()+60< other.getX()+60
                                && this.getY()<other.getY()+40
                                && this.getY()>other.getY()){
                            return true;
                        }
                        //右下角
                        else if(this.getX()+60>other.getX()
                                && this.getX()+60< other.getX()+60
                                && this.getY()+40<other.getY()+40
                                && this.getY()+40>other.getY()){
                            return true;
                        }
                    }
                }
                break;
            case 2://自己朝向向下
                //让当前坦克与其他坦克进行比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    Tank other = enemyTanks.get(i);
                    //不与自己进行比较
                    if(this == enemyTanks.get(i)){
                        continue;
                    }

                    //对其他坦克朝向分类
                    if(other.getDirect() == 0 || other.getDirect() == 2){//其他坦克朝向上下的情况
                        //根据当前坦克的左下角

                        if(this.getX()>other.getX()
                                && this.getX()< other.getX()+40
                                && this.getY()+60<other.getY()+60
                                && this.getY()+60>other.getY()){
                            return true;
                        }
                        //右下角进行判断
                        else if(this.getX()+40>other.getX()
                                && this.getX()+40< other.getX()+40
                                && this.getY()+60<other.getY()+60
                                && this.getY()+60>other.getY()){
                            return true;
                        }
                    }else if (other.getDirect() == 1 || other.getDirect() == 3){//其他坦克朝向左右的情况
                        //根据当前坦克的左下角进行判断
                        if(this.getX()>other.getX()
                                && this.getX()< other.getX()+60
                                && this.getY()+60<other.getY()+40
                                && this.getY()+60>other.getY()){
                            return true;
                        }
                        //右下角
                        else if(this.getX()+40>other.getX()
                                && this.getX()+40< other.getX()+60
                                && this.getY()+60<other.getY()+40
                                && this.getY()+60>other.getY()){
                            return true;
                        }
                    }
                }
                break;
            case 3://自己朝向向左
                //让当前坦克与其他坦克进行比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    Tank other = enemyTanks.get(i);
                    //不与自己进行比较
                    if(this == enemyTanks.get(i)){
                        continue;
                    }

                    //对其他坦克朝向分类
                    if(other.getDirect() == 0 || other.getDirect() == 2){//其他坦克朝向上下的情况
                        //根据当前坦克的左上角

                        if(this.getX()>other.getX()
                                && this.getX()< other.getX()+40
                                && this.getY()<other.getY()+60
                                && this.getY()>other.getY()){
                            return true;
                        }
                        //左下角进行判断
                        else if(this.getX()>other.getX()
                                && this.getX()< other.getX()+40
                                && this.getY()+40<other.getY()+60
                                && this.getY()+40>other.getY()){
                            return true;
                        }
                    }else if (other.getDirect() == 1 || other.getDirect() == 3){//其他坦克朝向左右的情况
                        //根据当前坦克的左上角进行判断
                        if(this.getX()>other.getX()
                                && this.getX()< other.getX()+60
                                && this.getY()<other.getY()+40
                                && this.getY()>other.getY()){
                            return true;
                        }
                        //左下角
                        else if(this.getX()>other.getX()
                                && this.getX()< other.getX()+60
                                && this.getY()+40<other.getY()+40
                                && this.getY()+40>other.getY()){
                            return true;
                        }
                    }
                }
                break;
        }
        return false;//没有发生碰撞
    }


    public void MoveUp(){
        y -= speed;
    }

    public void MoveDown(){
        y += speed;
    }

    public void MoveLeft(){
        x -= speed;
    }
    public void MoveRight(){
        x += speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
