package utils.vector;

import utils.Interval;
import utils.Random;

public class Color extends Vec3 {
    public Color(double r, double g, double b) {
        super (r, g, b);
    }

    public Color(Vec3 v) {
        super (v.x(),  v.y(), v.z());
    }

    public double r() { return e[0]; }
    public double g() { return e[1]; }
    public double b() { return e[2]; }

    public void setColor(Color color) {
        e[0] = color.r();
        e[1] = color.g();
        e[2] = color.b();
    }

    @Override
    public String toString() {
        return "Vectors.Color(" + r() + ", " + g() + ", " + b() + ")";
    }

    @Override
    protected Vec3 create(double x, double y, double z) {
        return new Color(x, y, z);
    }

    public static Color random() {
        return new Color(Random.randomDouble(), Random.randomDouble(), Random.randomDouble());
    }

    public static Color random(double min, double max) {
        return new Color(Random.randomDouble(min, max), Random.randomDouble(min, max), Random.randomDouble(min, max));
    }

    private double linearToGamma(double linearComp) {
        if (linearComp > 0) {
            return Math.sqrt(linearComp);
        }

        return 0;
    }

    public String writeColor() {
        var r = r();
        var g = g();
        var b = b();

        r = linearToGamma(r);
        g = linearToGamma(g);
        b = linearToGamma(b);

        Interval intensity = new Interval(0.000, 0.999);
        int rbyte = (int) (255.999 * intensity.clamp(r));
        int gbyte = (int) (255.999 * intensity.clamp(g));
        int bbyte = (int) (255.999 * intensity.clamp(b));

        return rbyte + " " + gbyte + " " + bbyte + "\n";
    }

}
