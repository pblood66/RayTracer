package materials;

import object.SurfaceRecord;
import utils.Random;
import utils.vector.Color;
import utils.vector.Ray;
import utils.vector.Vec3;

public class Dielectric implements Material {
    public Dielectric(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    @Override
    public boolean scatter(Ray rayIn, SurfaceRecord rec, Color attenuation, Ray scattered) {
        attenuation.setColor(new Color(1.0, 1.0, 1.0));
        double  ri = rec.frontFace ? (1.0 / refractionIndex) : refractionIndex;

        Vec3 unitDirection = Vec3.unitVector(rayIn.direction());
        double cosTheta = Math.min(Vec3.dot(unitDirection.negate(), rec.normal), 1.0);
        double sinTheta = Math.sqrt(1.0 - cosTheta * cosTheta);

        boolean cannotRefract = ri * sinTheta > 1.0;
        Vec3 direction;
        if (cannotRefract || reflectance(cosTheta, ri) > Random.randomDouble()) {
            direction = Vec3.reflect(unitDirection, rec.normal);
        } else {
            direction = Vec3.refract(unitDirection, rec.normal, ri);
        }

        scattered.setValues(rec.p, direction);
        return true;
    }

    private static double reflectance(double cosine, double refractionIndex) {
        var r0 = (1 - refractionIndex) / (1 + refractionIndex);
        r0 = r0 * r0;
        return r0 + (1 - r0) * Math.pow((1 - cosine) , 5);
    }

    private final double refractionIndex;
}
