package experis.ds.particpants;

import java.util.Arrays;
import java.util.List;

public class Moderator {
    private final List<String> curses = Arrays.asList("C", "C++", "python", "static", "switch");
    private int numOfCurses = 0;

    public String getWithoutCurses(String msg){
        String newMessage = msg;
        for(String curse: curses){
            newMessage = newMessage.replaceAll(curse, getCensor(curse));
        }
        if(!msg.equals(newMessage)){
            ++numOfCurses;
        }
        return newMessage;
    }

    public boolean shouldBeBanned(int limit){
        return numOfCurses >= limit;
    }

    private String getCensor(String txt){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < txt.length(); i++){
            sb.append("*");
        }
        return sb.toString();
    }
}
