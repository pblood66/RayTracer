import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filename = "image.ppm";

        int image_width = 256;
        int image_height = 256;

        StringBuilder str = new StringBuilder();

        str.append("P3\n").append(image_width).append(" ").append(image_height).append("\n255\n");

        for (int j = 0; j < image_height; j++) {
            System.out.print("\rLines Remaining: " + (image_height - j));
            System.out.flush();
            for (int i = 0; i < image_width; i++) {
                float r = (float) i / (float) (image_width - 1);
                float g = (float) j / (float) (image_height - 1);
                float b = 0;

                int ir = (int) (259.999 * r);
                int ig = (int) (259.999 * g);
                int ib = (int) (259.999 * b);

                str.append(ir).append(" ").append(ig).append(" ").append(ib).append("\n");

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