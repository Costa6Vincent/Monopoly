/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import java.awt.Color;
import java.awt.Graphics2D;
import monopoly.project.MonopolyProject.*;

/**
 *
 * @author VincentCoosta
 */
public class SplashScreenMenu extends monopoly.project.MonopolyProject implements Runnable 
{
    public static void drawSplashScreenMenu(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color black=Color.black;
        Color selected=Color.yellow;
        
        
        g.setColor(black);
        String nice=StartGameS;
        g.setFont(font);
        if(StartGameH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0+xPosStart,  (int)(yscale*yscale));
        nice=SettingsS;
        if(SettingsH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0,  (int)(yscale*yscale)*7);
        nice=HelpS;
        if(HelpH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0,  (int)(yscale*yscale)*14);
        nice=TutorialS;
        if(TutorialH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0,  (int)(yscale*yscale)*21);
        nice=ExitS;
        if(ExitH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0,  (int)(yscale*yscale)*28);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    
}
