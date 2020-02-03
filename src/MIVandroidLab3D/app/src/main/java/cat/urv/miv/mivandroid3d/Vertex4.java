package cat.urv.miv.mivandroid3d;

public class Vertex4 {

	public static final Vertex4 ZERO = new Vertex4(0.0f, 0.0f, 0.0f, 0.0f);
	
	private float values[];
	
	public Vertex4() {
		values = new float[4];
	}

	public Vertex4(float[] v) { values = v; }

	public Vertex4(float x, float y, float z, float w) {
		values = new float[] {x, y, z, w};
	}

	public float get(int index) {
		return values[index];
	}

	public void setValues(float[] values) {
		this.values = values;
	}

	public void set(int index, float value) {
		values[index] = value;
	}

	public float module() {
		float length = 0.0f;
		for(int i = 0; i < values.length; i++)
			length += values[i] * values[i];
		return (float) Math.sqrt(length);
	}
	
	public Vertex4 normalize() {
		float l = module();
		if (l == 0)
			return this;
		else
			return new Vertex4(values[0] / l, values[1] / l, values[2] / l, values[3] / l);
	}
	
	public Vertex4 cross3(Vertex4 o) {
		Vertex4 result = new Vertex4(
				values[1] * o.values[2] - values[2] * o.values[1],
				values[2] * o.values[0] - values[0] * o.values[2],
				values[0] * o.values[1] - values[1] * o.values[0],
				0.0f);
		return result;
	}
	
	public Vertex4 add(Vertex4 o) {
		Vertex4 v = new Vertex4();
		for(int i = 0; i < 4; i++)
			v.set(i, values[i] + o.values[i]);
		return v;
	}
	
	public Vertex4 add(float x, float y, float z, float w) {
		return add(new Vertex4(x, y, z, w));
	}

	public Vertex4 mult(float d) {
		Vertex4 v = new Vertex4();
		for(int i = 0; i < 4; i++)
			v.set(i, values[i] * d);
		return v;
	}

	// Does not modify the current object
	public Vertex4 mult(Matriu4 m)
	{
		float[] matrixValues = m.getValues();
		float[] vectorOutput = new float[]{0, 0, 0, 0};
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				vectorOutput[i] += matrixValues[i * 4 + j] * this.values[j];
		return new Vertex4(vectorOutput);
	}

	// Does modify the current object
	public void multiply(Matriu4 m)
	{
		float[] vectorValues = this.values.clone();  // copy current values
		for (int i = 0; i < 4; i++) values[i] = 0;  // Init in 0
		float[] matrixValues = m.getValues();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				values[i] += matrixValues[i * 4 + j] * vectorValues[j];
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		for(int i = 0; i < values.length; i++) {
			sb.append(values[i]);
			if (i != values.length - 1)
				sb.append(", ");
		}
		sb.append(')');
		return sb.toString();
	}
}
