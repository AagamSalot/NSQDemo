import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;


public class ZMQSubscriber {

    public static void main (String[] args) {

        // Prepare our context and subscriber
        Context context = ZMQ.context(1);
        Socket subscriber = context.socket(ZMQ.SUB);

        subscriber.connect("tcp://localhost:5563");
        subscriber.subscribe(ZMQ.SUBSCRIPTION_ALL);
//       subscriber.setRcvHWM(1);
//       subscriber.setReceiveBufferSize(2*1024);

        System.out.println("Starting Subscriber..");
        int i = 0;
        while (true) {
               String address = subscriber.recvStr();
//            System.out.println("Inside while");
            String contents = subscriber.recvStr(0);
            System.out.println(":"+new String(contents) + ": "+(i));
            i++;
        }

    }
}
