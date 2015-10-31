/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.awt.event.MouseEvent;


public class MouseMoved  extends monopoly.project.MonopolyProject implements Runnable
{
    public static void Moved(MouseEvent e)
    {
        if(!gameStart)
        {
              int xdelta = getWidth2()/numColumns*2;
              int ydelta = getHeight2()/numRows*2+30;
              int xpos = e.getX() - getX(0);
              int ypos = getYNormal(e.getY() - getY(0))+60;
              int column = xpos/(xdelta/2);
              int row = ypos/(ydelta/2);

              if(xpos>getWidth2()/2&&xpos<getWidth2()/2+StartGameS.length()*25
              &&ypos<453&&ypos>413)
                  StartGameH=true;
              else
                  StartGameH=false;
              if(xpos>getWidth2()/2&&xpos<getWidth2()/2+SettingsS.length()*25
              &&ypos<407&&ypos>362)
                  SettingsH=true;
              else
                  SettingsH=false;
              if(xpos>getWidth2()/2&&xpos<getWidth2()/2+HelpS.length()*25
              &&ypos<344&&ypos>302)
                  HelpH=true;
              else
                  HelpH=false;
              if(xpos>getWidth2()/2&&xpos<getWidth2()/2+TutorialS.length()*25
              &&ypos<283&&ypos>249)
                  TutorialH=true;
              else
                  TutorialH=false;
              if(xpos>getWidth2()/2&&xpos<getWidth2()/2+ExitS.length()*25
              &&ypos<233&&ypos>190)
                  ExitH=true;
              else
                  ExitH=false;
        }
    }
}
