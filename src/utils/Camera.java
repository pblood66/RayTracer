package utils;

import utils.vector.*;
import object.Surface;
import object.SurfaceRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Camera {
    public void render(Surface world) throws IOException {
        initialize();

        builder.append("P3\n").append(imageWidth).append(" ").append(imageHeight).append("\n255\n");

        for (int j = 0; j < imageHeight; j++) {
                System.out.print("\rLines Remaining: " + (imageHeight - j));
                System.out.flush();
            for (int i = 0; i < imageWidth; i++) {
                Color pixelColor = new Color(0, 0, 0);
                for (int sample = 0; sample < samplesPerPixel; sample++) {
                    Ray r = getRay(i, j);
                    pixelColor = (Color) pixelColor.add(rayColor(r, maxDepth, world));
                }

                pixelColor = (Color) pixelColor.multiply(pixelSamplesScale);
                builder.append(pixelColor.writeColor());
            }
        }
        writeToFile("image.ppm", builder.toString());
        System.out.println("\nDone");
    }

    private void initialize() {
        builder = new StringBuilder();

        imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = (imageHeight < 1) ? 1 : imageHeight;

        pixelSamplesScale = (double) (1.0 / samplesPerPixel);

        center = new Vec3(0, 0, 0);

        // viewport
        var focalLength = 1.0;
        var viewportHeight = 2.0;
        var viewportWidth = viewportHeight * aspectRatio;

        // viewport vectors
        var viewportU = new Vec3(viewportWidth, 0, 0);
        var viewportV = new Vec3(0, -viewportHeight, 0);

        pixelDeltaU = viewportU.divide(imageWidth);
        pixelDeltaV = viewportV.divide(imageHeight);

        // loc of upper left pixel
        var viewportUpperLeftLoc = center.subtract(pixelDeltaU)
                .subtract(new Vec3(0, 0, focalLength))
                .subtract(viewportU.divide(2))
                .subtract(viewportV.divide(2));
        this.pixel100Loc = viewportUpperLeftLoc.add(pixelDeltaV.add(pixelDeltaU).multiply(0.5));
    }

    private Ray getRay(int i, int j) {
        var offset = sampleSquare();
        var iOffset = pixelDeltaU.multiply(i + offset.x());
        var jOffset = pixelDeltaV.multiply(j + offset.y());
        var pixelSample = pixel100Loc.add(iOffset).add(jOffset);

        var rayOrigin = center;
        var rayDirection = pixelSample.subtract(rayOrigin);

        return new Ray(rayOrigin, rayDirection);
    }

    private Vec3 sampleSquare() {
        return new Vec3(Random.randomDouble() - 0.5,  Random.randomDouble() - 0.5, 0);
    }

    private Color rayColor(Ray r, int depth, Surface world) {
        if (depth <= 0) {
            return new Color(0, 0, 0);
        }

        SurfaceRecord rec = new SurfaceRecord();

        if (world.hit(r, new Interval(0.001, Double.POSITIVE_INFINITY), rec)) {
            Ray scattered = new Ray();
            Color attenuation = new Color(0,0,0);

            if (rec.mat.scatter(r, rec, attenuation, scattered)) {
                return (Color) attenuation.multiply(rayColor(scattered, depth - 1, world));
            }
            return new Color(0, 0,0 );
        }
        Vec3 unitDirection = r.direction().normalize();

        double a = 0.5 * (unitDirection.y() + 1.0);
        Color startValue = new Color(1.0, 1.0, 1.0);
        Color endValue = new Color(0.5, 0.7, 1.0);

        return (Color) startValue.multiply(1.0 - a).add(endValue.multiply(a));
    }

    private void writeToFile(String filename, String contents) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(contents);

        writer.close();
    }


    public double aspectRatio = 1.0;
    public int imageWidth = 100;
    public int samplesPerPixel = 10;
    public int maxDepth = 10;

    private int imageHeight;
    private double pixelSamplesScale;
    private Vec3 center;
    private Vec3 pixel100Loc;
    private Vec3 pixelDeltaU;
    private Vec3 pixelDeltaV;
    private StringBuilder builder;
}
