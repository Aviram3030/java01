package experis.ds;

import java.util.ArrayList;
import java.util.List;

public class BigNumber {
    private final List<Integer> list = new ArrayList<>();
    private final String word;
    private final int number;

    public BigNumber(String word){
        this.word = word;
        number = Integer.parseInt(word);
        calculateList(number);
    }

    public void calculateList(int digits){
        while(digits != 0){
            int digit = digits % 10;
            list.add(list.size(), digit);
            digits = digits / 10;
        }
    }

    public String getWord(){
        return word;
    }

    public int getNumber(){
        return number;
    }

    public List<Integer> getList(){
        return list;
    }

}
