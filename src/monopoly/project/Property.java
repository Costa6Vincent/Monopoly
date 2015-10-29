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
    
    Property(String _name,int _cost,Image _splashImage)
    {
        name=_name;
        cost=_cost;
        splashImage=_splashImage;
        rent=cost/3;
        tax=cost/10;
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
        g.drawImage(getSplashImage(), xpos, ypos, length, height,image);
    }
    
    public static void InitializeDataBase()
    {
        
        Image image1=null;/*Toolkit.getDefaultToolkit().getImage("./Pictures/BoardPieces/b.GIF")*/;
        
        property[0][0]=new Property("Free Parking",image1);
        property[0][1]=new Property("Piece 2",250,image1);
        property[0][2]=new Property("Piece 3",300,image1);
        property[0][3]=new Property("Piece 4",350,image1);
        property[0][4]=new Property("Piece 5",100,image1);
        property[0][5]=new Property("Piece 6",50,image1);
        property[0][6]=new Property("Piece 7",60,image1);
        property[0][7]=new Property("Piece 8",90,image1);
        property[0][8]=new Property("Piece 9",250,image1);
        property[0][9]=new Property("Piece 10",120,image1);
        property[0][10]=new Property("Go to Jail",image1);
        
        //side right column
        property[1][10]=new Property("Piece 12",250,image1);
        property[2][10]=new Property("Piece 13",300,image1);
        property[3][10]=new Property("Piece 14",350,image1);
        property[4][10]=new Property("Piece 15",100,image1);
        property[5][10]=new Property("Piece 16",50,image1);
        property[6][10]=new Property("Piece 17",60,image1);
        property[7][10]=new Property("Piece 18",90,image1);
        property[8][10]=new Property("Piece 19",250,image1);
        property[9][10]=new Property("Piece 20",120,image1);
        
        //bottom row
        property[10][0]=new Property("Jail",image1);
        property[10][1]=new Property("Piece 22",250,image1);
        property[10][2]=new Property("Piece 23",300,image1);
        property[10][3]=new Property("Piece 24",350,image1);
        property[10][4]=new Property("Piece 25",100,image1);
        property[10][5]=new Property("Piece 26",50,image1);
        property[10][6]=new Property("Piece 27",60,image1);
        property[10][7]=new Property("Piece 28",90,image1);
        property[10][8]=new Property("Piece 29",250,image1);
        property[10][9]=new Property("Piece 30",120,image1);
        property[10][10]=new Property("Go",image1);
        
        //side left column
        property[1][0]=new Property("Piece 32",250,image1);
        property[2][0]=new Property("Piece 33",300,image1);
        property[3][0]=new Property("Piece 34",350,image1);
        property[4][0]=new Property("Piece 35",100,image1);
        property[5][0]=new Property("Piece 36",50,image1);
        property[6][0]=new Property("Piece 37",60,image1);
        property[7][0]=new Property("Piece 38",90,image1);
        property[8][0]=new Property("Piece 39",250,image1);
        property[9][0]=new Property("Piece 40",120,image1);
    }
    
    
}
