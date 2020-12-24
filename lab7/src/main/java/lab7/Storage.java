package lab7;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;


public class Storage {
    private static String ADDR = "tcp://localhost:9000";
    private static void sendConnectCommand(ZMQ.Socket dealer, int start, int end){
        dealer.send(Commands.setConnectCommand(start, end), 0);
    }
    public static void main(String[] args){
        ZContext context = new ZContext();
        ZMQ.Socket dealer = context.createSocket(SocketType.DEALER);
        dealer.connect(ADDR);
        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);
        sendConnectCommand(dealer, start, end);
        while(!Thread.currentThread().isInterrupted()){
            ZMsg msg = ZMsg.recvMsg(dealer, false);
            if (msg != null) {
                String com = new String(msg.getLast().getData(), ZMQ.CHARSET);
                
            }
        }
    }
}
