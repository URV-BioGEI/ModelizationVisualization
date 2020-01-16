package cat.urv.deim.gc.laboratoris;

public class VectorR2 {
    private int x;
    private int y;

    public VectorR2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")";
    }
}
