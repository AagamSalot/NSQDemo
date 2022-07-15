import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

import java.util.concurrent.atomic.AtomicInteger;

public class NSQTConsumer extends  Thread{
    public static void main(String[] args) {
        NSQLookup nsqLookup = new DefaultNSQLookup();
        nsqLookup.addLookupAddress("localhost", 4161);
        for(int i=0;i<5;i++){
            NSQTConsumer nsqtConsumer = new NSQTConsumer();
            nsqtConsumer.setName("Thread "+i);
            nsqtConsumer.start();
        }
    }


    @Override
    public void run() {
        NSQLookup nsqLookup = new DefaultNSQLookup();
        nsqLookup.addLookupAddress("localhost", 4161);
        String s1 = Thread.currentThread().getName();
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
        NSQConsumer consumer1 = new NSQConsumer(nsqLookup, "test1", "channel1", nsqMessageCallback);
        consumer1.start();
    }
}
