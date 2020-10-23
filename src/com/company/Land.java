package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static com.company.GameScreen.GROUND;

public class Land {

    private ArrayList<ImageLand> listImage;
    private BufferedImage land1,land2,land3;
    private Random random;


    public Land(GameScreen game) {
        random = new Random();
        land1 = Image.getImage("data/land1.png");
        land2 = Image.getImage("data/land2.png");
        land3 = Image.getImage("data/land3.png");


        listImage = new ArrayList<ImageLand>();     //tworze liste zdjec
        int numberOfLand = 3840/land1.getWidth(); //rozmiar podloza zeby do w liscie okreslic ile podloze ma sie renderowac

        for(int i=0;i<numberOfLand;i++){
            ImageLand imageLand = new ImageLand();          //tworze obiekt klasy prywatnej
            imageLand.posX= (int)(i*land1.getWidth());      //przypisuje wartosc do posX
            imageLand.image= getImageLand();
            listImage.add(imageLand);       //dodaje zdjecia do tablicy
        }
    }

    public void update(){               //metoda ktora przesuwa ląd
        for(ImageLand imageLand:listImage){
            imageLand.posX--;
        }
        ImageLand firstElement = listImage.get(0);
        if(listImage.get(0).posX+land1.getWidth()<0){   //przesuwanie nieksonczone podloza
            firstElement.posX = listImage.get(listImage.size()-1).posX+land1.getWidth();
            listImage.add(listImage.get(0));        //dodaje pierwsze zdj
            listImage.remove(0);    //usuwa ostatnie zdj ktore wyszlo poza plansze
        }
    }


    public void draw(Graphics g) {
        for(ImageLand imageLand: listImage){
            g.drawImage(imageLand.image,imageLand.posX,(int)GROUND-20,null);    //rysowanie landu
        }
    }

   private BufferedImage getImageLand(){
       int i = random.nextInt(3);       //wybieranie randomowego landu
        switch (i){
            case 0: return land1;
            case 1: return land2;
             default: return land3;
        }
    }


    private class ImageLand{
        int posX;               //ustawianie pozycji landu
        BufferedImage image;
    }


}
