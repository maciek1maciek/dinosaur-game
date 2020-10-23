package com.company;

import javax.swing.*;


public class GameWindow extends JFrame {

    private GameScreen gameScreen;

    public GameWindow(){
        setTitle("Dinozaur");
        setSize(600,195);//tworzenie okna
        setLocation(660,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        gameScreen = new GameScreen();  //tworzy obiekrt klasy
        add(gameScreen);// dodaje obiekt do okna aplikacji
        addKeyListener(gameScreen); //dodaje listenera na przycisk z klawiatury
    }

    public void startGame(){
        gameScreen.startGame(); //start gry
    }

}

