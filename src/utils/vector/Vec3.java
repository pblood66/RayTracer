package utils.vector;

import utils.Random;

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

    public static Vec3 random() {
        return new Vec3(Random.randomDouble(), Random.randomDouble(), Random.randomDouble());
    }

    public static Vec3 random(double min, double max) {
        return new Vec3(Random.randomDouble(min, max), Random.randomDouble(min, max), Random.randomDouble(min, max));
    }

    public static Vec3 randomUnitVector() {
        while (true) {
            var p = random(-1, 1);
            var lensq = p.lengthSquared();
            if (1e-160 < lensq && lensq <= 1) {
                return p.divide(Math.sqrt(lensq));
            }
        }
    }

    public static Vec3 randomOnHemisphere(Vec3 normal) {
        Vec3 onUnitSphere = random();
        if (dot(onUnitSphere, normal) > 0.0) {
            return onUnitSphere;
        } else {
            return onUnitSphere.negate();
        }
    }

    public static Vec3 reflect(Vec3 v, Vec3 normal) {
        // v - 2 * dot(v,normal) * n
        return v.subtract(normal.multiply(2 * dot(v, normal)));
    }

    public static Vec3 refract(Vec3 uv, Vec3 normal, double etaiOverEtat) {
        var cosTheta = Math.min(dot(uv.negate(), normal), 1.0);
        Vec3 rOutPerp = (uv.add(normal.multiply(cosTheta))).multiply(etaiOverEtat);

        // -sqrt(abs(1.0 - rOutPerp.lengthSquared())) * normal
        Vec3 rOutParallel = normal.multiply(-Math.sqrt(Math.abs(1.0 - rOutPerp.lengthSquared())));
        return rOutPerp.add(rOutParallel);
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

    public boolean nearZero() {
        var s = 1e-8;
        return (Math.abs(e[0]) < s) && (Math.abs(e[1]) < s) && (Math.abs(e[2]) < s);
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

    public static Vec3 cross(Vec3 v1, Vec3 v2) {
        return new Vec3(v1.e[1] * v2.e[2] - v1.e[2] * v2.e[1],
                v1.e[2] * v2.e[0] - v1.e[0] * v2.e[2],
                v1.e[0] * v2.e[1] - v1.e[1] * v2.e[0]);
    }

    public Vec3 normalize() {
        return this.divide(this.length());
    }

    public static Vec3 unitVector(Vec3 v) {
        return v.divide(v.length());
    }

    public static Vec3 randomInUnitDisk() {
        while (true) {
            var p = new Vec3(Random.randomDouble(-1, 1), Random.randomDouble(-1, 1), 0);
            if (p.lengthSquared() < 1) {
                return p;
            }
        }
    }

}
