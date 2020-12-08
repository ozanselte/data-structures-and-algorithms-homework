package com.ozanselte;

/**
 * Coordinate class
 * @author Ozan Şelte
 */
public class Coordinate {

    int x, y;

    /**
     * The constructor.
     * @param x the X-coordinate
     * @param y the Y-coordinate
     */
    Coordinate(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * The X-coordinates getter.
     * @return the X-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * The X-coordinates setter.
     * @param x the new X-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * The Y-coordinates getter.
     * @return the Y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * The Y-coordinates setter.
     * @param y the new Y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}
