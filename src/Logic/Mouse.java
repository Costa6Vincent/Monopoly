/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.awt.event.MouseEvent;

/**
 *
 * @author VincentCoosta
 */
public class Mouse extends monopoly.project.MonopolyProject implements Runnable
{
    public void button(MouseEvent e) 
    {
        if (MouseEvent.BUTTON3 == e.getButton()){
            reset();
        }

        if (MouseEvent.BUTTON1 == e.getButton()){

            Logic.LeftClick.Click1(e);


        }

        repaint();
    }
    
    
    
    
}
