package wuziqi.checkWinStrategies;
import wuziqi.*;

public interface Strategy {

    public boolean execute(int x, int y, QiZi qizi, QiZi[][] wuziqipan, int checkCode);
}

