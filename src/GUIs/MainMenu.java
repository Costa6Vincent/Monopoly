/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import java.awt.Color;
import java.awt.Graphics2D;
import monopoly.project.MonopolyProject;
import static monopoly.project.MonopolyProject.XBORDER;
import static monopoly.project.MonopolyProject.YTITLE;
import static monopoly.project.MonopolyProject.gameStart;
import static monopoly.project.MonopolyProject.getHeight2;
import static monopoly.project.MonopolyProject.getWidth2;
import static monopoly.project.MonopolyProject.mScale;
import static monopoly.project.MonopolyProject.sRotation;
import static monopoly.project.MonopolyProject.sScale;
import static monopoly.project.MonopolyProject.startMenu;

/**
 *
 * @author VincentCoosta
 */
public class MainMenu 
{
    public static void drawMainMenu(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale,MonopolyProject nice  )
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        if(!gameStart)
        {
            g.setColor(Color.white);
            g.fillRect(0,0,getWidth2()+XBORDER+YTITLE,getHeight2()+XBORDER+YTITLE);
            GUIs.SplashScreen.drawSplashScreen(g,getWidth2()/2,getHeight2()/2,360-sRotation,.1+sScale,.05+sScale,nice);
        }
        if(startMenu)
        {
            GUIs.SplashScreenMenu.drawSplashScreenMenu(g,getWidth2()/2,getHeight2()*8/12,0,.1+mScale,.05+mScale);
        }
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
}
