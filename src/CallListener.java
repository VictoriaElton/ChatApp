import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.file.LinkOption;

/**
 * Created by Alexandr on 18.11.2015.
 */
public class CallListener {
    public final int port = 5832;
    private String localNick, localIp, remoteNick;
    private boolean busy;
    private SocketAddress remoteAddress;
    private SocketAddress listenAddress;

    public CallListener() throws IOException {
        this.localNick = "Unnamed";
        this.localIp = "127.0.0.1";
    }

    public CallListener(String localNick) throws IOException {
        this.localNick = localNick;
    }

    CallListener(String localNick, String localIp) throws IOException {
        this.localNick = localNick;
        this.localIp = localIp;
    }



    public Connection getConnection() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        //DIALOG
        int reply = JOptionPane.showConfirmDialog(null,"Accept incoming connection?",null,JOptionPane.YES_NO_OPTION);
        if(reply== JOptionPane.NO_OPTION){
            //DISSCONECT
        }
        //END DIALOG

        Connection connection = new Connection(socket);

        if (busy) {
            connection.sendNickBusy("busy ", localNick);
            return null;
        } else {
            setBusy(true);
            connection.sendNickHello("Hello", localNick);
            return connection;
        }

    }

    public SocketAddress getListenAddress() {
        return listenAddress;
    }

    public String getLocalNick() {
        return localNick;
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    String getRemoteNick() {
        return remoteNick;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void setListenAddress(SocketAddress listenAddress) {
        this.listenAddress = listenAddress;
    }

    public void setLocalNick(String localNick) {
        this.localNick = localNick;
    }
}
