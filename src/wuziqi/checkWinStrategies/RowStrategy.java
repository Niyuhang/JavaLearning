package wuziqi.checkWinStrategies;
import wuziqi.*;

public class RowStrategy implements Strategy {
    @Override
    public boolean execute(int x, int y, QiZi qizi, QiZi[][] wuziqipan, int checkCode) {
        // 横向从当前点向两边出发
        // 比y大的地方
        int greaterPart = 0;
        // 比y大的地方
        int lowerPart = 0;
        for (int j = y; j < wuziqipan[x].length; j++) {
            if (!(wuziqipan[x][j] == null) && wuziqipan[x][j].getCode() == checkCode) {
                greaterPart += 1;
            } else {
                break;
            }
        }
        for (int j = y; j >= 0; j--) {
            if (!(wuziqipan[x][j] == null) && wuziqipan[x][j].getCode() == checkCode) {
                lowerPart += 1;
            } else {
                break;
            }
        }
        // 如果总和大于6个棋子就赢了
        if (lowerPart + greaterPart >= 6) {
            return true;
        }
        return false;
    }
}
