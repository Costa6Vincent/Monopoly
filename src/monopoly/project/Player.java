package monopoly.project;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import static monopoly.project.MonopolyProject.players;
public class Player 
{
    private String name;
    private int Money;
    private Image splashImage;
    private Image onBoardImage;
    static private Image player1=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player1.JPG");
    static private Image player2=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player2.JPG");
    static private Image player3=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player3.JPG");
    static private Image player4=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player4.JPG");
    static private Image p1onBoard=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/p1onBoard.PNG");;
    static private Image p2onBoard=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/p2onBoard.PNG");;
    static private Image p3onBoard=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/p3onBoard.PNG");;
    static private Image p4onBoard=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/p4onBoard.PNG");;
    private ArrayList<Property>propertyOwnership= new ArrayList<>();
    private boolean isTurn=false;
    private int x;
    private int y;
    private int numProperty;
    private boolean inJail;
    private Army theArmy;
    private boolean inGame;
    private Color color; 
    

   
    
    Player(String _name,int _money,int numProperties,boolean _inGame,Color _color, Image _Image,Image _Image2 )
    {
        name=_name;
        Money=_money;
        splashImage=_Image;
        onBoardImage=_Image2;
        inGame=_inGame;
        color=_color;
    }

    public boolean getInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public boolean getInGame() {
        return inGame;
    }

    public void SetInGame(boolean inGame) {
        this.inGame = inGame;
    }
    
    public void addProperty(Property _property)
    {
        //if(propertyOwnership.get(numProperty)==null)
        {
            numProperty++;
            propertyOwnership.add(_property);
            _property.addPlayer(this);
        }
    }
    public void deleteProperty(Property _property)
    {
        //if(propertyOwnership.get(numProperty)!=null)
        {
            numProperty--;
            propertyOwnership.remove(_property);
            _property.deletePlayer(this);
        }
    }
    public void addArmy(Army _army)
    {

        theArmy=_army;
        _army.addPlayer(this);
    }

    public void setTheArmy(Army _theArmy) {
        theArmy = _theArmy;
    }

    public Army getTheArmy() {
        return theArmy;
    }
    public void deleteArmy(Army _army)
    {
        if(propertyOwnership.get(numProperty)!=null)
        {
            theArmy=null;
            _army.deletePlayer(this);
        }
    }

    public int getNumProperty() {
        return numProperty;
    }
    public void getAllProperties()
    {
        for(int index=0;index<numProperty;index++)
        {
            System.out.println(getPropertyOwnership(index).getName());
        }
    }

    public Property getPropertyOwnership(int _index) 
    {
        return propertyOwnership.get(_index);
    }
    
    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public void setNumProperty(int numProperty) {
        this.numProperty = numProperty;
    }

    public boolean getIsTurn() {
        return isTurn;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getSplashImage() {
        return splashImage;
    }

    public void setSplashImage(Image splashImage) {
        this.splashImage = splashImage;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return Money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int Money) {
        this.Money = Money;
    }
    public Image getOnBoardImage() {
        return onBoardImage;
    }

    public Color getColor() {
        return color;
    }
    
    public void drawonBoard(Graphics2D g,int xpos,int ypos,int length,int height,MonopolyProject image  ) 
    {
        g.drawImage(getOnBoardImage(), xpos, ypos, length/2, height,image);
    }
    public void drawonWindow(Graphics2D g,int xpos,int ypos,int length,int height,MonopolyProject image  )
    {
        g.drawImage(getSplashImage(), xpos, ypos, length, height,image);
    }
    
    public static void InitializeDataBase2()
    {
        players[0]=new Player("Player1",5000,0,true,Color.red,player1,p1onBoard);
        players[1]=new Player("Player2",5000,0,true,Color.blue,player2,p2onBoard);
        players[2]=new Player("Player3",5000,0,true,Color.green,player3,p3onBoard);
        players[3]=new Player("Player4",5000,0,true,Color.magenta,player4,p4onBoard);
    }

   
    
    
}