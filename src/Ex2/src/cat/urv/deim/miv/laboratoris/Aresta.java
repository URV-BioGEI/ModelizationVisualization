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

    public Vertex vectorAresta ()
    {
        return new Vertex(this.v2.getX() - this.v1.getX(), this.v2.getY() - this.v1.getY());
    }

    public int producteEscalar (Aresta a)
    {
        // Obtenim els vectors dels costats
        Vertex b = this.vectorAresta();
        Vertex c = a.vectorAresta();

        // Calculem producte escalar
        return b.getX() * c.getX() + b.getY() * c.getY();
    }

    public int producteVectorial (Aresta a)
    {  // Suposem this com el vector u, el primer que es coloca
        // Calculem vectors aresta
        Vertex b = this.vectorAresta();
        Vertex c = a.vectorAresta();

        return b.getX() * c.getY() - b.getY() * c.getX();
    }
    /*
    *
     */
    public boolean esConvex(Aresta a)
    {
        Vertex comu = this.vertexComu(a);  // Obtenim el vertex en comu amb les dues arestes
        if (comu != null)
        {
            List<Vertex> puntsSegments = new LinkedList<>();

            // Afegim tots els vertex i desprem eliminem el vertex comú
            puntsSegments.add(this.v1);
            puntsSegments.add(this.v2);
            puntsSegments.add(a.v1);
            puntsSegments.add(a.v2);
            puntsSegments.remove(comu);  // //RF aixo es una miketa Marranada
            puntsSegments.remove(comu);
            /*
            * PRE: Tenim 2 punts en la nostra llista, corresponent als punts extrems de dues arestes consecutives
            * POST: Obtenim la disposicio dels punts, indicant si cal emmagatzemar dues interseccions o no
             */
            return !((comu.getY() > puntsSegments.get(0).getY() && comu.getY() > puntsSegments.get(1).getY()) || (comu.getY() < puntsSegments.get(0).getY() && comu.getY() < puntsSegments.get(1).getY()));
        }
        return false;
    }

    public Vertex vertexComu (Aresta a)
    {
        if (a.v1.equals(this.v2)) return this.v2;
        else if (a.v2.equals(this.v1)) return this.v1;
        else if (a.v1.equals(this.v1)) return this.v1;
        else if (a.v2.equals(this.v2)) return this.v2;
        return null;
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
