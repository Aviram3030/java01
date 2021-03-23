package experis.ds;

class DisplayWithColor implements IDisplayWithColor{
    private int color;

    public DisplayWithColor(int color){
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void printChar(int n) {
        System.out.println(makeColor()+" "+(char)n);
    }

    @Override
    public void printValue(int n) {
        System.out.println(makeColor()+" "+(char)n);
    }

    private String makeColor(){
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[");
        sb.append(String.valueOf(30+this.color));
        sb.append("m");
        return sb.toString();
    }
}
