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
}
