package cat.urv.deim.gc.laboratoris;

public class VectorR4 {
    private float[] values;

    public VectorR4() { this.values = new float[4]; }

    public VectorR4(float x, float y, float z, float w) { this.values = new float[]{x, y, z, w}; }

    public static VectorR4 zero () { return new VectorR4(0.0F, 0.0F, 0.0F, 0.0F); }

    public float get(int index) { return this.values[index]; }

    public void set(int index, float value) { this.values[index] = value; }

    public float module()
    {
        float length = 0.0F;
        for (float value : this.values) length += value * value;
        return (float)Math.sqrt((double)length);
    }

    public VectorR4 normalize()
    {
        float result = this.module();
        return new VectorR4(this.values[0] / result, this.values[1] / result, this.values[2] / result, this.values[3] / result);
    }
/*
    public VectorR4 cross3(VectorR4 o) {
        VectorR4 result = new VectorR4(this.values[1] * o.values[2] - this.values[2] * o.values[1], this.values[2] * o.values[0] - this.values[0] * o.values[2], this.values[0] * o.values[1] - this.values[1] * o.values[0], 0.0F);
        return result;
    }
*/
    public VectorR4 add(VectorR4 o)
    {
        VectorR4 v = new VectorR4();
        for(int i = 0; i < 4; ++i) v.set(i, this.values[i] + o.values[i]);
        return v;
    }

    public VectorR4 add(float x, float y, float z, float w) { return this.add(new VectorR4(x, y, z, w)); }

    public VectorR4 directMultiplication(float d)
    {
        VectorR4 v = new VectorR4();
        for(int i = 0; i < 4; ++i) v.set(i, this.values[i] * d);
        return v;
    }


    public String toString()
    {
        String result = "";
        result = result.concat("(");
        for(int i = 0; i < this.values.length; ++i)
        {
            result = result.concat(((Float)this.values[i]).toString());
            if (i != this.values.length - 1) result = result.concat(", ");
        }

        result = result.concat(")");
        return result;
    }
}
