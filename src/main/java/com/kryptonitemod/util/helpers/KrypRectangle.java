package com.kryptonitemod.util.helpers;

public class KrypRectangle {
    public int x;
    public int y;
    public int width;
    public int height;

    public KrypRectangle() {
        this(0, 0, 0, 0);
    }

    public KrypRectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public KrypRectangle(KrypRectangle r) {
        this(r.x, r.y, r.width, r.height);
    }

    public KrypRectangle(int width, int height) {
        this(0, 0, width, height);
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Checks whether or not this <code>Rectangle</code> contains the
     * point at the specified location {@code (x,y)}.
     *
     * @param  x the specified X coordinate
     * @param  y the specified Y coordinate
     * @return    <code>true</code> if the point
     *            {@code (x,y)} is inside this
     *            <code>Rectangle</code>;
     *            <code>false</code> otherwise.
     */
    public boolean contains(int x, int y) {
        return inside(x, y);
    }

    public boolean inside(int X, int Y) {
        int w = this.width;
        int h = this.height;
        if ((w | h) < 0) {
            // At least one of the dimensions is negative...
            return false;
        }
        // Note: if either dimension is zero, tests below must return false...
        int x = this.x;
        int y = this.y;
        if (X < x || Y < y) {
            return false;
        }
        w += x;
        h += y;
        //    overflow || intersect
        return ((w < x || w > X) &&
                (h < y || h > Y));
    }
}
