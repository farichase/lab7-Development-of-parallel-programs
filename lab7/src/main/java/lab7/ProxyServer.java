package lab7;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ProxyServer {
    public static void main(String[] args){
        ZContext context = new ZContext();
        ZMQ.Socket clientSocket = context.createSocket(SocketType.ROUTER);
        ZMQ.Socket storageSocket = context.createSocket(SocketType.ROUTER);
    }
}
