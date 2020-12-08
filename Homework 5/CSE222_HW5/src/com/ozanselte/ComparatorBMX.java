package com.ozanselte;

import java.util.Comparator;

public class ComparatorBMX implements Comparator<PixelO> {

    private static int rgb2int(PixelO px) {
        int rgb = 0;
        char r = px.getRed();
        char g = px.getGreen();
        char b = px.getBlue();
        for(int i = 7; i >= 0; i--) {
            int one = 1 << i;
            rgb <<= 1;
            rgb |= ((r & one) >> i);
            rgb <<= 1;
            rgb |= ((g & one) >> i);
            rgb <<= 1;
            rgb |= ((b & one) >> i);
        }
        return rgb;
    }

    @Override
    public int compare(PixelO o1, PixelO o2) {
        return rgb2int(o1) - rgb2int(o2);
    }
}
