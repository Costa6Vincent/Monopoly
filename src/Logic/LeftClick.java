/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import monopoly.project.*;


/**
 *
 * Code for a Left Click
 */
public class LeftClick extends Mouse implements Runnable
{
    public static void Click1(MouseEvent e)
    {
        if(!gameStart)
        {
            if(StartGameH)
            {
                startMenuAnim=true;
            }
            if(HelpH)
            {
//                String url = "";
//                File htmlFile = new File(url);
//                Desktop.getDesktop().browse(htmlFile.toURI());
            }
            if(ExitH)
            {
                System.exit(0);
            }
        }
        if(players[currentPlayer].getInJail())
        {
            players[currentPlayer].setInJail(false);
            nextTurn();
            return;
        }
        int numberOfSquaresNeededToMove=0;
        int xdelta = getWidth2()/numColumns*2;
        int ydelta = getHeight2()/numRows*2;
        int xpos = e.getX() - getX(0);
        int ypos = e.getY() - getY(0);
        int column = xpos/(xdelta/2);
        int row = ypos/(ydelta/2);
        if(diceRoll)
        {
            if((diceRow1==row&&diceColumn1==column)||(diceRow2==row&&diceColumn2==column))
            {
                dice=new Dice();
                dice2=new Dice();
                numberOfSquaresNeededToMove=Dice.addSides();
                Player currentPerson=players[currentPlayer];
                while(numberOfSquaresNeededToMove>=1)
                {
                    Property currentProperty=property[players[currentPlayer].getY()][players[currentPlayer].getX()];
                    if(players[currentPlayer].getX()<numColumns-1&&players[currentPlayer].getY()==0)
                    {
                        players[currentPlayer].setX(players[currentPlayer].getX()+1);
                        numberOfSquaresNeededToMove--;
                    }
                    else if(players[currentPlayer].getX()==numColumns-1&&players[currentPlayer].getY()<=numRows-2)
                    {
                        players[currentPlayer].setY(players[currentPlayer].getY()+1);
                        numberOfSquaresNeededToMove--;
                    }
                    else if(players[currentPlayer].getX()>=1&&players[currentPlayer].getY()<=numRows-1)
                    {
                        players[currentPlayer].setX(players[currentPlayer].getX()-1);
                        numberOfSquaresNeededToMove--;
                    }
                    else if(players[currentPlayer].getX()==0&&players[currentPlayer].getY()>=0)
                    {
                        players[currentPlayer].setY(players[currentPlayer].getY()-1);
                        numberOfSquaresNeededToMove--;
                    }
                    else
                        numberOfSquaresNeededToMove--;
                    if(players[currentPlayer].getY()==numRows-1&&players[currentPlayer].getX()==numColumns-1)
                    {
                        players[currentPlayer].setMoney(players[currentPlayer].getMoney()+200);
                    }
                    

                }
                Property currentProperty=property[players[currentPlayer].getY()][players[currentPlayer].getX()];
                if(currentProperty.getIsJail())
                {
                    currentPerson.setInJail(true);
                    currentPerson.setX(0);
                    currentPerson.setY(10);
                    nextTurn();
                    return;
                }
                if(currentProperty.getThePlayer()==null&&currentProperty.getThePlayer()!=currentPerson)
                {
                    payRent=false;
                }
                else
                    payRent=true;
                if(currentProperty.getThePlayer()==currentPerson)
                {
                    upgrade=true;
                }
                else
                    upgrade=false;
                if(currentProperty.getThePlayer()==null&&currentProperty.getCanPurchase())
                {
                    purchase=true;
                }
                else
                    purchase=false;
                
                if(!currentProperty.getCanPurchase())
                {
                    nextTurn();
                    return;
                }
                diceRoll=false;
                decision=true;
            }
        }
        else if(decision)
        {
            Property currentProperty=property[players[currentPlayer].getY()][players[currentPlayer].getX()];
            Player currentPerson=players[currentPlayer];

            if(currentProperty.getThePlayer()==null&&currentPerson.getMoney()>=currentProperty.getCost()&&currentProperty.getCanPurchase())
            {
                if(xpos>=purchaseX&&xpos<purchaseX+purchaseLength&&ypos>purchaseY-YTITLE&&ypos<purchaseY+purchaseHeight-YTITLE)
                {
                    currentProperty.addPlayer(currentPerson);
                    currentProperty.addUpgrade();
                    currentPerson.setMoney(currentPerson.getMoney()-currentProperty.getCost());
                    currentPerson.getAllProperties();
                    nextTurn();
                }
                if(xpos>=endTurnX&&xpos<endTurnX+purchaseLength&&ypos>endTurnY-YTITLE&&ypos<endTurnY+purchaseHeight-YTITLE)
                {
                    nextTurn();
                }
            }
            else if(upgrade&&xpos>=upgradeX&&xpos<upgradeX+purchaseLength&&ypos>upgradeY-YTITLE&&ypos<upgradeY+purchaseHeight-YTITLE)
            {
                currentProperty.addUpgrade();
                nextTurn();
            }
            else if(currentPerson.getMoney()>=currentProperty.getCost()&&currentProperty.getThePlayer()!=null&&currentProperty.getThePlayer()!=currentPerson)
            {
                Player playerOwner=currentProperty.getThePlayer();
                playerOwner.setMoney(playerOwner.getMoney()+currentProperty.getRent());
                currentPerson.setMoney(currentPerson.getMoney()-currentProperty.getRent());
                nextTurn();
            }


        }
    }
    public static void nextTurn()
    {
        players[currentPlayer].setIsTurn(false);
        currentPlayer++;
        if(currentPlayer>=numPlayers)
            currentPlayer=0;
        players[currentPlayer].setIsTurn(true);
        decision=false;
        diceRoll=true;
        turnCount++;
        xAnim=0;
        yAnim=0;
        turnAnimation=true;
    }
}
