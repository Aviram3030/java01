package experis.ds.commands;
import java.util.concurrent.ConcurrentHashMap;

public class Transformation implements ITransformation{
    private final ConcurrentHashMap<CommandType,Func> transformer = new ConcurrentHashMap<>();

    public Transformation(){
        Func getWithoutFirstWord = (txt) -> {int pos = txt.indexOf(' ');
            if(pos == -1){
                return null;
            }
            return txt.substring(pos + 1);
        };
        Func doNothing = (txt) -> txt;

        transformer.put(CommandType.QUIT, doNothing);
        transformer.put(CommandType.ENTER_ROOM, getWithoutFirstWord);
        transformer.put(CommandType.LEAVE_ROOM, doNothing);
        transformer.put(CommandType.ROOMS_LIST, getWithoutFirstWord);
        transformer.put(CommandType.NICK, getWithoutFirstWord);
        transformer.put(CommandType.MESSAGE_USER, getWithoutFirstWord);
        transformer.put(CommandType.REGULAR_MESSAGE, doNothing);
        transformer.put(CommandType.BAN, getWithoutFirstWord);
        transformer.put(CommandType.SHUT_DOWN, doNothing);
    }

    @Override
    public String transform(String msg, CommandType type){
        Func func = transformer.get(type);
        return func.apply(msg);
    }

}
