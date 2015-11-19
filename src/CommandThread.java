import java.io.IOException;

/**
 * Created by Pavel on 19.11.2015.
 */
public class CommandThread extends Thread implements Runnable {
    Command lastcommand;
    boolean stop;
    Connection con;
    public CommandThread(Connection connect){
        con=connect;
    }

    public Command getLastcommand(){
        return lastcommand;
    }

    public boolean isDisconnected(){
        return stop;
    }

    public void start(){
        run();
    }

    public void stopped(){
        stop=true;
    }

    public void run(){
        try {
            Command cmd =con.receive();
            if(cmd!=null){
                lastcommand=cmd;
            }
        } catch (IOException e) {
            stop=true;
        }
        while(!stop);
    }



}
