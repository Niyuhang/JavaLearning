package ProducerAndConsumer;

import java.util.Queue;

public class Consumer<T> {
    private final Queue<T> tasks;

    public Consumer(Queue<T> tasks) {
        this.tasks = tasks;
    }

    public void consumeForever(){
        while (true){
            consume();
        }
    }

    private void consume() {
        System.out.println("消费线程开始消费");
        // 首先进行条件判定
        // 消费者要判定是否有生产
        synchronized (tasks) {
            while (tasks.size() == 0) {
                System.out.println("消费线程开始等待");
                try {
                    tasks.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T ret = tasks.poll();
            System.out.println("消费者消费了" + ret);
            // 通知所有线程
            tasks.notifyAll();
        }
    }
}
