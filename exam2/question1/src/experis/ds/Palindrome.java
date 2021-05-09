package experis.ds;

import java.util.List;

public class Palindrome {
    public Boolean isPalindrome(BigNumber bigNumber){
        if(bigNumber == null){
            return false;
        }

        List<Integer> number = bigNumber.getList();
        return check(number, 0, number.size() - 1);
    }

    private Boolean check(List<Integer> number, int start, int end){
        if(start >= end){
            return true;
        }
        if(!number.get(start).equals(number.get(end))){
            return false;
        }

        return check(number, ++start, --end);
    }
}
