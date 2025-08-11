import java.util.Vector;

public class SurfaceList implements Surface {

    public SurfaceList() {}

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
    public boolean hit(Ray r, double rayTmin, double rayTmax, SurfaceRecord rec) {
        SurfaceRecord temp = new SurfaceRecord();
        boolean hitDetected = false;
        var closestSoFar = rayTmax;

        for (var object : objects) {
            if (object.hit(r, rayTmin, rayTmax, temp)) {
                hitDetected = true;
                closestSoFar = temp.t;
                rec = temp;
            }
        }

        return hitDetected;
    }

    public Vector<Surface> objects;
}
