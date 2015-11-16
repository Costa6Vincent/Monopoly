/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import static monopoly.project.MonopolyProject.*;

/**
 *
 * @author VincentCoosta
 */
public class Settings
{
    public static void drawSettingsWindow(Graphics2D g)
    {
        
        if(soundsOn)
            g.setColor(Color.yellow);
        else
            g.setColor(Color.black);
        g.setFont(new Font("Impact",Font.ITALIC,50));
        String text2 = "Sounds";
        g.drawString(text2, SoundsX, SoundsY); 
        
        if(musicOn)
            g.setColor(Color.yellow);
        else
            g.setColor(Color.black);
        text2 = "Music";
        g.drawString(text2, MusicX, MusicY); 
        
    }
}
