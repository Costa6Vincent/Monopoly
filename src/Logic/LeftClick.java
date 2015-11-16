/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.awt.event.MouseEvent;
import monopoly.project.*;
import monopoly.project.MonopolyProject.*;


/**
 *
 * Code for a Left Click
 */
public class LeftClick extends Mouse implements Runnable
{
    public static void Click1(MouseEvent e)
    {
        int numberOfSquaresNeededToMove=0;
        int xdelta = getWidth2()/numColumns*2;
        int ydelta = getHeight2()/numRows*2;
        int xpos = e.getX() - getX(0);
        int ypos = e.getY() - getY(0);
        int column = xpos/(xdelta/2);
        int row = ypos/(ydelta/2);
        if(!gameStart)
        {
            if(!settings)
            {
                if(StartGameH)
                {
                    startMenuAnim=true;
                }
                if(HelpH)
                {
    //                
                }
                if(SettingsH)
                {
                    settings=true;
                }
                if(ExitH)
                {
                    System.exit(0);
                }
                if(AIH)
                {
                    AIOn=true;
                    startMenuAnim=true;
                }
                startMenuAnim=true;
            }
            if(HelpH)
            {
//                String url = "";
//                File htmlFile = new File(url);
//                Desktop.getDesktop().browse(htmlFile.toURI());
                if(!helpMenuActive)
                    helpMenuActive = true;
                
                if(currentHelpPage==0)
                    currentHelpPage = 1;
                else if(currentHelpPage==1)
                    currentHelpPage = 2;

            }
            if(ExitH)
            {
                System.exit(0);
            }
            else if(settings)
            {
                if(xpos>SoundsX&&xpos<SoundsX+40
                &&ypos<SoundsY&&ypos>SoundsY-40)
                {
                    soundsCounter++;
                    if(soundsCounter%2==0)
                        soundsOn=true;
                    else
                        soundsOn=false;
                }
            }
        }
        if(players[currentPlayer].getInJail())
        {
            players[currentPlayer].setInJail(false);
            nextTurn();
        }
        
        if(diceRoll&&(diceRow1==row&&diceColumn1==column)||(diceRow2==row&&diceColumn2==column))
        {
            
            movePlayer();
            diceRoll=false;
            dicerollN=true;
            decision=true;
            return;
        }
        if(decision)
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
            if(currentProperty.getThePlayer()==null&&currentProperty.getCanPurchase())
            {
                if(xpos>=purchaseX&&xpos<purchaseX+purchaseLength&&ypos>purchaseY-YTITLE&&ypos<purchaseY+purchaseHeight-YTITLE)
                {
                    currentProperty.addPlayer(currentPerson);
                    currentProperty.addUpgrade();
                    currentPerson.setMoney(currentPerson.getMoney()-currentProperty.getCost());
                    currentPerson.getAllProperties();
                    purchaseN=true;
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
                boolean win = currentPerson.getTheArmy().determineWin(currentProperty.getDefense());
                if(win)
                {
                    currentProperty.setThePlayer(currentPerson);
                    currentPerson.addProperty(currentProperty);
                }
                warH=true;
                nextTurn();
              }
            }
            if(canUpgrade&&upgradeArmy&&xpos>=upgradeArmyX&&xpos<upgradeArmyX+upgradeArmyLength&&ypos>upgradeArmyY-YTITLE&&ypos<upgradeArmyY+upgradeArmyHeight-YTITLE)
            {
              currentPerson.setMoney(currentPerson.getMoney()-500);
              currentPerson.getTheArmy().setCurrentArmyPower(currentPerson.getTheArmy().getCurrentArmyPower()+100);
              upgradeArmyH=true;
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
        if(!players[currentPlayer].getInGame())
            nextTurn();
        if(!AIOn)
        {
            players[currentPlayer].setIsTurn(true);
            decision=false;
            diceRoll=true;
            turnCount++;
            xAnim=0;
            yAnim=0;
            turnAnimation=true;
            canUpgrade=true;
        }
        if(AIOn&&currentPlayer!=0)
        {
            diceRoll=true;
            movePlayer();
            monopoly.project.MonopolyProject.AITurn();
            nextTurn();
            return;
        }
        turns++;
        return;
        
    }
    public static void movePlayer()
    {
        if(diceRoll)
        {
            int numberOfSquaresNeededToMove=0;
            dice=new Dice();
            dice2=new Dice();
            numberOfSquaresNeededToMove=Dice.addSides();
            Player currentPerson=players[currentPlayer];
            while(numberOfSquaresNeededToMove>=1)
            {
                Property currentProperty=property[currentPerson.getY()][currentPerson.getX()];
                if(currentPerson.getX()<numColumns-1&&currentPerson.getY()==0)
                {
                    currentPerson.setX(currentPerson.getX()+1);
                    numberOfSquaresNeededToMove--;
                }
                else if(currentPerson.getX()==numColumns-1&&currentPerson.getY()<=numRows-2)
                {
                    currentPerson.setY(currentPerson.getY()+1);
                    numberOfSquaresNeededToMove--;
                }
                else if(currentPerson.getX()>=1&&currentPerson.getY()<=numRows-1)
                {
                    currentPerson.setX(currentPerson.getX()-1);
                    numberOfSquaresNeededToMove--;
                }
                else if(currentPerson.getX()==0&&currentPerson.getY()>=0)
                {
                    currentPerson.setY(currentPerson.getY()-1);
                    numberOfSquaresNeededToMove--;
                }
                else
                    numberOfSquaresNeededToMove--;
                if(currentPerson.getY()==numRows-1&&currentPerson.getX()==numColumns-1)
                {
                    currentPerson.setMoney(currentPerson.getMoney()+200);
                }


            }
            Property currentProperty=property[currentPerson.getY()][currentPerson.getX()];
            if(currentProperty.getName().contentEquals("Smaug"))
            {
                Cards.Bad badCard=Cards.Bad.getRandomBadCard();
                badCard.Effect(currentPerson);
                drawBadCard=true;
                
            }
            else
                drawBadCard=false;
            if(currentProperty.getName().contentEquals("Treasure"))
            {
                Cards.Good goodCard=Cards.Good.getRandomGoodCard();
                goodCard.Effect(currentPerson);
                drawGoodCard=true;
            }
            else
                drawGoodCard=false;
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
//            if(currentProperty.getName().contentEquals("Good"))
//            {
//                drawGoodCard=true;
//                purchase=false;
//                attack=false;
//                payRent=false;
//            }
//            if(currentProperty.getName().contentEquals("Bad"))
//            {
//                drawBadCard=true;
//                purchase=false;
//                attack=false;
//                payRent=false;
//            }
            diceRoll=false;
            decision=true;
            if(AIOn&&(currentPlayer!=1||currentPlayer!=2||currentPlayer!=3))
                decision=true;
        }
    }
}
