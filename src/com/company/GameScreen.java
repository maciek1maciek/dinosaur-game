package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameScreen extends JPanel implements Runnable, KeyListener {
     public static final float GRAVITY=0.1f;
     public static final float GROUND=110; //pixeli
     public static final int GAME_FIRST_STATE=0;
     public static final int GAME_PLAY_STATE=1;
     public static final int GAME_OVER_STATE=2;

    private MainCharacter character;
    private Thread thread;
    private Land land;
    private EnemyManager enemyManager;
    private int score;

    private int gameState=GAME_FIRST_STATE;
    private BufferedImage imageGameOver;

    ArrayList<Integer>pointsList;

    public GameScreen() {
        thread = new Thread(this);   //tworze watek
        character = new MainCharacter();
        land = new Land(this);
        enemyManager = new EnemyManager(character,this);
        imageGameOver = Image.getImage("data/gameover_text.png");
        pointsList = new ArrayList<Integer>();
    }

    public void startGame(){
        thread.start();             //metoda do startowanai watku
    }


    @Override
    public void run() {         //uruchomienie watku
        while(true){
            try {
                repaint();
                update();
                Thread.sleep(10); //uspienie watku na 20 i metoda try catch
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){ //metoda ktroa updateuje np przesuwanie ladu
        switch (gameState){
            case GAME_PLAY_STATE:{
                character.update();     //zdjecie dinozaura
                land.update();
                enemyManager.update();
                if(!character.getAlive()){
                    gameState =GAME_OVER_STATE;
                }
                break;
            }

        }

    }


    public void setScore(int score){        //liczenie punktow
        this.score += score;
    }

    public void paint(Graphics g){
        super.paint(g); //zeby tło się dobrze wczytało bez tego mogą być błedy np: źle pokaze konsole figura zamazuje pole swoje
        g.setColor(Color.decode("#f7f7f7")); //kolor tla dlatego ze na poczatku wszystko jest czerowone a fillrect ustawiwa ze to jest tlo a potem prostokat jest czarny
        g.fillRect(0,0,getWidth(),getHeight()); //maluje tlo
      //  g.setColor(Color.RED);
       // g.drawLine(0,(int)GROUND,getWidth(),(int)GROUND);
        switch (gameState){
            case GAME_FIRST_STATE:              //pierwsza faza gry
                    character.draw(g);
                    break;
            case GAME_PLAY_STATE:       //podczas gry
                     land.draw(g);
                     character.draw(g);
                     enemyManager.draw(g);
                     g.drawString(String.valueOf(score),getWidth()/2+200,30);     //rysowanie wyniku
                     g.drawString("Pkt: ",getWidth()/2+180,30);    //rysowanie wyniku
                     break;
             case GAME_OVER_STATE://gdy gra sie zakonczy
                     land.draw(g);
                     character.draw(g);
                     enemyManager.draw(g);
                     g.drawImage(imageGameOver,getWidth()/2-imageGameOver.getWidth()/2,getHeight()/2,null);
                     g.drawString("Wynik: "+score+" punktów",getWidth()/2-50,getHeight()/2+40);
                     break;

        }
    }

    private void restartGame(){     //metoda restartujaca gre
        character.setAlive(true);
        character.setX(50); //odleglosc dinozaura od lewej strony
        character.setY(60);
        enemyManager.reset();
    }
/*
    public void addScore(int score){
        pointsList.add(score);
        Collections.reverse(pointsList);
        System.out.println(pointsList);
    }
*/

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:         //gdy nacisne spacje to wykona dzialania
                if (gameState == GAME_FIRST_STATE) {
                    gameState = GAME_PLAY_STATE;
                } else if (gameState == GAME_PLAY_STATE) {
                    character.jump();
                } else if (gameState == GAME_OVER_STATE) {
                    gameState = GAME_PLAY_STATE;
                    restartGame();
                }
                break;
        }
    }
}
