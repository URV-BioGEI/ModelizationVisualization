package cat.urv.deim.gc.laboratoris;

public class ViewPort {
        private int x;
        private int y;
        private int width;
        private int height;

        public ViewPort(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public VectorR2 normalize(VectorR4 newVertex)
        {
            /*
             * X' = Xviewport / W
             * Y' = Yviewport / W
             * Xwin = Xini + (x' + 1) * (WIDTH / 2)
             * Ywin = Yini + (-y' + 1) * (HEIGHT / 2)
             *
             */
            // Operem amb precisió máxima i recastegem a int
            int coordinadaX = (int)((float)this.x + (newVertex.get(0) / newVertex.get(3) + 1.0F) * ((float)this.width / 2.0F));
            int coordinadaY = (int)((float)this.y + (-1.0F * newVertex.get(1) / newVertex.get(3) + 1.0F) * ((float)this.height / 2.0F));

            return new VectorR2(coordinadaX, coordinadaY);
        }
}
