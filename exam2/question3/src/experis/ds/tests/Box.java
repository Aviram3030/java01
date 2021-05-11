package experis.ds.tests;

public class Box {
    private final int val;
    private final int type;

    public Box(int val, int type){
        this.val = val;
        this.type = type;
    }

    public int getVal(){
        return val;
    }

    public int getType(){
        return type;
    }
}