package materials;

import object.SurfaceRecord;
import utils.vector.Color;
import utils.vector.Ray;
import utils.vector.Vec3;

public class Lambertian implements Material {
    public Lambertian(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rayIn, SurfaceRecord rec, Color attenuation, Ray scattered) {
        var scatterDirection = rec.normal.add(Vec3.randomUnitVector());

        if (scatterDirection.nearZero()) {
            scatterDirection = rec.normal;
        }

        scattered.setValues(rec.p, scatterDirection);
        attenuation.setColor(albedo);
        return true;
    }

    private Color albedo;
}
