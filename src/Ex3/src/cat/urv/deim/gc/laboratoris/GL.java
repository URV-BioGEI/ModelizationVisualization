package cat.urv.deim.gc.laboratoris;

import java.util.Stack;

public class GL {
    // Probablement totes les variables acabin sent estàtiques
    private static ViewPort viewport;
    public static final int GL_PROJECTION = 0;
    public static final int GL_MODELVIEW = 1;
    private static int model = 0;
    private static Stack<MatrixR4> projectionStack = new Stack<>();
    private static Stack<MatrixR4> modelviewStack = new Stack<>();

    // De moment he fet un singleton per conservar la crida al objecte per si es mes necessari mes endavant
    private static GL ownInstance = null;
    private GL() { }
    public static GL getInstance()
    {
        if (GL.ownInstance == null)
        {
            GL.ownInstance = new GL();
        }
        return GL.ownInstance;
    }

    public static ViewPort getViewport() {
        return GL.viewport;
    }

    public static int getModel() {
        return model;
    }

    public static void setModel(int model) {
        GL.model = model;
    }

    public static void setViewport(int x, int y, int width, int height) {
        GL.viewport = new ViewPort(x, y, width, height);
    }

    // podria ser public?
    private static Stack<MatrixR4> getStack() {
        switch(GL.model) {
            case 0:
                return GL.projectionStack;
            case 1:
                return GL.modelviewStack;
            default:
                return null;
        }
    }

    public static MatrixR4 popMatrix()
    {
        return GL.getStack().pop();
    }

    public static void pushMatrix()
    {
        MatrixR4 cloned = ((MatrixR4)GL.getStack().peek()).clone();
        GL.getStack().push(cloned);
    }

    public static void loadIdentity()
    {
        GL.getStack().pop();
        GL.getStack().push(MatrixR4.identity());
    }

    public static void gluPerspective(float fovy, float aspect, float zNear, float zFar)
    {
        double radians = (double)fovy * 3.141592653589793D / 180.0D;  // Transformació a radiants
        double f = 1.0D / Math.tan(radians / 2.0D);  // Cotangent de fovy / 2 = near / top

    }






}
