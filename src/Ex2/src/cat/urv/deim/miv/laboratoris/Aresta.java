package cat.urv.deim.miv.laboratoris;

import java.util.Objects;

public class Aresta {
    private Vertex v1;
    private Vertex v2;

    public Aresta(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;

        if (v1.getY() > v2.getY())
        {
            Vertex tmp = this.v1;
            this.v1 = this.v2;
            this.v2 = tmp;
        }
    }

    public int intersectar(int y) {
        if (y < this.v1.getY())
        {
            return -1;
        }
        else if (y > this.v2.getY())
        {
            return -1;
        }
        else if (this.v1.getY() == this.v2.getY())
        {
            return -1;  // les linees horitzontals no es poden intersectar
        }
        else {
            // Y - Y0 = m * (X - X0) -> X = (Y - Y0) / m + X0 = (Y - Y0) * (1 / m) + X0 = (Y - Y0) * dx / dy + X0
            float dx = (float)(this.v2.getX() - this.v1.getX());
            float dy = (float)(this.v2.getY() - this.v1.getY());
            float a = (float) (y - this.v1.getY());
            float b = a * dx / dy;
            int c = (int)b;
            return c + this.v1.getX();
            //return (int)((float)(y - this.v1.getY()) * dx / dy + this.v1.getX());
            /*
            Intersect
            float dx = (float)(this.v2.getX() - this.v1.getX());
            float dy = (float)(this.v2.getY() - this.v1.getY());
            float tpc = (float)(y - this.v1.getY()) * dx / dy;
            return this.v1.getX() + (int)tpc;
        }
             */
        }
    }

    public Vertex getV1() {
        return v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "Aresta{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta aresta = (Aresta) o;
        return Objects.equals(v1, aresta.v1) &&
                Objects.equals(v2, aresta.v2);
    }

}
