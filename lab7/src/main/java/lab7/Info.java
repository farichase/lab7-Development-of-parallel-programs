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
    public void setId(String id){
        this.id = id;
    }
    public void setAddress(ZFrame address){
        this.address = address;
    }
    public void setStart(int start){
        this.start = start;
    }
    public void setEnd(int end){
        this.end = end;
    }
    public void setHeartBeat(long heartBeat){
        this.heartBeat = heartBeat;
    }
    public boolean isDead(){
        return this.heartBeat +
    }
}
