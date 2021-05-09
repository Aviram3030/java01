package experis.ds;

import java.util.ArrayList;
import java.util.List;

public class BigNumber {
    private final List<Integer> list;
    private final boolean positive;

    public BigNumber(List<Integer> list, boolean positive){
        this.list = list;
        this.positive = positive;
    }

    public BigNumber(String word){
        list = new ArrayList<>();
        int i = 0;
        if(word.charAt(0) == '-'){
            positive = false;
            i++;
        }
        else{
            positive = true;
        }
        for(;i < word.length(); i++){
            char c = word.charAt(i);
            int digit = c - '0';
            list.add(digit);
        }
    }

    public String getString(){
        int i = 0;
        StringBuilder sb = new StringBuilder();
        if(!positive){
            sb.append('-');
        }

        for(;i < list.size(); i++){
            int digit = list.get(i);
            char c = Character.forDigit(digit, 10);
            sb.append(c);
        }

        return sb.toString();
    }

    public int getValue(){
        int value = list.size() - 1;
        int previousValue = 0;
        int multiplyByTen = 10;
        for(int i = list.size() - 2; i > 0; i--){
            value = list.get(i) * multiplyByTen;
            multiplyByTen *= 10;
            if(previousValue > value){
                value = Integer.MAX_VALUE;
                break;
            }
            previousValue = value;
        }

        if(!positive){
            if(value == Integer.MAX_VALUE){
                value = Integer.MIN_VALUE;
            }
            else {
                value = value * -1;
            }
        }

        return value;
    }

    public BigNumber add(BigNumber first, BigNumber second){
        List<Integer> result = new ArrayList<>();
        List<Integer> a = first.getList();
        List<Integer> b = second.getList();
        int factor = 0;
        boolean didSwap = false;

        if(a.size() < b.size()){
            swap(a,b);
            didSwap = true;
        }

        int i = a.size() - 1;
        int j = b.size() - 1;
        while(i >= 0){
            int firstDigit = a.get(i);
            int secondDigit = 0;
            if(j > 0) {
                secondDigit = a.get(j);
            }

            int sum = firstDigit + secondDigit + factor;
            if(sum > 0){
                factor = 1;
            }
            result.add(result.size(), sum);

            i--;
            j--;
        }
        boolean isPositive = true;
        if((didSwap && second.isPositive()) || (!didSwap && first.isPositive())){
            isPositive = false;
        }
        return new BigNumber(result, isPositive);
    }

    private void swap(List<Integer> a, List<Integer> b){
        List<Integer> temp = a;
        a = b;
        b = temp;
    }

    public List<Integer> getList(){
        return list;
    }

    public Boolean isPositive(){
        return positive;
    }

    public Boolean isPalindrome(){
        return check(0, list.size() - 1);
    }

    private Boolean check(int start, int end){
        if(start >= end){
            return true;
        }
        if(!list.get(start).equals(list.get(end))){
            return false;
        }

        return check(++start, --end);
    }



}
