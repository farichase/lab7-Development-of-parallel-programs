package lab7;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMQ.Poller;
public class ProxyServer {
    public static String ADDR1 = "tcp://localhost:8080";
    public static String ADDR2 = "tcp://localhost:9000";
    public static void main(String[] args){
        ZContext context = new ZContext();
        Socket clientSocket = context.createSocket(SocketType.ROUTER);
        Socket storageSocket = context.createSocket(SocketType.ROUTER);
        clientSocket.bind(ADDR1);
        clientSocket.bind(ADDR2);
        ZMQ.Poller items = context.createPoller(2);

        for (; !Thread.currentThread().isInterrupted(); ){

        }

    }
}
