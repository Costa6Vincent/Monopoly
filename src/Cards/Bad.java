/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import monopoly.project.Player;

/**
 *
 * @author VincentCoosta
 */
public class Bad extends Cards{


    private String effect;
    public int CardNumber;
    public static int numCards=13;
    public static Bad card[]=new Bad[numCards];

    
    public Bad(String _name, String _desc) 
    {
        super(_name, _desc);
        CardNumber=numCards;
    }
    
    public void setEffect(String effect) 
    {
        this.effect = effect;
    }

    public void setCardNumber(int CardNumber) 
    {
        this.CardNumber = CardNumber;
    }

    public static void setNumCards(int numCards) 
    {
        Bad.numCards = numCards;
    }

    public String getEffect() 
    {
        return effect;
    }

    public int getCardNumber() 
    {
        return CardNumber;
    }

    public static int getNumCards() 
    {
        return numCards;
    }
    
    public static Bad getRandomBadCard()
    {
        int random=(int)(Math.random()*numCards);
        Bad randomCard = card[random];
        return randomCard;
    }
    
    public static void InitializeDatabase()
    {
        card[0] = new Bad("card0","Lost in the Mirkwood Forest.");
        card[0].setEffect("Go back 3 spaces");
        
        card[1] = new Bad("card1","Use The One Ring to steal the Wood-elf gaoler key.");
        card[1].setEffect("Get put pf jail free card");

        
        card[2] = new Bad("card2","Elrond offers you the hospitality of Rivendell.");
        card[2].setEffect("Collect 150");
//""
        
        card[3]= new Bad("card3","By the last light of Durin's Day");
        card[3].setEffect("Advance to Lake-town Courtyard. If you pass GO collect 200");
        //""
        
        card[4] = new Bad("card4","Destined by Thrandul in the Wood-elf goal.");
        card[4].setEffect("Go direstly to Jail");
        //""
        
        card[5] = new Bad("card5","Smaug takes the flight to exact his revenge.");
        card[5].setEffect("Advance to whichever is nearest: Long Lake or Forges of Erebor. If UNOWNED, you may buy it from the Bank. \n" +
"        If OWNED, throw dice and pay owner a total ten times amount thrown.");
        //""
        
        card[6] = new Bad("card6","Steal the Arenstone");
        card[6].setEffect("Advance to GO");
        //""
        
        card[7]= new Bad("card7","Upgrade your fortifications in preparations for war!");
        card[7].setEffect("Pay 25 for each Outpost and 100 for each Great Hall");
        //""
        
        card[8]= new Bad("card8","Radagast leads pursuing Orcs away.");
        card[8].setEffect("Advance to Rhosgobel Rabbit Sled");
        //"."
        
        card[9]= new Bad("card9","Challenge Gollum to contest of riddles.");
        card[9].setEffect("Advance to Cave of Gollum");
        //
        
        card[10]= new Bad("card10","Discover the trolls' hidden trove of fine Elvish blades.");
        card[10].setEffect("Collect 50");
        //
        
        card[11]= new Bad("card11","Reclaim the ancestra throne of Thror The Last King Under The Mountain");
        card[11].setEffect("Advance to Erebor Throne Room");
        //
        
        card[12]= new Bad("card12","Bribe Bard the Bowman to smuggle you in to Lake-town.");
        card[12].setEffect("Pay 15");
        
    }
    public void Effect(monopoly.project.Player player)
    {
        String text = this.getEffect();
//        if(card[0])
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
//        if()
//        {
//            
//        }
        
    }
}
