import javax.swing.*;
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

    private boolean isclickconnection=false;
    private boolean busy=false, startORstop;
    private Connection connect;
    private Caller caller;
    private JTextField IPText;
    private JButton connectButton;
    private JButton dissconectButton;
    private JTextArea messageText;
    private JButton sendButton;


    public void setIsclickconnection(boolean isclickconnection) {
        this.isclickconnection = isclickconnection;
    }

    public CallListenerThread(JTextField IPText, JButton connectButton, JButton dissconectButton, JTextArea messageText, JButton sendButton)throws IOException {
        callListener = new CallListener();
        this.IPText=IPText;
        this.connectButton=connectButton;
        this.dissconectButton=dissconectButton;
        this.messageText=messageText;
        this.sendButton=sendButton;
    }

    public CallListenerThread(String localNick,JTextField IPText, JButton connectButton, JButton dissconectButton, JTextArea messageText, JButton sendButton)throws IOException{
        callListener = new CallListener(localNick);
        this.IPText=IPText;
        this.connectButton=connectButton;
        this.dissconectButton=dissconectButton;
        this.messageText=messageText;
        this.sendButton=sendButton;
    }

    public CallListenerThread(String localNick, String localIp,JTextField IPText, JButton connectButton, JButton dissconectButton, JTextArea messageText, JButton sendButton)throws IOException{
        callListener = new CallListener(localNick,localIp);
        this.IPText=IPText;
        this.connectButton=connectButton;
        this.dissconectButton=dissconectButton;
        this.messageText=messageText;
        this.sendButton=sendButton;
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

    public void blockButton(){
        this.IPText.setEnabled(false);
        this.connectButton.setEnabled(false);
        this.dissconectButton.setEnabled(true);
        this.messageText.setEnabled(true);
        this.sendButton.setEnabled(true);
    }

    public void run() {
        while (true){
            try {
                if(!busy) {
                    connect = callListener.getConnection();
                    sc=connect.getScr();
                    int reply = JOptionPane.showConfirmDialog(null,"Accept incoming connection?",null,JOptionPane.YES_NO_OPTION);
                 //   if(reply== JOptionPane.NO_OPTION){
                  //      setChanged();
                    //    notifyObservers(new String ("Reject"));
                  //  }
                    busy=true;
                    if(!isclickconnection){
                    blockButton();
                    setChanged();
                    notifyObservers(new Connection(connect.getSc()));}
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
