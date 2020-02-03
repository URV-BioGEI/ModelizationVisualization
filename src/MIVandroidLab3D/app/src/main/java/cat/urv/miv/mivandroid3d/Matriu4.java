package cat.urv.miv.mivandroid3d;

import java.util.Arrays;

/**
 * HC for 4x4 Matrix. Com més HC, menys parametritzacions i per tant menys instruccions i codi més ràpid
 */
public class Matriu4 {

    private float[] values;

    public Matriu4() {
        this.values = new float[16];
    }  // i = r * 4 + c

    public Matriu4(Matriu4 m) {
        this.values = m.getValues().clone();
    }

    public Matriu4(Float[] values) {
        for (int i = 0; i < values.length; i++)
        {
            this.values[i] = (float) values[i];
        }
    }

    // Aliasing in this case
    public Matriu4(float[] values) {
        this.values = values;
    }

    public Matriu4 clone() {
        return new Matriu4(this);
    }

    public static Matriu4 identity() {
        Matriu4 result = new Matriu4();
        result.set(0 * 4 + 0, 1.0F);
        result.set(1 * 4 + 1, 1.0F);
        result.set(2 * 4 + 2, 1.0F);
        result.set(3 * 4 + 3, 1.0F);
        return result;
    }

    public float[] getValues() {
        return this.values;
    }

    public float get(int r, int c) {
        return this.values[r * 4 + c];
    }

    public void set(int index, float value) {
        this.values[index] = value;
    }

    public void set(int r, int c, float value) {
        this.values[r * 4 + c] = value;
    }

    public Matriu4 mult(Matriu4 o) {
        Matriu4 result = new Matriu4();
        for(int r = 0; r < 4; ++r)
        {
            for(int c = 0; c < 4; ++c)
            {
                float value = 0.0F;
                for(int i = 0; i < 4; ++i)  value += this.get(r, i) * o.get(i, c);  // Multipliquem els 4 parells de nombres

                result.set(r, c, value);
            }
        }

        return result;
    }

    Vertex4 mult(Vertex4 o) {
        Vertex4 result = new Vertex4();
        for(int r = 0; r < 4; ++r)
        {
            float value = 0.0F;
            for(int c = 0; c < 4; ++c)  value += this.get(r, c) * o.get(c);
            result.set(r, value);
        }
        return result;
    }

    public String toString() {
        String result = "";
        for(int r = 0; r < 4; ++r)
        {
            result = result.concat("[");
            for(int c = 0; c < 4; ++c)
            {
                result = result.concat(((Float)this.get(r, c)).toString());  //
                if (c != 3) result = result.concat(", ");
            }
            result = result.concat("]");
        }
        return result;
    }
}

