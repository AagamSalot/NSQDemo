import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;


public class ZMQPublisher {

    public static void main (String[] args) throws Exception {
        Context context = ZMQ.context(1);
        Socket publisher = context.socket(ZMQ.PUB);

        publisher.bind("tcp://localhost:5563");
        System.out.println("Starting Publisher..");
        publisher.setIdentity("B".getBytes());
//        publisher.setRcvHWM(1);
//        publisher.setSendBufferSize(2*1024);
        // for testing setting sleep at 100ms to ensure started.
        Thread.sleep(100l);
        for (int i = 1; i <= 1000; i++) {
            publisher.sendMore("B");
            boolean isSent = publisher.send("X("+System.currentTimeMillis()+"):"+i);
            System.out.println("Message was sent "+i+" , "+isSent);
            Thread.sleep(1000);
        }

        publisher.close ();
        context.term ();
    }
}