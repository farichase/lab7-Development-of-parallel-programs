package lab7;


import javafx.util.Pair;

public class Commands {
    private static String GET = "get";
    public static String setConnectCommand(int start, int end){
        return "Connect" + start + " " + end;
    }
    public static String setResponseCommand(String response){
        return "Response: " + response;
    }
    public static Commands.CommandType getCommandType(String com){
        if (com.contains(GET)) {
            return CommandType.GET;
        }
    }
    public enum CommandType {
        GET,
        SET
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
