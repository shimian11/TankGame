package draw;

import javax.swing.*;
import java.awt.*;

public class drawMethod extends JFrame{
    private Panel panel = null;

    public drawMethod(){
        panel = new Panel();
        this.add(panel);
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new drawMethod();
    }
}

class Panel extends JPanel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //画圆
        g.drawOval(60,60,100,100);
        //画直线
        g.drawLine(20,20,100,10);//起点到终点
        //画矩形
        g.drawRect(10,10,100,20);//左上角和长宽
        //填充矩形
        //设置画笔颜色
        g.setColor(Color.blue);
        g.fillRect(10,10,100,100);
        //填充椭圆
        g.setColor(Color.red);
        g.fillOval(60,90,10,100);
        //画图片
        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/test.png"));
        g.drawImage(image,200,200,300,300,this);//this表示在当前画板画
        //设置字体
        g.setColor(Color.PINK);
        g.setFont(new Font("宋体",Font.BOLD,50));
        g.drawString("喜欢学JAVA",500,600);
    }
}