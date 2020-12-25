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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ProxyServer {
    public static final String ADDR1 = "tcp://localhost:8080";
    public static final String ADDR2 = "tcp://localhost:9000";
    public static final String SPACE = " ";
    public static List<Info> store;
    public static Socket clientSocket;
    public static Socket storageSocket;
    private static final long TIMEOUT = 5000;
    private static final int CLIENT_SOCKET_NUMBER = 0;
    private static final int STORAGE_SOCKET_NUMBER = 1;

    private static boolean sendGetReq(Integer key){
        Iterator iterator = store.iterator();
        Info info;
        do {
            if (iterator.hasNext()) {
                info = (Info)iterator.next();
            } else {
                return false;
            }
        } while (info.getStart() > key || key > info.getEnd());
        info.getAddress().send(storageSocket, 130);
        return true;
    }
    private static void updateHeartBeat(String id){
        Iterator iterator = store.iterator();
        while(iterator.hasNext()){
            Info info = (Info) iterator.next();
            if (info.getId().equals(id)){
                info.setHeartBeat(System.currentTimeMillis());
            }
        }
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
        long time = System.currentTimeMillis();
        while (items.poll(TIMEOUT) != -1){
            if (System.currentTimeMillis() - time >= TIMEOUT){
                Collections.shuffle(store);
                time = System.currentTimeMillis();
            }
            ZMsg msg;
            if (items.pollin(CLIENT_SOCKET_NUMBER)){
                msg = ZMsg.recvMsg(clientSocket);
                String com = new String(msg.getLast().getData(), ZMQ.CHARSET);
                Commands.CommandType type = Commands.getCommandType(com);
                int key;
                boolean isKeyValid;
                if (type == Commands.CommandType.GET){
                    key = Commands.getKey(com);
                    isKeyValid = sendGetReq(key);
                    if (!isKeyValid){
                        msg.getLast().reset(Commands.setResponseCommand("Out of array"));
                        msg.send(clientSocket);
                    }

                }
                if (type == Commands.CommandType.PUT) {
                    key = Commands.getKey(com);
                    isKeyValid = sendGetReq(key);
                    if (!isKeyValid){
                        msg.getLast().reset(Commands.setResponseCommand("Out of array"));
                    } else {
                        msg.getLast().reset(Commands.setResponseCommand("Well done"));
                    }
                    ZMsg responseMsg = new ZMsg();
                    responseMsg.send(clientSocket);
                }
            }
            if (items.pollin(STORAGE_SOCKET_NUMBER)){
                msg = ZMsg.recvMsg(storageSocket);
                ZFrame addr = msg.unwrap();
                String message = new String(addr.getData(), ZMQ.CHARSET);
                String[] split = message.split(SPACE);
                String id = split[1];
                String com = new String(msg.getLast().getData(), ZMQ.CHARSET);
                Commands.CommandType type = Commands.getCommandType(com);
                if (type == Commands.CommandType.CONNECT){
                    Pair<Integer, Integer> range = Commands.getKeyValue(com);
                    store.add(new Info(id, addr, range.getKey(), range.getValue(), System.currentTimeMillis()));
                }
                if (type == Commands.CommandType.NOTIFY){
                    updateHeartBeat(id);
                }
                if (type == Commands.CommandType.RESPONSE){
                    msg.send(clientSocket);
                }


            }
        }

    }
}
