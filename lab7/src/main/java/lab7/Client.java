package lab7;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ.Socket;

public class Client {
    public static void main(String[] args){
        ZContext context = new ZContext();
        Socket socket = context.createSocket(SocketType.REQ);


    }
}
