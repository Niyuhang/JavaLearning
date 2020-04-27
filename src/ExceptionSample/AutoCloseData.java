package ExceptionSample;
import java.io.IOException;
public class AutoCloseData implements AutoCloseable{
    public void read() throws IOException{
        throw new IOException("失败了");
    }

    @Override
    public void close() throws Exception {
        // 使用AutoCloseable 在发生异常的时候自动调用close
        System.out.println("处理了");
    }
}
