package object;

import utils.Interval;
import utils.vector.Ray;

public interface Surface {
    public boolean hit(Ray r, Interval rayT, SurfaceRecord rec);
}
