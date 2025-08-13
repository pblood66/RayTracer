package materials;

import object.SurfaceRecord;
import utils.Interval;
import utils.vector.Color;
import utils.vector.Ray;

public interface Material {
    boolean scatter(Ray rayIn, SurfaceRecord rec, Color attenuation, Ray scattered);
}
