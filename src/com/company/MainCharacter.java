package com.company;

import java.awt.*;
import static com.company.GameScreen.GRAVITY;
import static com.company.GameScreen.GROUND;

public class MainCharacter {
    private float x=0;
    private float y=0;
    private float speedY=0;

    private PhotoAnimation characterRun;
    private Rectangle rect;

    private boolean isAlive=true;


    public MainCharacter(){
        characterRun = new PhotoAnimation(100); //opoznienie odswierzania zdjec
        characterRun.addPhoto(Image.getImage("data/main-character1.png")); //dodanie zdjecia do listy z danej sciezki
        characterRun.addPhoto(Image.getImage("data/main-character2.png")); //dodanie zdjecia do listy z danej sciezki
        rect = new Rectangle();
    }

   public void update(){
        characterRun.update();
       if(y>=GROUND-characterRun.getPhoto().getHeight()){   //opadanie kwadratu do granicy tutaj jezeli opadnie
           speedY =0;
           y=GROUND-characterRun.getPhoto().getHeight();
       }else {
           speedY += GRAVITY;  //tutaj jezeli klikne to sie unosi
           y += speedY;
       }
       rect.x =(int)x;
       rect.y =(int)y;
       rect.width = characterRun.getPhoto().getWidth();
       rect.height = characterRun.getPhoto().getHeight();
   }

   public Rectangle getBound(){
        return rect;
   }

   public void draw(Graphics g){
       g.setColor(Color.BLACK); //kolor prostokata
      // g.drawRect((int)x,(int)y,characterRun.getPhoto().getWidth(),characterRun.getPhoto().getHeight()); //rysuje prostokat
       g.drawImage(characterRun.getPhoto(),(int)x,(int)y,null);  //rysuje zdiecie w prostokacie
   }

   public void jump(){
       speedY=-4;        //gdy wcisne przycisk z klaw
       y+=speedY;
   }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }


    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getAlive() {
        return isAlive;
    }
}
