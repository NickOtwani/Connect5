public class Location {
    private int x;
    private int y;
    private int z;


    public Location(int x, int y, int z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }
    public String toString()
    {
        return "(x="+x+", y="+y+", z="+z+")";
    }



}
//finished
