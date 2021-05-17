package order;

import entity.Track;

public class PriceCalculator{
    private double price = 0;

    public void calculate(Track track){
        price += track.getUnitPrice();
    }

    public double getPrice(){
        return price;
    }

    public void nullify(){
        price = 0;
    }
}
