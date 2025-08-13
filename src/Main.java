import materials.Lambertian;
import materials.Metal;
import object.Sphere;
import object.SurfaceList;
import utils.Camera;
import utils.vector.Color;
import utils.vector.Vec3;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        SurfaceList world = new SurfaceList();

        var materialGround = new Lambertian(new Color(0.8, 0.8, 0.0));
        var materialCenter = new Lambertian(new Color(0.1, 0.2, 0.5));
        var materialLeft = new Metal(new Color(0.8, 0.8, 0.8));
        var materialRight = new Metal(new Color(0.8, 0.6, 0.2));

        world.add(new Sphere(new Vec3(0.0, -100.5, -1.0), 100.0, materialGround));
        world.add(new Sphere(new Vec3(0.0, 0.0, -1.2), 0.5, materialCenter));
        world.add(new Sphere(new Vec3(-1.0, 0.0, -1.0), 0.5, materialLeft));
        world.add(new Sphere(new Vec3(1.0, 0.0, -1.0), 0.5, materialRight));

        Camera camera = new Camera();
        camera.aspectRatio = 16.0 / 9.0;
        camera.imageWidth = 400;
        camera.samplesPerPixel = 100;
        camera.maxDepth = 50;

        try {
            camera.render(world);
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }

        System.exit(0);
    }
}