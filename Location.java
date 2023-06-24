public class Location {
    private double x;
    private long y;
    private long z;
    public Location(double ix, long iy, long iz){
        x=ix; y=iy; z=iz;
    }
    public double getX(){
        return x;
    }
    public long getY(){
        return y;
    }
    public long getZ(){
        return z;
    }
}