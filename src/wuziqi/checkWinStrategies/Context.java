package wuziqi.checkWinStrategies;
import wuziqi.*;

public class Context {
    private Strategy strategy;

    private void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public boolean run(Strategy strategy, int x, int y, QiZi qizi, QiZi[][] wuziqipan, int checkCode) {
        setStrategy(strategy);
        return this.strategy.execute(x, y, qizi, wuziqipan, checkCode);
    }
}
