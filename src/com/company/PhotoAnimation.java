package com.company;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PhotoAnimation {
    private List<BufferedImage> photos;
    private int photoIndex =0;
    private int delay;      //opoznienie odswiezania zdj
    private long previousTime;


    public PhotoAnimation(int delay){
        this.delay = delay;
        photos = new ArrayList<BufferedImage>();  //tworze liste zdjec
    }

    public void update(){
        if(System.currentTimeMillis()-previousTime >delay) { //  metoda do opozniania zdj
            photoIndex++;
            if (photoIndex >= photos.size()) {
                photoIndex = 0;
            }
            previousTime = System.currentTimeMillis();
        }
    }

    public void addPhoto(BufferedImage photo){
        photos.add(photo);                  //dodaje zdjecia do listy
    }

    public BufferedImage getPhoto(){            //geter zdj
        if(photos.size()>0){
            return photos.get(photoIndex);
        }
        return null;
    }

}
