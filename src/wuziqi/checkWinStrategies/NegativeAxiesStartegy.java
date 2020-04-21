package wuziqi.checkWinStrategies;

import wuziqi.QiZi;

public class NegativeAxiesStartegy implements Strategy {

    @Override
    public boolean execute(int x, int y, QiZi qizi, QiZi[][] wuziqipan, int checkCode) {

        int greaterPart = 0;
        // 比x大的地方
        int lowerPart = 0;
        // 副对称轴检查
        for (int i = x, j = y; i < wuziqipan.length && j >= 0; i++, j--) {
            if (!(wuziqipan[i][j] == null) && wuziqipan[i][j].getCode() == checkCode) {
                greaterPart += 1;
            } else {
                break;
            }
        }
        for (int i = x, j = y; i >= 0 && j < wuziqipan[i].length; i--, j++) {
            if (!(wuziqipan[i][j] == null) && wuziqipan[i][j].getCode() == checkCode) {
                lowerPart += 1;
            } else {
                break;
            }
        }
        // 如果总和大于6个棋子就赢了
        if (greaterPart + lowerPart >= 6) {
            return true;
        }

        return false;
    }
}
