package cat.urv.deim.gc.laboratoris;

/**
 * HC for 4x4 Matrix. Com més HC, menys parametritzacions i per tant menys instruccions i codi més ràpid
 */
public class MatrixR4 {

    private float[] values;

    public MatrixR4() {
        this.values = new float[16];
    }  // i = r * 4 + c

    public MatrixR4(MatrixR4 m) {
        this.values = m.getValues().clone();
    }

    public MatrixR4 clone() {
        return new MatrixR4(this);
    }

    public static MatrixR4 identity() {
        MatrixR4 result = new MatrixR4();
        result.set(0 * 4 + 0, 1.0F);
        result.set(1 * 4 + 1, 1.0F);
        result.set(2 * 4 + 2, 1.0F);
        result.set(3 * 4 + 3, 1.0F);
        return result;
    }

    public float[] getValues() {
        return this.values;
    }

    public float get(int index) {
        return this.values[index];
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

    public MatrixR4 mult(MatrixR4 o) {
        MatrixR4 result = new MatrixR4();
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

    public VectorR4 mult(VectorR4 o) {
        VectorR4 result = new VectorR4();
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

