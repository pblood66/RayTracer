import object.Sphere;
import object.SurfaceList;
import utils.Camera;
import utils.vector.Vec3;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
       SurfaceList world = new SurfaceList();

       world.add(new Sphere(new Vec3(0, -100.5, -1), 100));
       world.add(new Sphere(new Vec3(0, 0, -1), 0.5));

       Camera camera = new Camera();
       camera.aspectRatio = 16.0 / 9.0;
       camera.imageWidth = 1600;
       camera.samplesPerPixel = 100;

       try {
            camera.render(world);
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }

       System.exit(0);
    }
}