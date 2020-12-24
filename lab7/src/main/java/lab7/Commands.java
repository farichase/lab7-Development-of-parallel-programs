package lab7;


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
    public static enum CommandType {
        GET,
        SET,
        CONNECT;
    }
    public static Integer getKey(String com){
        String[] comParts = com.split(" ");
        return Integer.parseInt(comParts[1]);
    }
}
