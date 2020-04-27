package wuziqi;
import wuziqi.checkWinStrategies.*;
import java.util.Arrays;

public class WuZiQi {
    private QiZi[][] wuziqipan = new QiZi[11][11];
    private String head;
    private int[] current = new int[2];

    // 构造函数
    public WuZiQi() {
        this.makeHead();
        Arrays.fill(current, -1);
    }

    public static void main(String[] args) {
        WuZiQi wuziqi = new WuZiQi();
        wuziqi.addQiZi(0, 3, QiZi.CURRENT_BLACK);
        wuziqi.addQiZi(1, 3, QiZi.CURRENT_WHITE);
        wuziqi.addQiZi(1, 4, QiZi.CURRENT_WHITE);
        wuziqi.addQiZi(1, 5, QiZi.CURRENT_WHITE);
        wuziqi.addQiZi(1, 2, QiZi.CURRENT_WHITE);
        wuziqi.addQiZi(1, 1, QiZi.CURRENT_WHITE);
        System.out.println(wuziqi.checkWin(1, 4, QiZi.CURRENT_WHITE));
    }


    private void makeHead() {
        String head = "\t";
        for (int i = 0; i < wuziqipan.length; i++) {
            head += i + "\t";
        }
        this.head = head;
    }

    /**
     * 增加棋子
     * 1. 把之前的最新的棋子设为对应的棋子
     * 2. 增加当前的棋子，并设为当前最新的棋子
     *
     * @param x: 第几行
     * @param y: 第几列
     * @param qizi:
     */
    public void addQiZi(int x, int y, QiZi qizi) {

        System.out.println("在" + x + "和" + y + "位置增加棋子");
        if (x < 0 || y < 0 || x >= wuziqipan.length || y >= wuziqipan[x].length) {
            // ToDo 抛出异常
            System.out.println("无效的落点");
        } else if (!(wuziqipan[x][y] == null)) {
            // ToDo 抛出异常
            System.out.println("当前已有落点");
        } else {
            wuziqipan[x][y] = qizi;
            if (current[0] < 0) {
                // 说明当前还没有下过棋子
                current[0] = x;
                current[1] = y;
            } else {
                int currentX = current[0];
                int currentY = current[1];
                switch (wuziqipan[currentX][currentY]) {
                    case CURRENT_WHITE:
                        wuziqipan[currentX][currentY] = QiZi.WHITE;
                        break;
                    case CURRENT_BLACK:
                        wuziqipan[currentX][currentY] = QiZi.BLACK;
                        break;
                }
                current[0] = x;
                current[1] = y;

            }
            printTheTable();

        }
    }

    public boolean checkWin(int x, int y, QiZi qizi) {
        /*
           检查哪一方赢了
           检查刚下的子是否可以连成5个
        */
        int checkCode = qizi.getCode();

        // 设置所有的运行策略
        Context checkContext = new Context();
        Strategy []  strategies = new Strategy [4];
        strategies[0] = new ColStrategy();
        strategies[1] = new RowStrategy();
        strategies[2] = new PasitiveAxiesStartegy();
        strategies[3] = new NegativeAxiesStartegy();

        boolean checkRes = false;
        for(Strategy strategy: strategies){
            if(checkContext.run(strategy, x, y, qizi, wuziqipan, checkCode)){
                checkRes = true;
                break;
            }
        }
        return checkRes;
    }

    public void printTheTable() {
        /*
            用于打印当前的五子棋棋谱
        */
        System.out.println("打印当前棋盘");
        System.out.println(head);
        for (int i = 0; i < wuziqipan.length; i++) {
            String line = i + "\t";
            for (int j = 0; j < wuziqipan[i].length; j++) {
                line += !(wuziqipan[i][j] == null) ?
                        wuziqipan[i][j].getValue() + "\t" :
                        "\t";
            }
            System.out.println(line);
        }

    }

}
