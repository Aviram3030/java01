package experis.ds.tests;

public class Box {
    private final int val;
    private final int index;

    public Box(int val, int index){
        this.val = val;
        this.index = index;
    }

    public int getVal(){
        return val;
    }

    public int getIndex(){
        return index;
    }
}
