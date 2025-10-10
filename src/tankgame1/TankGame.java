package tankgame1;

import javax.swing.*;
import java.awt.*;

//坦克大战的场地
public class TankGame extends JFrame {
    private MyTank myTank = null;
    public TankGame() {
        MyPanel mp = new MyPanel();
        Thread thread = new Thread(mp);//启动画板线程
        thread.start();
        this.add(mp);
        this.setSize(1000,800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
    }

    public static void main(String[] args) {
        new TankGame();
    }
}
