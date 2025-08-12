package utils;

public class Random {
    public static double  randomDouble() {
        return Math.random();
    }

    public static double randomDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }

}
