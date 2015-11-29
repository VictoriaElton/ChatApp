import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Pavel on 12.11.2015.
 */
public class Connection {
    private PrintWriter pw;
    private Scanner scr;
    private Socket sc;

    public Socket getSc() {
        return sc;
    }

    public Scanner getScr() {
        return scr;
    }

    public Connection(Socket soc) throws IOException {
        sc =soc;
        pw = new PrintWriter(sc.getOutputStream());
        scr = new Scanner(new InputStreamReader(sc.getInputStream()));
    }

    public void sendNickHello(String ver,String nick) {
        pw.println("MESSAGE\n");
        pw.flush();
        pw.println(ver+" user "+nick+"\n");
        pw.flush();
    }
    public void sendNickBusy(String ver,String nick){
        pw.println("MESSAGE");
        pw.println(ver+" user "+nick+" busy");
        pw.println("BUSY");
    }
    public void accept(){
        pw.println("Accepted");
        pw.flush();
    }
    public void reject() throws IOException {
        pw.println("MESSAGE");
        pw.println("User rejected you");
        pw.println("REJECTED");
        pw.flush();
        pw.close();
        sc.close();
    }
    public void Disconnect(String nick) throws IOException {
        pw.println("MESSAGE");
        pw.println("User "+nick+"Was disconnected");
        pw.println("DISCONNECTED");
        pw.flush();
        pw.close();
        sc.close();
    }

   public Command receive() throws IOException {
            return Command.getresult(scr.nextLine());
   }
    public void sendMsg(String msg){
        System.out.println(msg);
        pw.flush();
        pw.println(msg);
        pw.flush();
    }

}
