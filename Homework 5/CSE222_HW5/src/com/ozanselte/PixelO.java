package com.ozanselte;

public class PixelO {

    private char red, green, blue;

    public char getRed() {
        return red;
    }

    public void setRed(char red) {
        this.red = red;
    }

    public char getGreen() {
        return green;
    }

    public void setGreen(char green) {
        this.green = green;
    }

    public char getBlue() {
        return blue;
    }

    public void setBlue(char blue) {
        this.blue = blue;
    }

    public PixelO(char red, char green, char blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    public PixelO(int red, int green, int blue) {
        this((char)red, (char)green, (char)blue);
    }

    public PixelO(PixelO obj) {
        setRed(obj.getRed());
        setGreen(obj.getGreen());
        setBlue(obj.getBlue());
    }

    @Override
    public String toString() {
        return "[" + (int)red + "," + (int)green + "," + (int)blue + "]";
    }
}
