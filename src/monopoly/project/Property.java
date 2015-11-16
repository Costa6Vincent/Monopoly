/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.project;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import static monopoly.project.MonopolyProject.property;

public class Property {

    
    private int cost;
    private int rent;
    private int propertyTax;
    private String name;
    private Image splashImage;
    private int tax;
    private Player thePlayer;
    private boolean canPurchase;
    private boolean isJail;
    private int upgrade;
    private int defense;

    public boolean getCanPurchase() {
        return canPurchase;
    }

    public void setCanPurchase(boolean canPurchase) {
        this.canPurchase = canPurchase;
    }
    
    Property(String _name,int _cost,Image _splashImage)
    {
        name=_name;
        cost=_cost;
        splashImage=_splashImage;
        rent=cost/3;
        tax=cost/10;
        canPurchase=true;
        isJail=false;
        upgrade=0; 
        defense=50;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }

    public void setThePlayer(Player thePlayer) {
        this.thePlayer = thePlayer;
    }
    public void addUpgrade()
    {
        if(upgrade<4)
        {
            upgrade++;
            defense+=100;
        }
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public int getUpgrade() {
        return upgrade;
    }
    Property(String _name,Image _splashImage)
    {
        name=_name;
        splashImage=_splashImage;
    }
    public void addPlayer(Player _player)
    {
        if(thePlayer==null)
        {
            thePlayer= _player;
            _player.addProperty(this);
        }
    }
    public void deletePlayer(Player _player)
    {
        if(thePlayer!=null)
        {
            thePlayer= null;
            _player.deleteProperty(this);
        }
    }

    public Player getThePlayer() {
        return thePlayer;
    }
    
    public int getCost() {
        return cost;
    }

    public int getRent() {
        return rent;
    }

    public int getPropertyTax() {
        return propertyTax;
    }

    public String getName() {
        return name;
    }

    public Image getSplashImage() {
        return splashImage;
    }

    public int getTax() {
        return tax;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setPropertyTax(int propertyTax) {
        this.propertyTax = propertyTax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSplashImage(Image splashImage) {
        this.splashImage = splashImage;
    }
    


    public void setTax(int tax) {
        this.tax = tax;
    }
    public void draw(Graphics2D g,int xpos,int ypos,int length,int height,MonopolyProject image  ) 
    {
        g.drawImage(getSplashImage(), xpos, ypos, length*3/4, height,image);
    }
    
    public static void InitializeDataBase()
    {
        
        Image image1=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/FreeParking.PNG");
        Image image2=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[0][1].PNG");
        Image image3=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[0][2].PNG");
        Image image4=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[0][3].PNG");
        Image image5=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[0][4].PNG");
        Image image6=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[0][5].PNG");
        Image image7=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[0][6].PNG");
        Image image8=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[0][7].PNG");
        Image image9=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[0][8].PNG");
        Image image10=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[0][9].PNG");
        Image image11=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/GoToJail.PNG");
        
        Image image12=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[1][10].PNG");
        Image image13=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[2][10].PNG");
        Image image14=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[3][10].PNG");
        Image image15=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[4][10].PNG");
        Image image16=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[5][10].PNG");
        Image image17=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[6][10].PNG");
        Image image18=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[7][10].PNG");
        Image image19=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[8][10].PNG");
        Image image20=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[9][10].PNG");
        
        Image image21=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Jail.PNG");
        Image image22=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[10][1].PNG");
        Image image23=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[10][2].PNG");
        Image image24=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[10][3].PNG");
        Image image25=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[10][4].PNG");
        Image image26=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[10][5].PNG");
        Image image27=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[10][6].PNG");
        Image image28=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[10][7].PNG");
        Image image29=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[10][8].PNG");
        Image image30=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[10][9].PNG");
        Image image31=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Go.PNG");
        
        Image image32=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[1][1].PNG");
        Image image33=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[2][1].PNG");
        Image image34=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[3][1].PNG");
        Image image35=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[4][1].PNG");
        Image image36=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[5][1].PNG");
        Image image37=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[6][1].PNG");
        Image image38=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[7][1].PNG");
        Image image39=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[8][1].PNG");
        Image image40=Toolkit.getDefaultToolkit().getImage("Pictures/BoardPieces/Piece[9][1].PNG");
        
        
        property[0][0]=new Property("Free Parking",image1);
        property[0][0].setCanPurchase(false);
        property[0][1]=new Property("The Lobby",250,image2);
        property[0][2]=new Property("Smaug",300,image3);
        property[0][2].setCanPurchase(false);
        property[0][3]=new Property("The CourtYard",350,image4);
        property[0][4]=new Property("Priest Dining Room",100,image5);
        property[0][5]=new Property("White Van",50,image6);
        property[0][6]=new Property("Convent Chapel",60,image7);
        property[0][7]=new Property("Mary Mother Chapel",90,image8);
        property[0][8]=new Property("Computer Services",250,image9);
        property[0][9]=new Property("St. Charles Chapel",120,image10);
        property[0][10]=new Property("Go to Jail",image11);
        property[0][10].setCanPurchase(false);
        property[0][10].setIsJail(true);
        
        //side right column
        property[1][10]=new Property("Glennon Lounge",250,image12);
        property[2][10]=new Property("Priest's Lounge",300,image13);
        property[3][10]=new Property("Treasure",350,image14);
        property[4][10]=new Property("Kenrick Lounge",100,image15);
        property[5][10]=new Property("Sister's Car",50,image16);
        property[6][10]=new Property("Smaug",60,image17);
        property[6][10].setCanPurchase(false);
        property[7][10]=new Property("St. Joseph Chapel",90,image18);
        property[8][10]=new Property("Room and Board",250,image19);
        property[9][10]=new Property("The Tower",120,image20);
        
        //bottom row
        property[10][0]=new Property("Jail",image21);
        property[10][0].setCanPurchase(false);
        property[10][0].setIsJail(true);
        property[10][1]=new Property("Weight Room",250,image22);
        property[10][2]=new Property("Laundry Room",300,image23);
        property[10][3]=new Property("Smaug",350,image24);
        property[10][3].setCanPurchase(false);
        property[10][4]=new Property("Tennis Court",100,image25);
        property[10][5]=new Property("Red Van",50,image26);
        property[10][6]=new Property("Formation Fee",60,image27);
        property[10][7]=new Property("Boiler Room",90,image28);
        property[10][8]=new Property("Treasure",250,image29);
        property[10][9]=new Property("Wreck Room",120,image30);
        property[10][10]=new Property("Go",image31);
        property[10][10].setCanPurchase(false);
        
        
        //side left column
        property[1][0]=new Property("The Refectory",250,image32);
        property[2][0]=new Property("The Heights",300,image33);
        property[3][0]=new Property("Treasure",350,image34);
        property[3][0].setCanPurchase(false);
        property[4][0]=new Property("The Gym",100,image35);
        property[5][0]=new Property("Silver Van",50,image36);
        property[6][0]=new Property("The Auditorium",60,image37);
        property[7][0]=new Property("The Library",90,image38);
        property[8][0]=new Property("Kendrick Light",250,image39);
        property[9][0]=new Property("The Dorm",120,image40);
    }

    public boolean getIsJail() {
        return isJail;
    }

    public void setIsJail(boolean isJail) {
        this.isJail = isJail;
    }
    
    
}
