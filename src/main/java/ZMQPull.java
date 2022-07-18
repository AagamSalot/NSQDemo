import org.zeromq.ZMQ;

import java.util.concurrent.atomic.AtomicInteger;

public class ZMQPull {
    public static void main(String[] args) {
        final AtomicInteger number = new AtomicInteger(0);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                private int here = 0;

                public void run() {
                    //TODO Auto-generated method stub
                    ZMQ.Context context = ZMQ.context(1);
                    ZMQ.Socket pull = context.socket(ZMQ.PULL);

                    pull.connect("tcp://localhost:5583");
                    //pull.connect("ipc://fjs");
                    while (true) {
                        String message = new String(pull.recv());
                        System.out.println("Received..."+message+" name = "+Thread.currentThread().getName());
                        int now = number.incrementAndGet();
                        here++;

                        if (now==100) {
                            System.out.println(now + "here is:" + here);
                        }

                    }

                }

            }).start();
        }
    }
}