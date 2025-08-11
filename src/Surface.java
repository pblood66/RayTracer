public interface Surface {
    public boolean hit(Ray r, double rayTmin, double rayTmax, SurfaceRecord rec);
}
