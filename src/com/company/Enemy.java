package com.company;

import java.awt.*;

public abstract class Enemy {
    public abstract Rectangle getBound ();
    public abstract void draw(Graphics g);  //ogolna draw
    public abstract void update();  //ogolna update
    public abstract boolean outOfScreen(); //metoda gdy jest poza ekranem
    public abstract boolean isOver();   //kiedy zdjecia na siebie najada
    public abstract boolean isScoreGot(); //gdy zdobedzie punkt
    public abstract void setIsScoreGot(boolean isScoreGot); //gdy zdobedzie punkt


}
