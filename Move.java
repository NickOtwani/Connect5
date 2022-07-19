public class Move {
    private int col;
    private int row;

    public Move(int col, int row)
    {
        this.col=col;
        this.row=row;
    }

    public int getCol()
    {
        return col;
    }
    public int getRow()
    {
        return row;
    }
    public String toString()
    {
        return "("+col+", "+row+")";
    }

}
//finished
