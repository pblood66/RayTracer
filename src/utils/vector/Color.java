package utils.vector;

import utils.Interval;

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

    @Override
    public String toString() {
        return "Vectors.Color(" + r() + ", " + g() + ", " + b() + ")";
    }

    @Override
    protected Vec3 create(double x, double y, double z) {
        return new Color(x, y, z);
    }

    public String writeColor() {
        Interval intensity = new Interval(0.000, 0.999);
        int rbyte = (int) (255.999 * intensity.clamp(r()));
        int gbyte = (int) (255.999 * intensity.clamp(g()));
        int bbyte = (int) (255.999 * intensity.clamp(b()));

        return rbyte + " " + gbyte + " " + bbyte + "\n";
    }

}
