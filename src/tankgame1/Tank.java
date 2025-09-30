package tankgame1;

//所有坦克的父类
public class Tank {
    private int x = 0;
    private int y = 0;
    private int direct = 0;
    private int speed = 1;

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
