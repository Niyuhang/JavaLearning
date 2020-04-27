package ExceptionSample;

import java.io.IOException;

public class AutoCloseWithTry {
    public static void main(String[] args) {
        try (
                // 创建一个AutoCloseable的对象
                AutoCloseData data = new AutoCloseData();
        ) {
            data.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
