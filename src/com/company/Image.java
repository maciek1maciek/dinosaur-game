package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    public static BufferedImage getImage(String path){
        BufferedImage img =null;

        try {
            img = ImageIO.read(new File(path)); //proboje odczytac plik jezeli nie odczyta rzucony bedzie exception
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
