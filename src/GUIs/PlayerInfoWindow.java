
package GUIs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import static monopoly.project.MonopolyProject.decision;
import static monopoly.project.MonopolyProject.payHeight;
import static monopoly.project.MonopolyProject.payLength;
import static monopoly.project.MonopolyProject.payRent;
import static monopoly.project.MonopolyProject.payX;
import static monopoly.project.MonopolyProject.payY;
import static monopoly.project.MonopolyProject.purchaseHeight;
import static monopoly.project.MonopolyProject.purchaseLength;
import static monopoly.project.MonopolyProject.purchaseX;
import static monopoly.project.MonopolyProject.purchaseY;

public class PlayerInfoWindow extends monopoly.project.MonopolyProject
{
    public static void drawInfoWindow(Graphics2D g)
    {
        if(decision)
            g.setColor(Color.white);
        else
        {
            Color color2 = new Color(Color.red.getRed()/255,Color.red.getGreen()/255,Color.red.getBlue()/255,.3f);
            g.setColor(color2);
        }
        g.setFont(new Font("Impact",Font.ITALIC,40));
        String text2 = null;
        if(decision)
        {
            if(purchase)
            {
                g.fillRect(purchaseX, purchaseY, purchaseLength, purchaseHeight);
                text2 = "Purchase";
                g.setColor(Color.red);
                g.drawString(text2, purchaseX, purchaseY+purchaseHeight*3/4); 
            }
            if(payRent)
            {
                g.fillRect(payX, payY, payLength, payHeight);
                text2 = "Pay Rent";
                g.setColor(Color.red);
                g.drawString(text2, payX, payY+payHeight*3/4); 
            }

        }
        else
        {
            text2 = "Roll the die";
            g.setColor(Color.red);
            g.drawString(text2, purchaseX, purchaseY+purchaseHeight*3/4); 
        }
        if(players[currentPlayer].getInJail())
        {
            g.fillRect(payX, 100, payLength, payHeight);
            text2 = "In Jail";
            g.setColor(Color.red);
            g.drawString(text2, payX, payY+payHeight*3/4); 
        }
        
    }
    
}
