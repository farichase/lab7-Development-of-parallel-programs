package lab7;


import javafx.util.Pair;

public class Commands {
    private static String GET = "get";
    private static String CONNECT = "connect";
    private static String RESPONSE = "response";
    private static String SET = "set";
    private static String NOTIFY = "notify";
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
        if (com.toLowerCase().contains(SET)){
            return CommandType.SET;
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
        return CommandType.INVALID;
    }
    public enum CommandType {
        GET,
        SET,
        NOTIFY,
        CONNECT,
        RESPONSE,
        INVALID
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
        return new Pair(Integer.parseInt(comParts[1]), Integer.parseInt(comParts[2]));
    }
}
