package ThreadSample;

public class ChangeNumThreadSample {

    int num = 0;

    public static void main(String[] args) {
        System.out.println("操作数据");
        ChangeNumThreadSample changeNumSample = new ChangeNumThreadSample();
        // 多线程操作同一个对象就会出现问题
        Thread thread1 = new Thread(new ChangeNumClass(changeNumSample, 1));
        Thread thread2 = new Thread(new ChangeNumClass(changeNumSample, -1));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {

        }
        System.out.println("最终的num" + changeNumSample.num);
    }

    // 同一时间只有一个线程可以执行
    public synchronized void changeNum(int num) {
        this.num += num;
    }

    static class ChangeNumClass implements Runnable {


        private final ChangeNumThreadSample changeNumSample;
        private final int DELTA;

        public ChangeNumClass(ChangeNumThreadSample numThread, int delta) {
            this.changeNumSample = numThread;
            this.DELTA = delta;
        }

        @Override
        public void run() {
            // 在线程中操作同一个对象
            for (int i = 0; i < 10000; i++) {
                this.changeNumSample.changeNum(this.DELTA);
            }
        }
    }
}
