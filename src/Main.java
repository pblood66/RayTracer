import java.io.FileWriter;
import java.io.IOException;


public class Main {

    public static double hitSphere(Vec3 center, double radius, Ray r)  {
        Vec3 oc = center.subtract(r.origin());
        var a = r.direction().lengthSquared();
        var h = Vec3.dot(r.direction(), oc);
        var c = oc.lengthSquared() - radius * radius;
        var discriminant = h * h - a * c;

        if (discriminant < 0) {
            return -1.0;
        } else {
            return (h - Math.sqrt(discriminant)) / a;
        }
    }

    public static Color rayColor(Ray r) {
        var t = hitSphere(new Vec3(0, 0, -1), 0.5, r);
        if (t > 0.0) {
            Vec3 N = Vec3.unitVector(r.at(t).subtract(new Vec3(0, 0, -1)));
            return (Color) new Color(N.x() + 1, N.y() + 1, N.z() + 1).multiply(0.5);
        }

        Vec3 unitDirection = r.direction().normalize();

        double a = 0.5 * (unitDirection.y() + 1.0);
        Color startValue = new Color(1.0, 1.0, 1.0);
        Color endValue = new Color(0.5, 0.7, 1.0);

        return (Color) startValue.multiply(1.0 - a).add(endValue.multiply(a));
    }

    public static void main(String[] args) {
        String filename = "image.ppm";

        int imageWidth = 1600;
        double aspectRatio = 16.0 / 9.0;
        int imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = (imageHeight < 1) ? 1 : imageHeight;

        double focalLength = 1.0;
        double viewportHeight = 2.0;
        double viewportWidth = viewportHeight * aspectRatio;
        Vec3 cameraCenter = new Vec3(0, 0, 0);

        Vec3 viewportU = new Vec3(viewportWidth, 0, 0);
        Vec3 viewPortV = new Vec3(0, -viewportHeight, 0);

        Vec3 pixelDeltaU = viewportU.divide(imageWidth);
        Vec3 pixelDeltaV = viewPortV.divide(imageHeight);

        // Calculate the location of the upper left pixel.
        // cameraCenter - pixelDeltaU - vec3(0, 0, focalLength) - (viewportU / 2) - (viewportV / 2);
        Vec3 viewportUpperLeft = cameraCenter.subtract(pixelDeltaU)
                .subtract(new Vec3(0, 0, focalLength))
                .subtract(viewportU.divide(2))
                .subtract(viewPortV.divide(2));
        Vec3 pixel100Loc = viewportUpperLeft.add(pixelDeltaV.add(pixelDeltaU).multiply(0.5));

        StringBuilder str = new StringBuilder();

        str.append("P3\n").append(imageWidth).append(" ").append(imageHeight).append("\n255\n");

        for (int j = 0; j < imageHeight; j++) {
            System.out.print("\rLines Remaining: " + (imageHeight - j));
            System.out.flush();
            for (int i = 0; i < imageWidth; i++) {
                Vec3 pixelCenter = (pixel100Loc.add(pixelDeltaU.multiply(i))).add(pixelDeltaV.multiply(j));
                Vec3 rayDirection = pixelCenter.subtract(cameraCenter);

                Ray ray = new Ray(cameraCenter, rayDirection);

                Color pixelColor = rayColor(ray);
                str.append(pixelColor.writeColor());
            }
        }

        System.out.println("\nDone");

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(str.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}