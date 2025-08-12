package object;

import utils.Interval;
import utils.vector.Ray;
import utils.vector.Vec3;

public class Sphere implements Surface {
    public Sphere(Vec3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean hit(Ray r, Interval rayT, SurfaceRecord rec) {
        Vec3 oc = center.subtract(r.origin());
        var a = r.direction().lengthSquared();
        var h = Vec3.dot(r.direction(), oc);
        var c  = oc.lengthSquared() - radius * radius;

        var discriminant = h * h - a * c;
        if (discriminant < 0) {
            return false;
        }

        var sqrt = Math.sqrt(discriminant);

        var root = (h - sqrt) / a;
        if (!rayT.surrounds(root)) {
            root = (h + sqrt) / a;
            if (!rayT.surrounds(root)) {
                return false;
            }
        }

        rec.t = root;
        rec.p = r.at(rec.t);
        rec.normal = (rec.p.subtract(center)).divide(radius);
        Vec3 outwardNormal = (rec.p.subtract(center)).divide(radius);
        rec.setFaceNormal(r, outwardNormal);

        return true;
    }

    private Vec3 center;
    private double radius;

}
