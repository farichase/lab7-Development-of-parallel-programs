package lab7;

import org.zeromq.ZFrame;

public class Info {
    private String id;
    private ZFrame address;
    private int start;
    private int end;
    private long heartBeat;
    public Info(String id, ZFrame address, int start, int end, long heartBeat){
        this.id = id;
        this.address = address;
        this.start = start;
        this.end = end;
        this.heartBeat = heartBeat;
    }
    public String getId(){
        return this.id;
    }
    public ZFrame getAddress(){
        return this.address;
    }
    public int getStart(){
        return this.start;
    }
    public int getEnd(){
        return this.end;
    }
    public long getHeartBeat(){
        return this.heartBeat;
    }
}
