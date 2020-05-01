package ProducerAndConsumer;

import java.util.Queue;

public class Producer<T> {

    private final Queue<T> tasks;
    private int maxTaskCount;

    public Producer(Queue<T> tasks) {
        this.tasks = tasks;
        this.maxTaskCount = 1024;
    }

    public void produce(T task) {
        System.out.println("生产线程开始生产");
        // 首先进行条件判定
        // 生产者要判定是否超出了最大长度
        synchronized (tasks) {
            while (tasks.size() >= maxTaskCount) {
                System.out.println("生产线程开始等待");
                try {
                    // 等待 直到同一个对象notify了
                    tasks.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产线程生产了" + task);
            tasks.add(task);
            // 通知所有线程
            tasks.notifyAll();
        }
    }
}
