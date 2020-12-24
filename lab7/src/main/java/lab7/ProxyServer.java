package lab7;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMQ.Poller;
import org.zeromq.ZMsg;
import zmq.ZMQ;

public class ProxyServer {
    public static String ADDR1 = "tcp://localhost:8080";
    public static String ADDR2 = "tcp://localhost:9000";
    public static void main(String[] args){
        ZContext context = new ZContext();
        Socket clientSocket = context.createSocket(SocketType.ROUTER);
        Socket storageSocket = context.createSocket(SocketType.ROUTER);
        clientSocket.bind(ADDR1);
        clientSocket.bind(ADDR2);
        Poller items = context.createPoller(2);
        items.register(clientSocket, 1);
        items.register(storageSocket, 1);

        for (; !Thread.currentThread().isInterrupted(); ){
            items.poll(5000);
            ZMsg msg;
            if (items.pollin(0)){
                msg = ZMsg.recvMsg(clientSocket);
                String com = new String(msg.getLast().getData(), ZMQ.CHARSET);
                Commands.CommandType type = Commands.getCommandType(com);
                Integer key;
                if (type == Commands.CommandType.GET){
                    key = Commands.getKey(com);
                    msg.getLast().reset(Commands.setResponseCommand());
                    msg.send(clientSocket);
                }
                if (type == Commands.CommandType.SET) {
                    
                }
            }
        }

    }
}
