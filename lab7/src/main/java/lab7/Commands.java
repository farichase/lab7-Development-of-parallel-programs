package lab7;


import javafx.util.Pair;

public class Commands {
    private static final String GET = "get";
    private static final String CONNECT = "connect";
    private static final String RESPONSE = "response";
    private static final String PUT = "put";
    private static final String NOTIFY = "notify";
    private static final String EXIT = "exit";
    public static String setConnectCommand(int start, int end){
        return "CONNECT" + start + " " + end;
    }
    public static String setResponseCommand(String response){
        return "RESPONSE: " + response;
    }
    public static String setNotifyCommand(){
        return "NOTIFY";
    }
    public static Commands.CommandType getCommandType(String com){
        if (com.toLowerCase().contains(GET)) {
            return CommandType.GET;
        }
        if (com.toLowerCase().contains(PUT)){
            return CommandType.PUT;
        }
        if (com.toLowerCase().contains(NOTIFY)){
            return CommandType.NOTIFY;
        }
        if (com.toLowerCase().contains(CONNECT)){
            return CommandType.CONNECT;
        }
        if (com.toLowerCase().contains(RESPONSE)){
            return CommandType.RESPONSE;
        }
        if (com.toLowerCase().contains(EXIT)){
            return CommandType.EXIT;
        }
        return CommandType.INVALID;
    }
    public enum CommandType {
        GET,
        PUT,
        NOTIFY,
        CONNECT,
        RESPONSE,
        INVALID,
        EXIT
    }
    public static String[] splitCommand(String com){
        return com.split(" ");
    }
    public static Integer getKey(String com){
        String[] comParts = splitCommand(com);
        return Integer.parseInt(comParts[1]);
    }
    public static Pair<Integer, Integer> getKeyValue(String com){
        String[] comParts = splitCommand(com);
        return new Pair(Integer.parseInt(comParts[2]), Integer.parseInt(comParts[3]));
    }
}
