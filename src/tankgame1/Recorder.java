package tankgame1;

import java.io.*;
import java.util.Vector;

//该类用于记录相关信息，与文件进行交互
public class Recorder {
    //定义击败敌方坦克的数量
    private static int allEnemyTankNum = 0;
    //定义敌方坦克Vector
    private static Vector<EnemyTank> enemyTanks = null;
    //定义一个Node类型的Vector用于对数据的恢复
    private static Vector<Node> nodes = new Vector<>();
    //定义IO对象，将数据写入文件
    private static BufferedWriter bw = null;
    //定义IO对象，从文件中读取数据
    private static BufferedReader br = null;
    //定义存储路径,将记录文件保存到src
    private static String recorderFile = "src/myRecord.txt";

    //读取myRecorder.txt中的信息，对坦克的坐标进行恢复
    //该方法需要在继续上局游戏的时候调用
    public static Vector<Node> getEnemyTankNodes() {
        try {
            br = new BufferedReader(new FileReader(recorderFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            String line = "";
            while((line = br.readLine())!=null){
                String[] xzd = line.split(" ");
                Node node = new Node(Integer.parseInt(xzd[0]), Integer.parseInt(xzd[1]), Integer.parseInt(xzd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return nodes;
    }

    //当击毁一个敌方坦克时，对allEnemyTankNum进行修改
    public static void changeNum(){
        allEnemyTankNum++;
    }

    //当游戏退出时，将allEnemyTank保存到文件中
    public static void saveRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(recorderFile));
            bw.write(String.valueOf(allEnemyTankNum));
            bw.write(System.lineSeparator());
            //遍历敌方坦克，记录存活的敌方坦克的坐标和方向
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive()){
                    bw.write(String.valueOf(enemyTank.getX())+" "+String.valueOf(enemyTank.getY())+" "+enemyTank.getDirect());
                    bw.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(bw != null){
                    bw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }
}
