import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class NSQTProducer{
    public static void main(String[] args) throws NSQException, TimeoutException {
        NSQProducer producer = new NSQProducer();
        producer.addAddress("localhost", 4150);
        producer.start();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        NSQTProducer nsqtProducer = new NSQTProducer();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    producer.produce("test1", ("this is a message "+Thread.currentThread().getName()).getBytes());
                } catch (NSQException e) {
                    throw new RuntimeException(e);
                } catch (TimeoutException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        for(int i=0;i<10000;i++){
            executorService.execute(r);
        }


//        producer.produce("test1", ("this is a message").getBytes());
    }
}
