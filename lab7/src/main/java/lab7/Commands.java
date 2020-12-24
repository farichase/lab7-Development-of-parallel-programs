package lab7;


import javafx.util.Pair;

public class Commands {
    private static String GET = "get";
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
    }
    public enum CommandType {
        GET,
        SET,
        NOTIFY
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
