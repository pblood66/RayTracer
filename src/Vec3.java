import java.util.Vector;

public class Vec3 {
    protected double[] e = new double[3];
    public Vec3() {
        e = new double[]{0, 0, 0};
    }
    public Vec3(double x, double y, double z) {
        e[0] = x;
        e[1] = y;
        e[2] = z;
    }

    public double x()  {
        return e[0];
    }

    public double y()  {
        return e[1];
    }

    public double z()  {
        return e[2];
    }

    protected Vec3 create(double x, double y, double z) {
        return new Vec3(x, y, z);
    }

    public Vec3 negate() {
        return create(-e[0], -e[1], -e[2]);
    }

    public double at(int i) {
        return e[i];
    }

    public Vec3 add(Vec3 v) {
        return create(e[0] + v.e[0], e[1] + v.e[1], e[2] + v.e[2]);
    }

    public Vec3 add(double t) {
        return create(e[0] + t, e[1] + t, e[2] + t);
    }

    public Vec3 subtract(Vec3 v) {
        return create(e[0] - v.e[0], e[1] - v.e[1], e[2] - v.e[2]);
    }

    public Vec3 subtract(double t) {
        return create(e[0] - t, e[1] - t, e[2] - t);
    }

    public Vec3 multiply(Vec3 v) {
        return create(e[0] * v.e[0], e[1] * v.e[1], e[2] * v.e[2]);
    }

    public Vec3 multiply(double t) {
        return create(e[0] * t, e[1] * t, e[2] * t);
    }

    public Vec3 divide(Vec3 v) {
        return create(e[0] / v.e[0], e[1] / v.e[1], e[2] / v.e[2]);
    }

    public Vec3 divide(double t) {
        return create(e[0] / t, e[1] / t, e[2] / t);
    }

    public double lengthSquared() {
        return e[0] * e[0] + e[1] * e[1] + e[2] * e[2];
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public String toString() {
        return e[0] + " " + e[1] + " " + e[2];
    }

    public double dot(Vec3 v) {
        return e[0] * v.e[0] + e[1] * v.e[1] + e[2] * v.e[2];
    }

    public static double dot(Vec3 v1, Vec3 v2) {
        return v1.e[0] * v2.e[0] + v1.e[1] * v2.e[1] + v1.e[2] * v2.e[2];
    }

    public Vec3 cross(Vec3 v) {
        return create(e[1] * v.e[2] - e[2] * v.e[1],
                e[2] * v.e[0] - e[0] * v.e[2],
                e[0] * v.e[1] - e[1] * v.e[0]);
    }

    public Vec3 normalize() {
        return this.divide(this.length());
    }

    public static Vec3 unitVector(Vec3 v) {
        return v.divide(v.length());
    }

}
