/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 *
 * @author VincentCoosta
 */
public class SplashScreen extends monopoly.project.MonopolyProject
{
    public static void drawSplashScreen(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        
        g.setColor(Color.red);
        g.fillRect(-150, -50, 360, 100);
        g.setColor(Color.black);
        Stroke nice = g.getStroke();
        g.setStroke(new BasicStroke(3.0f));
        g.drawRect(-150, -50, 360, 100);
        g.setStroke(nice);
        g.setFont(font2);
        String red = "Monopoly";
        nice = g.getStroke();
        g.setStroke(new BasicStroke(4.0f));
        g.setColor(Color.black);
        g.drawString(red, -150,  25);
        g.setStroke(nice);
        g.setFont(font2);
        g.setColor(Color.white);
        g.drawString(red, -150+3,  25-3);
        
        red="War";
        g.setFont(font3);
        g.drawString(red, 90,  45);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    
}
