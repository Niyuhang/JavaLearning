package wuziqi;

import java.util.Arrays;

public class wuZiQi {
    private qiZi[][] wuziqipan = new qiZi[11][11];
    private String head;
    private int[] current = new int[2];

    // 构造函数
    public wuZiQi() {
        this.makeHead();
        Arrays.fill(current, -1);
    }

    public static void main(String[] args) {
        wuZiQi wuziqi = new wuZiQi();
        wuziqi.addQiZi(0, 3, qiZi.CURRENT_BLACK);
        wuziqi.addQiZi(1, 3, qiZi.CURRENT_WHITE);
        wuziqi.addQiZi(1, 4, qiZi.CURRENT_WHITE);
        wuziqi.addQiZi(1, 5, qiZi.CURRENT_WHITE);
        wuziqi.addQiZi(1, 2, qiZi.CURRENT_WHITE);
        wuziqi.addQiZi(1, 1, qiZi.CURRENT_WHITE);
        System.out.println(wuziqi.checkWin(1, 4, qiZi.CURRENT_WHITE));
    }


    private void makeHead() {
        String head = "\t";
        for (int i = 0; i < wuziqipan.length; i++) {
            head += i + "\t";
        }
        this.head = head;
    }

    public void addQiZi(int x, int y, qiZi qizi) {
        /*
            增加棋子
            1. 把之前的最新的棋子设为对应的棋子
            2. 增加当前的棋子，并设为当前最新的棋子
        */
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
                        wuziqipan[currentX][currentY] = qiZi.WHITE;
                        break;
                    case CURRENT_BLACK:
                        wuziqipan[currentX][currentY] = qiZi.BLACK;
                        break;
                }
                current[0] = x;
                current[1] = y;

            }
            printTheTable();

        }
    }

    public boolean checkWin(int x, int y, qiZi qizi) {
        /*
           检查哪一方赢了
           检查刚下的子是否可以连成5个
        */
        int checkCode = qizi.getCode();

        // 横向从当前点向两边出发
        // 比y大的地方
        int greaterThanY = 0;
        // 比y大的地方
        int lowerThanY = 0;
        for (int j = y; j < wuziqipan[x].length; j++) {
            if (!(wuziqipan[x][j] == null) && wuziqipan[x][j].getCode() == checkCode) {
                greaterThanY += 1;
            } else {
                break;
            }
        }
        for (int j = y; j >= 0; j--) {
            if (!(wuziqipan[x][j] == null) && wuziqipan[x][j].getCode() == checkCode) {
                lowerThanY += 1;
            } else {
                break;
            }
        }
        // 如果总和大于6个棋子就赢了
        if (lowerThanY + greaterThanY >= 6) {
            return true;
        }

        //  纵向检查
        int greaterThanX = 0;
        // 比x大的地方
        int lowerThanX = 0;
        for (int i = x; i < wuziqipan.length; i++) {
            if (!(wuziqipan[i][y] == null) && wuziqipan[i][y].getCode() == checkCode) {
                greaterThanX += 1;
            } else {
                break;
            }
        }
        for (int i = x; i >= 0; i--) {
            if (!(wuziqipan[i][y] == null) && wuziqipan[i][y].getCode() == checkCode) {
                lowerThanX += 1;
            } else {
                break;
            }
        }
        // 如果总和大于6个棋子就赢了
        if (lowerThanX + greaterThanX >= 6) {
            return true;
        }

        //  正对称轴向检查
        int greaterThanXY = 0;
        // 比x大的地方
        int lowerThanXY = 0;
        for (int i = x, j = y; i < wuziqipan.length && j < wuziqipan[i].length; i++, j++) {
            System.out.println(wuziqipan[i][j]);
            if (!(wuziqipan[i][j] == null) && wuziqipan[i][j].getCode() == checkCode) {
                greaterThanXY += 1;
            } else {
                break;
            }
        }
        for (int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if (!(wuziqipan[i][j] == null) && wuziqipan[i][j].getCode() == checkCode) {
                lowerThanXY += 1;
            } else {
                break;
            }
        }
        // 如果总和大于6个棋子就赢了
        if (greaterThanXY + lowerThanXY >= 6) {
            return true;
        }

        int nextGreaterThanXY = 0;
        // 比x大的地方
        int nextLowerThanXY = 0;
        // 副对称轴检查
        for (int i = x, j = y; i < wuziqipan.length && j >= 0; i++, j--) {
            if (!(wuziqipan[i][j] == null) && wuziqipan[i][j].getCode() == checkCode) {
                nextGreaterThanXY += 1;
            } else {
                break;
            }
        }
        for (int i = x, j = y; i >= 0 && j < wuziqipan[i].length; i--, j++) {
            if (!(wuziqipan[i][j] == null) && wuziqipan[i][j].getCode() == checkCode) {
                nextLowerThanXY += 1;
            } else {
                break;
            }
        }
        // 如果总和大于6个棋子就赢了
        if (nextGreaterThanXY + nextLowerThanXY >= 6) {
            return true;
        }

        return false;

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
