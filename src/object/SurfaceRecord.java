package object;

import utils.vector.Ray;
import utils.vector.Vec3;

public class SurfaceRecord {
    public SurfaceRecord() {
        p = new Vec3();
        normal = new Vec3();
    }

    public Vec3 p;
    public Vec3 normal;
    public double t;
    public boolean frontFace;

    public void setFaceNormal(Ray r, Vec3 outwardNormal) {
        frontFace = Vec3.dot(r.direction(), outwardNormal) < 0;
        normal = frontFace ? outwardNormal : outwardNormal.negate();
    }

    public void setValues(Vec3 p, Vec3 normal, double t,  boolean frontFace) {
        this.p = p;
        this.normal = normal;
        this.t = t;
        this.frontFace = frontFace;
    }

    public void setValues(SurfaceRecord rec) {
        this.p = rec.p;
        this.normal = rec.normal;
        this.t = rec.t;
        this.frontFace = rec.frontFace;
    }
}
