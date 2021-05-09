package experis.ds;

public class Multiply {
    public BigNumber multiplyBigNumbers(BigNumber a, BigNumber b){
        if(a == null || b == null){
            return null;
        }

        int sum = a.getNumber() * b.getNumber();
        return new BigNumber(Integer.toString(sum));
    }
}
