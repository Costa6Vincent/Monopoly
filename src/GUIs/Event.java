/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import java.awt.Graphics2D;

/**
 *
 * @author 373000781
 */
public class Event extends monopoly.project.MonopolyProject
{
    static String text;
    public static int stringX;
    public static int stringY;
    public static boolean stringmove;
    public static void drawEvents(Graphics2D g)
    {
        gameStart=false;
        if(true)
        {
            g.setFont(font);
            text="BATTLE LOST!!!!";
            g.drawString(text,getStringX(),getStringY());
        }
        if(true)
        {
            g.setFont(font);
            text = "BATTLEWON!!!!";
            g.drawString(text,getStringX(),getStringY());
        }
    }

    public static String getText() {
        return text;
    }

    public static int getStringX() {
        return stringX;
    }

    public static int getStringY() {
        return stringY;
    }

    public static boolean isStringmove() {
        return stringmove;
    }

    public static void setText(String text) {
        Event.text = text;
    }

    public static void setStringX(int stringX) {
        Event.stringX = stringX;
    }

    public static void setStringY(int stringY) {
        Event.stringY = stringY;
    }

    public static void setStringmove(boolean stringmove) {
        Event.stringmove = stringmove;
    }
    
}
