package lab7;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;


public class Storage {
    private static String ADDR = "tcp://localhost:9000";
    public static void main(String[] args){
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.DEALER);
        socket.connect(ADDR);

    }
}
