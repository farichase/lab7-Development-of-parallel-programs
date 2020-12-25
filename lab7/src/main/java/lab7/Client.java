package lab7;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ.Socket;

import java.util.Scanner;

public class Client {
    public static final String CLIENT_ADDRESS = "tcp://localhost:8080";
    public static void main(String[] args){
        ZContext context = new ZContext();
        Socket socket = context.createSocket(SocketType.REQ);
        socket.connect(CLIENT_ADDRESS);
        System.out.println("start");
        Scanner in = new Scanner(System.in);

        for(;;){
            String com = in.nextLine();
            Commands.CommandType type  = Commands.getCommandType(com);
            if (type == Commands.CommandType.EXIT) {
                context.destroySocket(socket);
                context.destroy();
                return;
            }
            if (type == Commands.CommandType.INVALID){
                System.out.println("Invalid command");
            } else {
                socket.send(com, 0);
                String answer = socket.recvStr(0);
                System.out.println(answer);
            }
        }


    }
}
