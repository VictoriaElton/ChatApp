/**
 * Created by Pavel on 17.11.2015.
 */
public class Command {
    private final Command.CommandType type;
    static enum CommandType {DISCONNECT, ACCEPT, REJECT, MESSAGE}

    public Command(CommandType t) {
        type = t;
    }

    public static Command getresult(String s) {
        if (s.equals("DISCONNECTED")) {
            return new Command(CommandType.DISCONNECT);
        }
        if (s.equals("ACCEPTED")) {
            return new Command(CommandType.ACCEPT);
        }
        if (s.equals("REJECTED")) {
            return new Command((CommandType.REJECT));
        }
        if (s.equals("MESSAGE")) {
            return new Command((CommandType.MESSAGE));

        }
        return null;

    }
}


