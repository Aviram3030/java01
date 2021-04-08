package experis.ds;

public class Ball implements Comparable<Ball>{
    private final double area;

    public Ball(){
        area = 0;
    }

    public Ball(double area){
        this.area = area;
    }

    public double getArea(){
        return area;
    }

    public int compareTo(Ball ball) {
        if(area > ball.area){
            return 1;
        }
        else{
            return 0;
        }
    }


}
