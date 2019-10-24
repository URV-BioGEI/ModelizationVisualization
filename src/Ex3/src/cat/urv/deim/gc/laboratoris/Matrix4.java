package cat.urv.deim.gc.laboratoris;

import cat.urv.deim.miv.Vertex4;

/**
 * HC for 4x4 Matrix. Com més HC, menys parametritzacions i per tant menys instruccions i codi més ràpid
 */
public class Matrix4 {
        private float[] values;

        public Matrix4() {
            this.values = new float[16];
        }

        public Matrix4(Matrix4 m) {
            this.values = m.getValues().clone();
        }

        public Matrix4 clone() {
            return new Matrix4(this);
        }

        public static cat.urv.deim.miv.Matrix4 identity() {
            cat.urv.deim.miv.Matrix4 result = new cat.urv.deim.miv.Matrix4();
            result.set(0, 1.0F);
            result.set(5, 1.0F);
            result.set(10, 1.0F);
            result.set(15, 1.0F);
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

        public cat.urv.deim.miv.Matrix4 mult(cat.urv.deim.miv.Matrix4 o) {
            cat.urv.deim.miv.Matrix4 result = new cat.urv.deim.miv.Matrix4();

            for(int r = 0; r < 4; ++r) {
                for(int c = 0; c < 4; ++c) {
                    float value = 0.0F;

                    for(int i = 0; i < 4; ++i) {
                        value += this.get(r, i) * o.get(i, c);
                    }

                    result.set(r, c, value);
                }
            }

            return result;
        }

        public Vertex4 mult(Vertex4 o) {
            Vertex4 result = new Vertex4();

            for(int r = 0; r < 4; ++r) {
                float value = 0.0F;

                for(int c = 0; c < 4; ++c) {
                    value += this.get(r, c) * o.get(c);
                }

                result.set(r, value);
            }

            return result;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            for(int r = 0; r < 4; ++r) {
                sb.append('[');

                for(int c = 0; c < 4; ++c) {
                    sb.append(this.get(r, c));
                    if (c != 3) {
                        sb.append(", ");
                    }
                }

                sb.append(']');
            }

            return sb.toString();
        }
    }

