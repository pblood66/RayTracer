public interface Surface {
    public boolean hit(Ray r, Interval rayT, SurfaceRecord rec);
}
