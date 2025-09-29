package tankgame1;

import javax.swing.*;
import java.awt.*;

//坦克大战的场地
public class TankGame extends JFrame {
    private MyTank myTank = null;
    public TankGame() {
        MyPanel mp = new MyPanel();
        this.add(mp);
        this.setSize(1000,800);
        this.setVisible(true);
        this.setBackground(Color.red);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TankGame();
    }
}
