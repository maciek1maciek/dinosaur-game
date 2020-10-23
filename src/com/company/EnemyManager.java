package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {

    private ArrayList<Enemy> enemies;
    private Random random;
    private BufferedImage cactus1,cactus2;
    private MainCharacter mainCharacter;
    private GameScreen gameScreen; //do zliczania punktow


    public EnemyManager(MainCharacter mainCharacter,GameScreen gameScreen) {
        this.gameScreen=gameScreen;
        this.mainCharacter =mainCharacter;
        enemies = new ArrayList<Enemy>();

        cactus1 = Image.getImage("data/cactus1.png");
        cactus2 = Image.getImage("data/cactus2.png");
        random = new Random();

        enemies.add(getRandom());
    }

    public void update(){
        for(Enemy e :enemies){
            e.update();
            if(e.isOver() && !e.isScoreGot()){
                gameScreen.setScore(20);
                e.setIsScoreGot(true);
            }
            if(e.getBound().intersects(mainCharacter.getBound())){          //kiedy jest kolizja z glowna postacia do tego sluzy intersects
                mainCharacter.setAlive(false);
            }
        }
        Enemy firstEnemy = enemies.get(0);
        if(enemies.get(0).outOfScreen()){   //jezeli kaktus zniknie z ekranu to usuwa o z lsity i dodaje randomowego
            enemies.remove(firstEnemy);  //usuwa tego kaktusa ktory wyszedl poza ekran
            enemies.add(getRandom());       //dodaje randomowego kaktusa
        }

    }

    public void draw(Graphics g){       //rysuje tablice kaktusow
        for(Enemy e :enemies){
            e.draw(g);
        }
    }

    public void reset(){
        enemies.clear();
        enemies.add(getRandom());
    }

    private Cactus getRandom(){     //daje ranodmowego kaktusa
        Cactus cactus;
        cactus = new Cactus(mainCharacter);
        cactus.setX(600);           //ustawiam odlegosc w (x) gdzie kaktus ma sie renderowac
        if(random.nextBoolean()){
            cactus.setImage(cactus1);
        }else {
            cactus.setImage(cactus2);
        }
        return cactus;
    }

}
