package lab7;

public class Commands {
    public static String setConnectCommand(int start, int end){
        return "Connect" + start + "end";
    }
    public static enum CommandType {
        GET,
        SET,
        CONNECT;
    }
}
