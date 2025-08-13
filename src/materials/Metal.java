package materials;

import object.SurfaceRecord;
import utils.vector.Color;
import utils.vector.Ray;
import utils.vector.Vec3;

public class Metal implements Material {
    public Metal(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rayIn, SurfaceRecord rec, Color attenuation, Ray scattered) {
        Vec3 reflected = Vec3.reflect(rayIn.direction(), rec.normal);
        scattered.setValues(rec.p, reflected);
        attenuation.setColor(albedo);
        return true;
    }

    private Color albedo;
}
