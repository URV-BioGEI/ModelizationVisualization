package cat.urv.deim.gc.laboratoris;

import java.util.*;

public class GL {
    private static ViewPort viewport;
    public static final int GL_PROJECTION = 0;
    public static final int GL_MODELVIEW = 1;
    private static int currentStack = 0;
    private static Stack<MatrixR4> projectionStack = new Stack<>();
    private static Stack<MatrixR4> modelViewStack = new Stack<>();
    private static List<VectorR2> currentPolygon = new ArrayList<>();

    /*
    * Static constructor to initialize stacks with identity.
     */
    static void init()
    {
        GL.getModelViewStack().push(MatrixR4.identity());
        GL.getProjectionStack().push(MatrixR4.identity());
    }

    /*
    * Receives one of the two stacks depending on the state saved in currentStack class variable.
     */
    private static Stack<MatrixR4> getStack() {
        switch(GL.currentStack) {
            case 0:
                return GL.projectionStack;
            case 1:
                return GL.modelViewStack;
            default:
                return null;
        }
    }

    static void pushMatrix()
    {
        GL.getStack().push(GL.getStack().peek().clone());
    }


    static MatrixR4 popMatrix()
    {
        return GL.getStack().pop();
    }

    static void loadIdentity()
    {
        GL.getStack().pop();
        GL.getStack().push(MatrixR4.identity());
    }

    static void vector3f(float x, float y, float z)
    {
        if (!(x == -1 && y == -1 && z == 0))  // No es centinella. El tractem com un vector normal
        {
            VectorR4 newVertex = new VectorR4(x, y, z, 1.0F);  // Definim nou vertex
            MatrixR4 projection = GL.getProjectionStack().peek();  // Obtenim matriu de projecció
            MatrixR4 modelview = GL.getModelViewStack().peek();  // Obtenim matriu de modelView
            MatrixR4 combinedProjectionModelView = projection.mult(modelview);  // Multipliquem projection * modelView
            // Multipliquem la matriu combinada pel nou vertex per obtenir les coordenades al nou sistema de referencia
            newVertex = combinedProjectionModelView.mult(newVertex);
            // Transformem les coordenades del punt a punts en pantalla amb la transformació del viewport
            VectorR2 v2 = GL.getViewport().normalize(newVertex);
            GL.currentPolygon.add(v2);
        }
        else  // Es centinella, el fiquem a dins tal qual
        {
            GL.currentPolygon.add(new VectorR2((int)x, (int)y));
        }

    }

    static void beginPolygon()
    {
        GL.currentPolygon = new LinkedList<>();
    }

    static Integer[] endPolygon()
    {
        List<Integer> coordinateList = new LinkedList<>();
        for (VectorR2 v : GL.currentPolygon)
        {
            coordinateList.add(v.getX());
            coordinateList.add(v.getY());
        }
        return coordinateList.toArray(new Integer[0]);
    }

    static void gluPerspective(float fovy, float aspect, float zNear, float zFar)
    {
        /*
         * https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/gluPerspective.xml
         * {
         * {1 / (AspectRatio * tan ( fovy / 2)), 0, 0, 0}
         * {0, 1 / tan (fovy / 2), 0, 0}
         * {0, 0, (NearZ + FarZ) / (FarZ - NearZ), 2 * FarZ * NearZ / (NearZ - FarZ)}
         * {0, 0, 1, 0}
         *
         * cotan(alfa) = 1 / tan(alfa)
         */
        double fovyRadians = fovy * 3.141592653589793D / 180.0D;  // Convert to radians
        double f = 1.0D / Math.tan(fovyRadians / 2.0D);  // f = Cotan(fovy / 2)

        // Define GLU perspective
        MatrixR4 m = new MatrixR4();
        m.set(0, (float)(f / (double)aspect));
        m.set(5, (float)f);
        m.set(10, (zFar + zNear) / (zNear - zFar));
        m.set(11, 2.0F * zFar * zNear / (zNear - zFar));
        m.set(14, -1.0F);
        m = GL.getStack().pop().mult(m);
        GL.getStack().push(m);
    }

    static void translate(float x, float y, float z)
    {
        // Generate Translation Matrix
        MatrixR4 m = MatrixR4.identity();
        m.set(3, x);
        m.set(7, y);
        m.set(11, z);

        // Mult and push
        GL.getStack().push(GL.getStack().pop().mult(m));
    }

    static void scale(float x, float y, float z)
    {
        // Generate scalation matrix
        MatrixR4 m = MatrixR4.identity();
        m.set(0, x);
        m.set(5, y);
        m.set(10, z);

        // Mult and push
        m = GL.getStack().pop().mult(m);
        GL.getStack().push(m);
    }

    // https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glRotate.xml
    static void rotatef(float angle, float x, float y, float z)
    {
        // Normalize (get unitary vector)
        double angleRadians = (double)angle * Math.PI / 180.0D;
        double c = Math.cos(angleRadians);
        double s = Math.sin(angleRadians);
        double mod = Math.sqrt((double)(x * x + y * y + z * z));
        if (mod != 1.0D) {
            x = (float)((double)x / mod);
            y = (float)((double)y / mod);
            z = (float)((double)z / mod);
        }

        // Generate rotation matrix
        MatrixR4 m = new MatrixR4();
        m.set(0, (float)((double)(x * x) * (1.0D - c) + c));
        m.set(1, (float)((double)(x * y) * (1.0D - c) - (double)z * s));
        m.set(2, (float)((double)(x * z) * (1.0D - c) + (double)y * s));
        m.set(3, 0.0F);
        m.set(4, (float)((double)(y * x) * (1.0D - c) + (double)z * s));
        m.set(5, (float)((double)(y * y) * (1.0D - c) + c));
        m.set(6, (float)((double)(y * z) * (1.0D - c) - (double)x * s));
        m.set(7, 0.0F);
        m.set(8, (float)((double)(x * z) * (1.0D - c) - (double)y * s));
        m.set(9, (float)((double)(y * z) * (1.0D - c) + (double)x * s));
        m.set(10, (float)((double)(z * z) * (1.0D - c) + c));
        m.set(11, 0.0F);
        m.set(12, 0.0F);
        m.set(13, 0.0F);
        m.set(14, 0.0F);
        m.set(15, 1.0F);

        // Apply rotation
        GL.getStack().push(GL.getStack().pop().mult(m));
    }

    public static Stack<MatrixR4> getProjectionStack() {
        return projectionStack;
    }

    public static Stack<MatrixR4> getModelViewStack() {
        return modelViewStack;
    }

    private static ViewPort getViewport() {
        return GL.viewport;
    }

    static void setCurrentStack(int currentStack) {
        GL.currentStack = currentStack;
    }

    static void setViewport(int x, int y, int width, int height) {
        GL.viewport = new ViewPort(x, y, width, height);
    }




}
