package experis.ds;

public class Sum {
    public BigNumber addBigNumbers(BigNumber a, BigNumber b){
        if(a == null || b == null){
            return null;
        }

        int sum = a.getNumber() + b.getNumber();
        return new BigNumber(Integer.toString(sum));
    }

}
