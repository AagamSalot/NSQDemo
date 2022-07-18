import org.zeromq.ZMQ;

public class ZMQPush {

    public static void main(String[] args) throws InterruptedException {

        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.PUSH);

        socket.bind( "tcp://localhost:5583" );
        for  ( int  i = 0 ; i < 100 ; i++) {
            socket.send( "hello" .getBytes( ));
            Thread.sleep(200);
        }

        socket.close();

    }
}
