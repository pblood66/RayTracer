public class Interval {
    public Interval() {
        min = Double.NEGATIVE_INFINITY;
        max = Double.POSITIVE_INFINITY;
    }

    public Interval(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double size() {
        return max - min;
    }


    public boolean contains(double x) {
        return min <= x && x <= max;
    }

    public boolean surrounds(double x) {
        return min < x && x < max;
    }

    public double min() { return min; }
    public double max() { return max; }

    public void setMin(double min) { this.min = min; }
    public void setMax(double max) { this.max = max; }

    private double min;
    private double max;
    public static Interval empty = new Interval(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
    public static Interval universe = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
}
