import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
       SurfaceList world = new SurfaceList();

       world.add(new Sphere(new Vec3(0, -100.5, -1), 100));
       world.add(new Sphere(new Vec3(0, 0, -1), 0.5));

       Camera camera = new Camera();
       camera.aspectRatio = 1.0;
       camera.imageWidth = 400;

       try {
            camera.render(world);
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }

       System.exit(0);
    }
}