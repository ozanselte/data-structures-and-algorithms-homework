package com.ozanselte;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        String imagePath = args[0].trim();
        PriorityQueueO pqLEX = new PriorityQueueO(new ComparatorLEX());
        PriorityQueueO pqEUC = new PriorityQueueO(new ComparatorEUC());
        PriorityQueueO pqBMX = new PriorityQueueO(new ComparatorBMX());
        PQThread thLEX = new PQThread("Thread2-PQLEX", pqLEX);
        PQThread thEUC = new PQThread("Thread3-PQEUC", pqEUC);
        PQThread thBMX = new PQThread("Thread4-PQBMX", pqBMX);
        try {
            File imageFile = new File(imagePath);
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            int w = bufferedImage.getWidth();
            int h = bufferedImage.getHeight();
            for(int i = 0; i < w; i++) {
                for(int j = 0; j < h; j++) {
                    Color c = new Color(bufferedImage.getRGB(i, j));
                    PixelO pixel = new PixelO(c.getRed(), c.getGreen(), c.getBlue());
                    System.out.println("Thread 1: " + pixel.toString());
                    synchronized(thLEX) {
                        pqLEX.add(pixel);
                        thLEX.notify();
                    }
                    synchronized(thEUC) {
                        pqEUC.add(pixel);
                        thEUC.notify();
                    }
                    synchronized(thBMX) {
                        pqBMX.add(pixel);
                        thBMX.notify();
                    }
                    if(100 == i*h + j) {
                        thLEX.start();
                        thEUC.start();
                        thBMX.start();
                    }
                }
            }
            thLEX.close();
            thEUC.close();
            thBMX.close();
            System.out.println("FINISHED");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
