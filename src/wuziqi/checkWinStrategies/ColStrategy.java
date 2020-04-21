package wuziqi.checkWinStrategies;
import wuziqi.QiZi;

public class ColStrategy implements Strategy {
    @Override
    public boolean execute(int x, int y, QiZi qizi, QiZi[][] wuziqipan, int checkCode) {
        // 横向从当前点向两边出发
        // 比y大的地方
        int greaterPart = 0;
        // 比y大的地方
        int lowerPart = 0;
        for (int i = x; i < wuziqipan.length; i++) {
            if (!(wuziqipan[i][y] == null) && wuziqipan[i][y].getCode() == checkCode) {
                greaterPart += 1;
            } else {
                break;
            }
        }
        for (int i = x; i >= 0; i--) {
            if (!(wuziqipan[i][y] == null) && wuziqipan[i][y].getCode() == checkCode) {
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
