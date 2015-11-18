import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Victoria on 17.11.2015.
 */

public class Caller {
    private String localNick;
    private String friendNick;
    private int port;
    private SocketAddress remoteAddress;
    private String ip;

    public Caller(){
        this.localNick="unnamed";
        this.ip="127.0.0.1";
    }

    public Caller(String localNick){
        this.localNick=localNick;
        this.ip="127.0.0.1";
    }

    public Caller(String localNick, SocketAddress remoteAddress){
        this.localNick=localNick;
        this.remoteAddress=remoteAddress;
        this.ip="127.0.0.1";
    }

    public Caller(String localNick, String ip){
        this.localNick=localNick;
        this.ip=ip;
    }

    public Connection call(){
        try{
            Socket socket=new Socket(ip, 28411);
            return new Connection(socket);
        }
        catch (IOException e){
            return null;
        }
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(SocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getLocalNick() {
        return localNick;
    }

    public void setLocalNick(String localNick) {
        this.localNick = localNick;
    }

    public String getFriendNick() {
        return friendNick;
    }
    public String toString() {
        return "Local nick: " + localNick + ", IP: " + ip + ", remote nick: " + friendNick + ", remote address: " + remoteAddress;
    }
}
