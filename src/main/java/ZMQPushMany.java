import org.zeromq.ZMQ;

public class ZMQPushMany {
    public static void main(String[] args) {
        {
            for (int j = 0; j < 3; j++) {
                new Thread(new Runnable() {
                    public void run() {
                        ZMQ.Context context = ZMQ.context(1);
                        ZMQ.Socket push = context.socket(ZMQ.PUSH);
                        push.setSndHWM(0);
                        push.connect("tcp://localhost:7070");
                        for (int i = 0; i < 2000; i++) {
                            push.send(("hello "+i).getBytes());
                            System.out.println(i);
                            //blocked after a while if Pull is not available at moment
                            // (store the data in buffer,pull will use it once it's available)
                        }

                        push.close();
                        context.term();
                    }

                }).start();
                ;

            }

        }
    }
}
