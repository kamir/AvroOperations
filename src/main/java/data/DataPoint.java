package data;

import java.util.Random;

public class DataPoint {

    public Long tsSource = null;
    public Double value = null;

    Random rand = new Random();

    public DataPoint() {
        tsSource = System.currentTimeMillis();
        value = rand.nextDouble();
    }

}