/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.awt.event.MouseEvent;
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
                }
                if(currentProperty.getThePlayer()==null&&currentProperty.getThePlayer()!=currentPerson)
                {
                    payRent=false;
                    attack=false;
                }
                else
                {
                    payRent=true;
                    attack=true;
                }
                if(currentProperty.getThePlayer()==currentPerson)
                {
                    upgrade=true;
                    payRent=false;
                }
                else
                    upgrade=false;
                if(currentProperty.getThePlayer()==null&&currentProperty.getCanPurchase())
                {
                    purchase=true;
                }
                else
                    purchase=false;
                
//                if(!currentProperty.getCanPurchase())
//                {
//                    nextTurn();
//                }
                if(currentPerson.getMoney()>=250&&canUpgrade)
                {
                    upgradeArmy=true;
                }
                else
                {
                    upgradeArmy=false;
                    canUpgrade=false;
                }
                if(currentProperty.getName().contentEquals("Good"))
                {
                    drawGoodCard=true;
                    purchase=false;
                    attack=false;
                    payRent=false;
                }
                if(currentProperty.getName().contentEquals("Bad"))
                {
                    drawBadCard=true;
                    purchase=false;
                    attack=false;
                    payRent=false;
                }
                diceRoll=false;
                decision=true;
            }
        }
        else if(decision)
        {
            
            Property currentProperty=property[players[currentPlayer].getY()][players[currentPlayer].getX()];
            Player currentPerson=players[currentPlayer];
            if(currentPerson.getMoney()>=250&&canUpgrade)
            {
                upgradeArmy=true;
            }
            else
            {
                upgradeArmy=false;
                canUpgrade=false;
            }
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
                
            }
            if(!payRent)
            {
                if(xpos>=endTurnX&&xpos<endTurnX+endTurnLength&&ypos>endTurnY-YTITLE&&ypos<endTurnY+endTurnHeight-YTITLE)
                {
                    nextTurn();
                }
            }
            if(upgrade&&xpos>=upgradeX&&xpos<upgradeX+purchaseLength&&ypos>upgradeY-YTITLE&&ypos<upgradeY+purchaseHeight-YTITLE)
            {
                currentProperty.addUpgrade();
                nextTurn();
            }
            if(currentPerson.getNumProperty()>=1)
            {
                for(int index=0;index<currentPerson.getNumProperty();index++)
                {
                    if(xpos>=boardAlloc+50&&xpos<=boardAlloc+100&&ypos>YTITLE*3+index*30&&ypos<(YTITLE*3*(index))+index*30)
                    {
                        currentPerson.setMoney(currentPerson.getMoney()+currentPerson.getPropertyOwnership(index).getCost());
                        currentPerson.getPropertyOwnership(index).setThePlayer(null);
                        currentPerson.setNumProperty(currentPerson.getNumProperty()-1);
                        nextTurn();
                    }
                    //g.drawString(text2, boardAlloc, YTITLE*3+index*30); 
                }
            }
            if(currentPerson.getMoney()>=currentProperty.getCost()&&currentProperty.getThePlayer()!=null&&currentProperty.getThePlayer()!=currentPerson)
            {
                if(payRent&&xpos>=payX&&xpos<payX+attackLength&&ypos>payY-YTITLE&&ypos<payY+payHeight-YTITLE)
                {
                    Player playerOwner=currentProperty.getThePlayer();
                    playerOwner.setMoney(playerOwner.getMoney()+currentProperty.getRent());
                    currentPerson.setMoney(currentPerson.getMoney()-currentProperty.getRent());
                    nextTurn();
                }
            }
            if(attack&&xpos>=attackX&&xpos<attackX+attackLength&&ypos>attackY-YTITLE&&ypos<attackY+attackHeight-YTITLE)
            {
              if(currentPerson.getTheArmy()!=null)
              {
                currentPerson.getTheArmy().determineWin(currentProperty.getDefense());
              }
            }
            if(canUpgrade&&upgradeArmy&&xpos>=upgradeArmyX&&xpos<upgradeArmyX+upgradeArmyLength&&ypos>upgradeArmyY-YTITLE&&ypos<upgradeArmyY+upgradeArmyHeight-YTITLE)
            {
              currentPerson.setMoney(currentPerson.getMoney()-250);
              currentPerson.getTheArmy().setCurrentArmyPower(currentPerson.getTheArmy().getCurrentArmyPower()+250);
              canUpgrade=false;
            }
//            for(int index=0;index<players[currentPlayer].getNumProperty();index++)
//            {
//                if(xpos>&&xpos< +&&ypos>&&ypos< +)
//                    players[currentPlayer].deleteProperty(players[currentPlayer].getPropertyOwnership(players[currentPlayer].getNumProperty()-index));
//            }


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
        canUpgrade=true;
        return;
    }
}
