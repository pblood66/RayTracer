public class Color extends Vec3 {
    Color(double r, double g, double b) {
        super (r, g, b);
    }

    public double r() { return e[0]; }
    public double g() { return e[1]; }
    public double b() { return e[2]; }

    @Override
    public String toString() {
        return "Color(" + r() + ", " + g() + ", " + b() + ")";
    }

    @Override
    protected Vec3 create(double x, double y, double z) {
        return new Color(x, y, z);
    }

    String writeColor() {
        int rbyte = (int) (255.999 * e[0]);
        int gbyte = (int) (255.999 * e[1]);
        int bbyte = (int) (255.999 * e[2]);

        return rbyte + " " + gbyte + " " + bbyte + "\n";
    }

}
