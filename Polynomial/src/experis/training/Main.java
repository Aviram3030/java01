package experis.training;

import java.text.DecimalFormat;

public class Main {


    public static double Polynomial(double... numbers){
        if(isNullOrEmpty(numbers) || numbers.length == 1){
            System.out.println("Error,illegal input");
            return 0;
        }
        if(numbers.length == 2){
            return numbers[0];
        }

        double x = numbers[numbers.length - 1];
        double exponent = 1;
        double sum = 0;

        for(int i = numbers.length - 2; i >= 0; i--){
            sum += numbers[i] * exponent;
            exponent *= x;
        }

        return sum;
    }

    public static void print(double... numbers){
        if(isNullOrEmpty(numbers)){
            return;
        }

        if(numbers.length == 1){
            printConst(numbers[0]);
            return;
        }

        int exponent = numbers.length - 1;
        DecimalFormat format = new DecimalFormat("#.##");

        int j = 0;
        while(j < numbers.length && numbers[j] == 0){
            j++;
            exponent--;
        }

        if(j == numbers.length){
            System.out.println(0);
            return;
        }
        else if(j == numbers.length - 1){
            printConst(numbers[j]);
            return;
        }
        else if(j == numbers.length - 2){
            printLastTwoExponents(numbers[j], numbers[j+1]);
            return;
        }

        if(equalsOne(numbers[j])){
            System.out.print("x^" + exponent);
        }
        else {
            System.out.print(format.format(numbers[j]) + "x^" + exponent);
        }

        exponent--;

        for(int i = j + 1;i < numbers.length - 2; i++){
            if(numbers[i] > 0){
                printPositive(numbers, i, exponent);
            }
            else if(numbers[i] < 0){
                printNegative(numbers, i, exponent);
            }

            --exponent;
        }

        if(numbers[numbers.length - 2] > 0){
            System.out.print("+");
        }

        printLastTwoExponents(numbers[numbers.length - 2], numbers[numbers.length - 1]);


    }

    public static void printConst(double a){
        DecimalFormat format = new DecimalFormat("#.##");
        System.out.println(format.format(a));
    }

    public static Boolean equalsOne(double a){
        return a == 1;
    }

    public static void printLastTwoExponents(double a, double b){
        DecimalFormat format = new DecimalFormat("#.##");

        if(a > 0){
            if(equalsOne(a)){
                System.out.print("x");
            }
            else {
                System.out.print(format.format(a) + "*x");
            }
        }
        else if(a < 0){
            System.out.print(format.format(a) + "*x");
        }

        if(b > 0){
            System.out.println("+" + format.format(b));
        }
        else if(b < 0){
            System.out.println(format.format(b));
        }
    }

    public static void printPositive(double[] numbers, int i, int exponent){
        if(equalsOne(numbers[i])){
            System.out.print("+x^" + exponent);
        }
        else {
            DecimalFormat format = new DecimalFormat("#.##");
            System.out.print("+" + format.format(numbers[i]) + "*x^" + exponent);
        }
    }
    public static void printNegative(double[] numbers, int i, int exponent){
        if(equalsOne(-1 * numbers[i])){
            System.out.print("-x^" + exponent );
        }
        else {
            DecimalFormat format = new DecimalFormat("#.##");
            System.out.print(format.format(numbers[i]) + "*x^" + exponent);
        }
    }

    public static Boolean isNullOrEmpty(double[] numbers){
        return numbers == null || numbers.length == 0;
    }

    public static void main(String[] args) {
        DecimalFormat format = new DecimalFormat("#.##");
        System.out.println(format.format(Polynomial(0, 1, -1, 0, 2 , 1, 5, 2.2)));
        print(0, 1, -1, 0, 2 , 1, 5);

    }
}
