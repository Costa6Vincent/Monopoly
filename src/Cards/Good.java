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
    public static int numCards=16;
    public static Good card[]=new Good[numCards];
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
    
    public static Good getRandomGoodCard()
    {
        int random=(int)(Math.random()*numCards+1);
        Good randomCard = card[random];
        return randomCard;
    }
    
    public static void InitializeDatabase()
    {
        card[0] = new Good("Card0","Tauriel uses kingsfoil and her magic to heal your wounds.");
        card[0].setEffect("Collect 25");
        
        card[1] = new Good("Card1","Galadriel reveals The Light of Eärendil.");
        card[1].setEffect("Get out of jail free");
        
        card[2] = new Good("Card2","Recruit a burglar to join your expedition.");
        card[2].setEffect("Collect 10 from every player.");
        
        card[3]= new Good("Card3","Rescue Gandalf and vanquish Nazgul.");
        card[3].setEffect("Collect 100");
        
        card[4] = new Good("Card4","Beorn provides you with ponies and provisions.");
        card[4].setEffect("Collect 20");
        
        card[5] = new Good("Card5","Orc forces are discovered amassing at Gundabad.");
        card[5].setEffect("Pay 40 for each Outpost and 115 for each Great Hall");
        
        card[6] = new Good("Card6"," Imprisoned by the Master of Lake-town.");
        card[6].setEffect("Go to jail. Go directly to jail, do not pass GO");
        
        card[7]= new Good("card7","The Necromancer of Dol Guldur is revealed. Sauron has returned!");
        card[7].setEffect("Pay 100");
        
        card[8]= new Good("card8","The Great Eagles come to your rescure");
        card[8].setEffect("Collect 200");
        
        card[9]= new Good("card9","You trick Smaug into rekindling the Forges of Erebor.");
        card[9].setEffect("Collect 50");
        
        card[10]= new Good("card10","Dain Ironfoot, Lord of the Iron Hills, and his army of dwarves arrive.");
        card[10].setEffect("Receive 100");
        
        card[11]= new Good("card11","There and Back again.");
        card[11].setEffect("Advance to GO and collect 200");
        
        card[12]= new Good("card12","Vanquish spiders with your deadly blade, and eaern the title 'Sting.'");
        card[12].setEffect("Collect 10");
        
        card[13]= new Good("card13","Poisoned by an Orc arrow.");
        card[13].setEffect("Pay 50");
        
        card[14]= new Good("card14","Negotiations between the armies of Dwarves, Elves, and Men are at an impasse.");
        card[14].setEffect("Pay 50");
        
        card[15]= new Good("card15","Your aim is true, as you pierce the heart of the mighty Smaug with the Black Arrow!");
        card[15].setEffect("Collect 100");
        
    }
    public void Effect(monopoly.project.Player player)
    {
        String text = this.getEffect();
        if(text.contentEquals("Collect 25"))
        {
            player.setMoney(player.getMoney()+25);
        }
        if(text.contentEquals("Collect 10 from every player."))
        {
            player.setMoney(player.getMoney()+30);
        }
        if(text.contentEquals("Collect 100"))
        {
            player.setMoney(player.getMoney()+100);
        }
        if(text.contentEquals("Collect 20"))
        {
            player.setMoney(player.getMoney()+20);
        }
        if(text.contentEquals("Pay 40 for each Outpost and 115 for each Great Hall"))
        {
            player.setMoney(player.getMoney()-monopoly.project.MonopolyProject.players[0].getNumProperty()*25);
            player.setMoney(player.getMoney()-monopoly.project.MonopolyProject.players[1].getNumProperty()*25);
            player.setMoney(player.getMoney()-monopoly.project.MonopolyProject.players[2].getNumProperty()*25);
            player.setMoney(player.getMoney()-monopoly.project.MonopolyProject.players[3].getNumProperty()*25);
        }
        if(text.contentEquals("Go to jail. Go directly to jail, do not pass GO"))
        {
            player.setInJail(true);
            player.setX(0);
            player.setY(10);
            Logic.LeftClick.nextTurn();
        }
        if(text.contentEquals("Pay 100"))
        {
            player.setMoney(player.getMoney()-100);
        }
        if(text.contains("Collect 200"))
        {
            player.setMoney(player.getMoney()+200);
        }
        if(text.contentEquals("Collect 50"))
        {
            player.setMoney(player.getMoney()+50);
        }
        if(text.contentEquals("Receive 100"))
        {
            player.setMoney(player.getMoney()+100);
        }
        if(text.contentEquals("Advance to GO and collect 200"))
        {
            player.setMoney(player.getMoney()+200);
            player.setY(monopoly.project.MonopolyProject.numRows-1);
            player.setX(monopoly.project.MonopolyProject.numColumns-1);
        }
        if(text.contentEquals("Collect 10"))
        {
            player.setMoney(player.getMoney()+10);
        }
        
    }
    public int drawGoodCard()
    {
        int random=(int)(Math.random()*numCards);
        return random;
    }
    
}
