package lab7;

import javafx.util.Pair;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZFrame;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMQ.Poller;
import org.zeromq.ZMsg;
import zmq.ZMQ;

import java.util.ArrayList;
import java.util.List;

public class ProxyServer {
    public static String ADDR1 = "tcp://localhost:8080";
    public static String ADDR2 = "tcp://localhost:9000";
    public static List<Info> store;
    public static Socket clientSocket;
    public static Socket storageSocket;

    private static boolean sendGetReq(){

    }
    public static void main(String[] args){
        store = new ArrayList();
        ZContext context = new ZContext();
        clientSocket = context.createSocket(SocketType.ROUTER);
        storageSocket = context.createSocket(SocketType.ROUTER);
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
                Boolean isKeyValid;
                if (type == Commands.CommandType.GET){
                    key = Commands.getKey(com);
                    isKeyValid =
                    if (!isKeyValid){

                    }
                    msg.getLast().reset(Commands.setResponseCommand());
                    msg.send(clientSocket);
                }
                if (type == Commands.CommandType.SET) {
                    key = Commands.getKey(com);
                }
            }
            if (items.pollin(1)){
                msg = ZMsg.recvMsg(storageSocket);
                ZFrame addr = msg.unwrap();
                String id = new String(addr.getData(), ZMQ.CHARSET);
                String com = new String(msg.getLast().getData(), ZMQ.CHARSET);
                Commands.CommandType type = Commands.getCommandType(com)
                if (type == Commands.CommandType.CONNECT){
                    Pair<Integer, Integer> range = Commands.getKeyValue(com);
                    store.add(new Info(id, addr, range.getKey(), range.getValue(), System.currentTimeMillis()));
                }
                if (type == Commands.CommandType.NOTIFY){

                }

            }
        }

    }
}
