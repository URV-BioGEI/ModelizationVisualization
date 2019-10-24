package cat.urv.deim.miv.laboratoris;

import java.util.*;

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
        if (y < this.v1.getY()) return -1;
        else if (y > this.v2.getY()) return -1;
        else if (this.v1.getY() == this.v2.getY()) return -1;  // les linees horitzontals no es poden intersectar
        else
        {
            // Y - Y0 = m * (X - X0) -> X = (Y - Y0) / m + X0 = (Y - Y0) * (1 / m) + X0 = (Y - Y0) * dx / dy + X0
            float dx = (float)(this.v2.getX() - this.v1.getX());
            float dy = (float)(this.v2.getY() - this.v1.getY());
            float b = (float) (y - this.v1.getY()) * dx / dy;
            return (int)b + this.v1.getX();
        }
    }

    public boolean esConvex(Aresta a)
    {
        List<Vertex> puntsSegments = new LinkedList<>();
        Vertex comu;
        // Afegim tots els vertex i despres eliminem el vertex comú
        puntsSegments.add(this.v1);
        puntsSegments.add(this.v2);
        if (puntsSegments.contains(a.v1))
        {
            puntsSegments.remove(a.v1);
            puntsSegments.add(a.v2);
            comu = a.v1;
        }
        else  // Assumim arestes consecutives
        {
            puntsSegments.remove(a.v2);
            puntsSegments.add(v1);
            comu = a.v2;
        }
        /*
        * PRE: Tenim 2 punts en la nostra llista, corresponent als punts extrems de dues arestes consecutives
        * POST: Obtenim la disposicio dels punts, indicant si cal emmagatzemar dues interseccions o no
         */
        return !((comu.getY() > puntsSegments.get(0).getY() && comu.getY() > puntsSegments.get(1).getY()) || (comu.getY() < puntsSegments.get(0).getY() && comu.getY() < puntsSegments.get(1).getY()));
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
