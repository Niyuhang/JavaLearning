package ThreadSample;

import static java.lang.Thread.sleep;

public class ThreadSample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("当前线程" + Thread.currentThread().getName());
        // 传入一个Runable对象
        Thread thread = new Thread(new PrintRun());
        // 启动
        thread.start();
        // join 等待线程结束
        thread.join();
        System.out.println("当前线程" + Thread.currentThread().getName());

    }

    private static class PrintRun implements Runnable {

        @Override
        public void run() {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("切换到了当前线程" + Thread.currentThread().getName());
        }
    }
}
