import materials.Dielectric;
import materials.Lambertian;
import materials.Material;
import materials.Metal;
import object.Sphere;
import object.SurfaceList;
import utils.Camera;
import utils.Random;
import utils.vector.Color;
import utils.vector.Vec3;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        SurfaceList world = new SurfaceList();

       var groundMaterial = new Lambertian(new Color(0.5, 0.5, 0.5));
       world.add(new Sphere(new Vec3(0, -1000, 0), 1000, groundMaterial));

       for (int a = -11; a < 11; a++) {
           for (int b = -11; b < 11; b++) {
               var chooseMat = Random.randomDouble();
               Vec3 center = new Vec3(a + 0.9 * Random.randomDouble(), 0.2, b + 0.9 * Random.randomDouble());

               if ((center.subtract(new Vec3(4, 0.2, 0))).length() > 0.9) {
                   Material sphereMaterial;
                   if (chooseMat < 0.8) {
                       Color albedo = (Color) Color.random().multiply(Color.random());
                       sphereMaterial = new Lambertian(albedo);
                       world.add(new Sphere(center, 0.2, sphereMaterial));
                   } else if (chooseMat < 0.95) {
                       Color albedo = (Color) Color.random(0.5, 1);
                       var fuzz = Random.randomDouble(0, 0.5);
                       sphereMaterial = new Metal(albedo, fuzz);
                       world.add(new Sphere(center, 0.2, sphereMaterial));
                   } else {
                       sphereMaterial = new Dielectric(1.5);
                       world.add(new Sphere(center, 0.2, sphereMaterial));
                   }
               }
           }
       }

       var material1 = new Dielectric(1.5);
       world.add(new Sphere(new Vec3(0, 1, 0), 1.0, material1));

       var material2 = new Lambertian(new Color(0.4, 0.2, 0.1));
       world.add(new Sphere(new Vec3(-4, 1, 0), 1.0,  material2));

       var material3 = new Metal(new Color(0.7, 0.6, 0.5), 0.0);
       world.add(new Sphere(new Vec3(4, 1, 0), 1.0, material3));

        Camera camera = new Camera();

        camera.aspectRatio = 16.0 / 9.0;
        camera.imageWidth = 1200;
        camera.samplesPerPixel = 500;
        camera.maxDepth = 50;

        camera.vfov = 20;
        camera.lookFrom = new Vec3(13, 2, 3);
        camera.lookAt = new Vec3(0, 0, 0);
        camera.vup = new Vec3(0, 1, 0);

        camera.defocusAngle = 0.6;
        camera.focusDist    = 10.0;

        try {
            camera.render(world);
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }

        System.exit(0);
    }
}