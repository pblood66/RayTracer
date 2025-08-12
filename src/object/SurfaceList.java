package object;

import java.util.Vector;
import utils.Interval;
import utils.vector.Ray;

public class SurfaceList implements Surface {

    public SurfaceList() {
        this.objects = new Vector<Surface>();
    }

    public SurfaceList(Surface s) {
        add(s);
    }

    public void clear() {
        objects.clear();
    }

    public void add(Surface s) {
        objects.add(s);
    }

    @Override
    public boolean hit(Ray r, Interval rayT, SurfaceRecord rec) {
        SurfaceRecord temp = new SurfaceRecord();
        boolean hitDetected = false;
        var closestSoFar = rayT.max();

        for (var object : objects) {
            if (object.hit(r, new Interval(rayT.min(), closestSoFar), temp)) {
                hitDetected = true;
                closestSoFar = temp.t;
                rec.setValues(temp);
            }
        }

        return hitDetected;
    }

    public Vector<Surface> objects;
}
