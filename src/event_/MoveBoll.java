package event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveBoll extends JFrame{
    private MyPanel mp = null;

    public MoveBoll() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400,400);
        this.addKeyListener(mp);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MoveBoll();
    }
}


class MyPanel extends JPanel implements KeyListener {
    int x = 10,y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }


    @Override //当键盘有输入时该方法调用
    public void keyTyped(KeyEvent e) {

    }

    @Override//当按下键时该方法调用
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y++;
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            y--;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            x--;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            x++;
        }

        this.repaint();
    }

    @Override//当松开键时该方法调用
    public void keyReleased(KeyEvent e) {

    }
}
