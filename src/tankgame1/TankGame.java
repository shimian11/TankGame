package tankgame1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

//坦克大战的场地
public class TankGame extends JFrame {
    static Scanner sc = new Scanner(System.in);
    //判断是否继续上局游戏
    static String key = "";
    private MyTank myTank = null;
    public TankGame() {
        MyPanel mp = new MyPanel(key);
        Thread thread = new Thread(mp);//启动画板线程
        thread.start();
        this.add(mp);
        this.setSize(1300,800 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);

        //增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Recorder.saveRecord();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        System.out.println("请输入选择 1：新游戏  2：继续上局游戏");
        key = sc.nextLine();

        new TankGame();
    }
}
