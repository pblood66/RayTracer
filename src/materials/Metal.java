package materials;

import object.SurfaceRecord;
import utils.vector.Color;
import utils.vector.Ray;
import utils.vector.Vec3;

public class Metal implements Material {
    public Metal(Color albedo, double fuzz) {
        this.albedo = albedo;
        this.fuzz = fuzz < 1 ? fuzz : 1;
    }

    @Override
    public boolean scatter(Ray rayIn, SurfaceRecord rec, Color attenuation, Ray scattered) {
        Vec3 reflected = Vec3.reflect(rayIn.direction(), rec.normal);
        reflected = reflected.normalize().add(Vec3.randomUnitVector().multiply(fuzz));
        scattered.setValues(rec.p, reflected);
        attenuation.setColor(albedo);
        return (Vec3.dot(scattered.direction(), rec.normal) > 0);
    }

    private Color albedo;
    private double fuzz;
}
