import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Caller {
    private String localNick;
    private String remoteNick;
    SocketAddress remoteAdress;
    private int port=28411;
    private String ip;
    private CallStatus status;
    private Socket socket;

    public void socketclose() throws IOException {
        socket.close();
    }

    public Caller(){
        this.localNick="unnamed";
    }

    public Caller(String localNick){
        this.localNick=localNick;
    }

    public Caller(String localNick,SocketAddress remoteAdress){
        this.localNick=localNick;
        this.remoteAdress=remoteAdress;
    }

    public Caller(String localNick, String ip){
        this.localNick=localNick;
        this.remoteAdress=new InetSocketAddress(ip,port);
    }

    public Connection call(){
        try{
            socket=new Socket(ip,port);
            return new Connection(socket);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public SocketAddress getRemoteAdress() {
        return remoteAdress;
    }

    public void setRemoteAdress(SocketAddress remoteAdress) {
        this.remoteAdress = remoteAdress;
    }

    public String getLocalNick() {
        return localNick;
    }

    public void setLocalNick(String localNick) {
        this.localNick = localNick;
    }

    public String getRemoteNick() {
        return remoteNick;
    }
    public String toString() {
        return "Local nick: " + localNick + ", IP: " + ip + ", remote nick: " + remoteNick + ", remote address: " + remoteAdress;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public static enum CallStatus {
        BUSY, NO_SERVICE, NOT_ACCESSIBLE, OK, REJECTED
    }


}
