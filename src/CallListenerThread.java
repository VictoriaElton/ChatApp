import java.io.IOException;
import java.net.SocketAddress;
import java.util.Observable;
import java.util.Scanner;

/**
 * Created by Alexandr on 19.11.2015.
 */
public class CallListenerThread extends Observable implements Runnable {
private CallListener callListener;
private Caller.CallStatus callStatus;
    private String text;
    private Scanner sc;

    private boolean busy=false, startORstop;
    private Connection connect;
    private Caller caller;

    public CallListenerThread()throws IOException {
        callListener = new CallListener();
    }

    public CallListenerThread(String localNick)throws IOException{
        callListener = new CallListener(localNick);
    }

    public CallListenerThread(String localNick, String localIp)throws IOException{
        callListener = new CallListener(localNick,localIp);
    }


    public SocketAddress getListenAddress(){
        return callListener.getListenAddress();
    }

    public String getLocalNick(){
        return callListener.getLocalNick();
    }

    public SocketAddress getRemoteAddress(){
        return callListener.getRemoteAddress();
    }

    public String getRemoteNick(){
       return callListener.getRemoteNick();
    }

    public boolean isBusy() {
        return callListener.isBusy();

    }

    public void run() {
        while (true){
            try {
                if(!busy) {
                    connect = callListener.getConnection();
                    sc=connect.getScr();
                    busy=true;
                }
                else{
                    text=sc.nextLine();
                    setChanged();
                    notifyObservers(new String (text));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void setBusy(boolean busy){
        this.busy = busy;
    }

    public void setListenAddress(SocketAddress listenAddress){
        callListener.setListenAddress(listenAddress);
    }

    public void setLocalNick(String localNick){
       callListener.setLocalNick(localNick);
    }

    public void start(){
        this.startORstop = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        this.startORstop = false;
    }



}
