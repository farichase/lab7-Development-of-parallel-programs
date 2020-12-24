package lab7;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;


public class Storage {
    private static String ADDR = "tcp://localhost:9000";
    public static void main(String[] args){
        ZContext context = new ZContext();
        ZMQ.Socket dealer = context.createSocket(SocketType.DEALER);
        dealer.connect(ADDR);
        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);
        

    }
}
