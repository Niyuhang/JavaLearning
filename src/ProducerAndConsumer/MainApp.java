package ProducerAndConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class MainApp {
    public static void main(String[] args) {
        Queue<String> tasks = new LinkedList<>();
        Consumer<String> consumer = new Consumer<>(tasks);
        Producer<String> producer = new Producer<>(tasks);

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(consumer::consumeForever, "消费线程-" + i);
            thread.start();
        }

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                while (true){
                    String data = MainApp.produceData();
                    producer.produce(data);
                }
            }, "生辰线程-" + i);
            thread.start();
        }
    }

    public static String produceData() {
        return "www." + "aaa";
    }
}
