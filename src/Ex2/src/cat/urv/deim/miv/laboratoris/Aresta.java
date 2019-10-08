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
            this.v1 = v2;
            this.v2 = tmp;
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
