/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

/**
 *
 * @author VincentCoosta
 */
public class Good extends Cards
{
    public String effect;
    public int CardNumber;
    public static int numCards=0;
    public static Good card[]=new Good[40];
    public Good(String _name, String _desc) 
    {
        super(_name, _desc);
        numCards++;
        CardNumber=numCards;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setCardNumber(int CardNumber) {
        this.CardNumber = CardNumber;
    }

    public static void setNumCards(int numCards) {
        Good.numCards = numCards;
    }

    public String getEffect() {
        return effect;
    }

    public int getCardNumber() {
        return CardNumber;
    }

    public static int getNumCards() {
        return numCards;
    }
    
    public void InitializeDatabase()
    {
        card[0] = new Good("Card1","Bribe Bard Bowman");
        card[0].setEffect("Pay 15");
        
        card[1] = new Good("Card2","Bolg has caught your scent");
        card[1].setEffect("Advance buy property from bank");
        
        card[2] = new Good("Card3","");
        card[2].setEffect("Advance buy property from bank");
        
        card[3]= new Good("Card4","Hospitality");
        card[3].setEffect("Collect 150");
        
        card[4] = new Good("Card5","last light durins day");
        card[4].setEffect("Collect 200");
        
        card[5] = new Good("Card6","");
        card[5].setEffect("Advance buy property from bank");
        
        card[6] = new Good("","");
        card[7]= new Good("","");
        card[8]= new Good("","");
        card[9]= new Good("","");
        card[10]= new Good("","");
        card[11]= new Good("","");
        card[12]= new Good("","");
        card[13]= new Good("","");
        card[14]= new Good("","");
        card[15]= new Good("","");
        card[16]= new Good("","");
        card[17]= new Good("","");
        card[18]= new Good("","");
        card[19]= new Good("","");
        card[20]= new Good("","");
        card[21]= new Good("","");
        card[22]= new Good("","");
        card[23]= new Good("","");
        card[24]= new Good("","");
        card[25]= new Good("","");
        card[26]= new Good("","");
        card[27]= new Good("","");
        card[28]= new Good("","");
        card[29]= new Good("","");
        card[30]= new Good("","");
        card[31]= new Good("","");
        card[32]= new Good("","");
        card[33]= new Good("","");
        card[34]= new Good("","");
        card[35]= new Good("","");
        card[36]= new Good("","");
        card[37]= new Good("","");
        card[38]= new Good("","");
        card[39]= new Good("","");
        
    }
    public void Effect()
    {
        String text = this.getEffect();
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
//        if()
//        {
//            
//        }
        
    }
    public int drawGoodCard()
    {
        int random=(int)(Math.random()*numCards);
        return random;
    }
    
}
