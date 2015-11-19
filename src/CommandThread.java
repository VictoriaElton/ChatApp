import java.io.IOException;
import java.util.Observable;

/**
 * Created by Pavel on 19.11.2015.
 */
public class CommandThread extends Observable implements Runnable {
    Command lastcommand;
    boolean stop;
    Connection con;
    public CommandThread(){
    }

    public void setCon(Connection con) {
        this.con = con;
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
        do {
            try {
                Command cmd = con.receive();
                if (cmd != null) {
                    lastcommand = cmd;
                    notifyObservers();
                }
            } catch (IOException e) {
                stop = true;
            }
        }
        while(!stop);
    }



}
