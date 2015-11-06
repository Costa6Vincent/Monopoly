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
public class Bad extends Cards{

    private String effect;
    public int CardNumber;
    public static int numCards=0;
    public static Cards card[]=new Bad[40];

    
    public Bad(String _name, String _desc) 
    {
        super(_name, _desc);
        numCards++;
        CardNumber=numCards;
    }
    
    public void setEffect(String effect) 
    {
        this.effect = effect;
    }

    public void setCardNumber(int CardNumber) {
        this.CardNumber = CardNumber;
    }

    public static void setNumCards(int numCards) {
        Bad.numCards = numCards;
    }

    public String getEffect() 
    {
        return effect;
    }

    public int getCardNumber() {
        return CardNumber;
    }

    public static int getNumCards() {
        return numCards;
    }
    
    public static void InitializeDatabase()
    {
        card[0] = new Bad("","");
        card[1] = new Bad("","");
        card[2] = new Bad("","");
        card[3]= new Bad("","");
        card[4] = new Bad("","");
        card[5] = new Bad("","");
        card[6] = new Bad("","");
        card[7]= new Bad("","");
        card[8]= new Bad("","");
        card[9]= new Bad("","");
        card[10]= new Bad("","");
        card[11]= new Bad("","");
        card[12]= new Bad("","");
        card[13]= new Bad("","");
        card[14]= new Bad("","");
        card[15]= new Bad("","");
        card[16]= new Bad("","");
        card[17]= new Bad("","");
        card[18]= new Bad("","");
        card[19]= new Bad("","");
        card[20]= new Bad("","");
        card[21]= new Bad("","");
        card[22]= new Bad("","");
        card[23]= new Bad("","");
        card[24]= new Bad("","");
        card[25]= new Bad("","");
        card[26]= new Bad("","");
        card[27]= new Bad("","");
        card[28]= new Bad("","");
        card[29]= new Bad("","");
        card[30]= new Bad("","");
        card[31]= new Bad("","");
        card[32]= new Bad("","");
        card[33]= new Bad("","");
        card[34]= new Bad("","");
        card[35]= new Bad("","");
        card[36]= new Bad("","");
        card[37]= new Bad("","");
        card[38]= new Bad("","");
        card[39]= new Bad("","");
        
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
}
