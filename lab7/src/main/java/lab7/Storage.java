package lab7;

import javafx.util.Pair;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Storage {
    private static final String ADDR = "tcp://localhost:9000";
    private static void sendConnectCommand(ZMQ.Socket dealer, int start, int end){
        dealer.send(Commands.setConnectCommand(start, end), 0);
    }
    private static void sendNotifyCommand(ZMQ.Socket dealer){
        dealer.send(Commands.setNotifyCommand(), 0);
    }
    public static void main(String[] args){
        Map<Integer, Integer> storage = new HashMap<>();
        ZContext context = new ZContext();
        ZMQ.Socket dealer = context.createSocket(SocketType.DEALER);
        dealer.connect(ADDR);
        Scanner in = new Scanner(System.in);
        int start = in.nextInt();
        int end = in.nextInt();
        long heartBeat = System.currentTimeMillis() + 5000;
        sendConnectCommand(dealer, start, end);
        while(!Thread.currentThread().isInterrupted()){
            ZMsg msg = ZMsg.recvMsg(dealer, false);
            if (msg != null) {
                String com = new String(msg.getLast().getData(), ZMQ.CHARSET);
                Commands.CommandType type = Commands.getCommandType(com);
                if (type == Commands.CommandType.GET) {
                    Integer key = Commands.getKey(com);
                    Integer value = storage.get(key);
                    String response;
                    if (value == null){
                        response = "null";
                    } else {
                        response = Integer.toString(value);
                    }
                    msg.getLast().reset(Commands.setResponseCommand(response));
                    msg.send(dealer);
                }
                if (type == Commands.CommandType.PUT){
                    Pair<Integer, Integer> setPar = Commands.getKeyValue(com);
                    storage.put(setPar.getKey(), setPar.getValue());
                    msg.destroy();
                }
            }
        }
        if (System.currentTimeMillis() >= heartBeat){
            heartBeat = System.currentTimeMillis() + 5000;
            sendNotifyCommand(dealer);
        }
        context.destroySocket(dealer);
        context.destroy();
    }
}
