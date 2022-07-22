import org.zeromq.ZMQ;

public class ZMQPullMany {
    public static void  main(String args[]) throws InterruptedException {
        ZMQ.Context context = ZMQ.context( 1 );
        ZMQ.Socket pull = context.socket(ZMQ.PULL);

        pull.setRcvHWM(1);
        pull.bind( "tcp://localhost:7070" );
        int  number = 0 ;
        while  ( true ) {
            String message = new  String(pull.recv());
            System.out.println("Received...."+message);
            number++;

            if  (number%1000  == 0 ) {
                System.out.println(number);

            }

        }

    }
}
