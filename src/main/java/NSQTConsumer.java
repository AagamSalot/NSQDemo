import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import zmq.socket.pubsub.XPub;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class NSQTConsumer extends  Thread{
    public static void main(String[] args) throws InterruptedException {
        NSQLookup nsqLookup = new DefaultNSQLookup();
        nsqLookup.addLookupAddress("localhost", 4161);
        for(int i=0;i<1;i++){
            NSQTConsumer nsqtConsumer = new NSQTConsumer();
            nsqtConsumer.setName("Thread_"+i);
            Thread.sleep(100);
            nsqtConsumer.start();
        }
    }


    @Override
    public void run() {
        NSQLookup nsqLookup = new DefaultNSQLookup();
        nsqLookup.addLookupAddress("localhost", 4161);
        String s1 = Thread.currentThread().getName();
        System.out.println("name of the thread==="+s1);
        NSQMessageCallback nsqMessageCallback = new NSQMessageCallback() {
            @Override
            public void message(NSQMessage message) {
                {
//                    System.out.println("received: " + message);
                    System.out.println("Msg from Consumer" +s1+"====="+ new String(message.getMessage()));
                    //now mark the message as finished.
                    message.finished();

                    //or you could requeue it, which indicates a failure and puts it back on the queue.
                    //message.requeue();
                }
            }
        };
        NSQConsumer consumer1 = new NSQConsumer(nsqLookup, "test1", "channel_11", nsqMessageCallback);
        consumer1.setExecutor(Executors.newFixedThreadPool(6));
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello...............");
//            }
//        });
//        consumer1.setExecutor(executorService);
        consumer1.start();
    }
}
