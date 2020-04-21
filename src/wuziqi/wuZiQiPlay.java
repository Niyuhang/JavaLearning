package wuziqi;

import java.util.Scanner;

public class wuZiQiPlay {
    public static void main(String[] args) {
        System.out.println("================" + "开始五子棋游戏" + "================");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入黑方选手姓名");
        String blackName = sc.nextLine();
        System.out.println("请输入白方选手姓名");
        String whiteName = sc.nextLine();
        // 设置参赛人员并且赋值
        String[] members = new String[] {blackName, whiteName};
        boolean play = true;
        wuZiQi wuZiQiPan = new wuZiQi();
        while (play) {
            for (int i = 0; i < members.length; i++) {
                qiZi currentQiZi = qiZi.CURRENT_BLACK;
                String member = blackName;
                if (i == 1) {
                    currentQiZi = qiZi.CURRENT_WHITE;
                    member = whiteName;
                }
                System.out.println("请" + member + "输入第几行落点");
                int x = sc.nextInt();
                System.out.println("请" + member + "输入第几列落点");
                int y = sc.nextInt();

                wuZiQiPan.addQiZi(x, y, currentQiZi);
                if (wuZiQiPan.checkWin(x, y, currentQiZi)) {
                    System.out.println("=====恭喜呀" + member + "赢了======");
                    play = false;
                    break;
                }
            }

        }
    }
}
