package experis.ds;

import java.util.ArrayList;
import java.util.List;

public class BigNumber {
    private final List<Integer> list;
    private final boolean positive;

    public BigNumber(List<Integer> list, boolean positive) {
        this.list = list;
        this.positive = positive;
    }

    public BigNumber(String word) {
        list = new ArrayList<>();
        int i = 0;
        if (word.charAt(0) == '-') {
            positive = false;
            i++;
        } else {
            positive = true;
        }
        for (; i < word.length(); i++) {
            char c = word.charAt(i);
            int digit = c - '0';
            list.add(digit);
        }
    }

    public String getString() {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        if (!positive) {
            sb.append('-');
        }

        for (; i < list.size(); i++) {
            int digit = list.get(i);
            char c = Character.forDigit(digit, 10);
            sb.append(c);
        }

        return sb.toString();
    }

    public int getValue() {
        int value = list.get(list.size() - 1);
        int previousValue = 0;
        int multiplyByTen = 10;
        for (int i = list.size() - 2; i >= 0; i--) {
            value += list.get(i) * multiplyByTen;
            multiplyByTen *= 10;
            if (previousValue > value) {
                value = Integer.MAX_VALUE;
                break;
            }
            previousValue = value;
        }

        if (!positive) {
            if (value == Integer.MAX_VALUE) {
                value = Integer.MIN_VALUE;
            } else {
                value = value * -1;
            }
        }

        return value;
    }

    public static BigNumber add(BigNumber first, BigNumber second) {
        List<Integer> a = first.getList();
        List<Integer> b = second.getList();
        boolean didSwap = false;

        if (a.size() < b.size()) {
            List<Integer> temp = a;
            a = b;
            b = temp;
            didSwap = true;
        }

        boolean isPositive = true;
        if ((didSwap && !second.isPositive()) || (!didSwap && !first.isPositive())) {
            isPositive = false;
        }

        List<Integer> result = addNumbers(a, b);
        return new BigNumber(result, isPositive);
    }

    private static List<Integer> addNumbers(List<Integer> a, List<Integer> b) {
        if (a.size() < b.size()) {
            List<Integer> temp = a;
            a = b;
            b = temp;
        }

        List<Integer> result = new ArrayList<>();
        int factor = 0;
        int i = a.size() - 1;
        int j = b.size() - 1;

        if (a.isEmpty()) {
            return new ArrayList<>(b);
        }
        if (b.isEmpty()) {
            return new ArrayList<>(a);
        }

        while (i >= 0) {
            int firstDigit = a.get(i);
            int secondDigit = 0;
            if (j >= 0) {
                secondDigit = b.get(j);
            }

            int sum = firstDigit + secondDigit + factor;
            factor = sum / 10;
            sum = sum % 10;

            result.add(0, sum);

            i--;
            j--;
        }

        if (factor == 1) {
            result.add(1);
        }

        return result;
    }


    public static BigNumber multiply(BigNumber first, BigNumber second) {
        List<Integer> finalResultList = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        BigNumber finalResult;
        List<Integer> a = first.getList();
        List<Integer> b = second.getList();

        int factor = 0;

        boolean isPositive = true;
        if ((!second.isPositive() && first.isPositive()) || (!first.isPositive() && second.isPositive())) {
            isPositive = false;
        }

        int numOfZeroes = 0;
        for (int i = a.size() - 1; i >= 0; i--) {
            factor = 0;
            int firstDigit = a.get(i);
            fillWithZeroes(result, numOfZeroes);
            numOfZeroes++;
            for (int j = b.size() - 1; j >= 0; j--) {
                int secondDigit = b.get(j);

                int sum = firstDigit * secondDigit + factor;
                factor = sum / 10;
                sum = sum % 10;

                result.add(0, sum);
            }
            if (factor != 0) {
                result.add(0, factor);
            }

            finalResultList = addNumbers(finalResultList, result);
            result.clear();
        }

        finalResult = new BigNumber(finalResultList, isPositive);
        if (factor != 0) {
            result.add(factor);
        }

        return finalResult;
    }

    private static void fillWithZeroes(List<Integer> result, int numOfZeroes) {
        for (int i = 0; i < numOfZeroes; i++) {
            result.add(0);
        }
    }


    public List<Integer> getList() {
        return list;
    }

    public Boolean isPositive() {
        return positive;
    }

    public Boolean isPalindrome() {
        return check(0, list.size() - 1);
    }

    private Boolean check(int start, int end) {
        if (start >= end) {
            return true;
        }
        if (!list.get(start).equals(list.get(end))) {
            return false;
        }

        return check(++start, --end);
    }

}
