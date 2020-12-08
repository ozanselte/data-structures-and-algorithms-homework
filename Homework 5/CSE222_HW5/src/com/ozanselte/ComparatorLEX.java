package com.ozanselte;

import java.util.Comparator;

public class ComparatorLEX implements Comparator<PixelO> {

    private static int rgb2int(PixelO px) {
        int rgb = px.getRed();
        rgb <<= 8;
        rgb |= px.getGreen();
        rgb <<= 8;
        rgb |= px.getBlue();
        return rgb;
    }

    @Override
    public int compare(PixelO o1, PixelO o2) {
        return rgb2int(o1) - rgb2int(o2);
    }
}
