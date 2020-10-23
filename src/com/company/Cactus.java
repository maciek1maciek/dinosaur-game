package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy {

    private BufferedImage image;
    private int posX,posY;
    private Rectangle rect;
    private MainCharacter mainCharacter;
    private boolean isScoreGot = false;

    public Cactus(MainCharacter mainCharacter) {
        this.mainCharacter=mainCharacter;
        image =Image.getImage("data/cactus1.png"); //rysowanie kaktusa
        posX=200;
        posY= 65;
        rect = new Rectangle();
    }

    public void update(){
        posX-=2;
        rect.x=posX;        //ustawiam pozycje rectangle taka jaka ma kaktus
        rect.y=posY;
        rect.width = image.getWidth();
        int right = rect.x+rect.width;
        rect.height= image.getHeight();
    }

    @Override
    public boolean outOfScreen() {
        return (posX+image.getWidth()<0);       // kiedy kaktus jest poza ekranem
    }

    @Override
    public boolean isOver() { //kiedy zdj na siebie najadą
        return (mainCharacter.getX()>posX);
    }

    @Override
    public boolean isScoreGot() { //zdobycie punktu kiedy minie przeszkode
        return isScoreGot;
    }

    @Override
    public void setIsScoreGot(boolean isScoreGot) {  //seter do ustawiania pkt
        this.isScoreGot =isScoreGot;
    }

    @Override
    public Rectangle getBound(){
        return rect;
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(image,posX,posY,null);

    }

    public void setX(int posX) {
        this.posX = posX;
    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
