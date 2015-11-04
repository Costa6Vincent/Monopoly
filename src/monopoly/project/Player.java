package monopoly.project;
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
    static private Image player1=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player1.GIF");
    static private Image player2=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player2.GIF");
    static private Image player3=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player3.GIF");
    static private Image player4=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player4.GIF");
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
    

   
    
    Player(String _name,int _money,int numProperties, Image _Image,Image _Image2 )
    {
        name=_name;
        Money=_money;
        splashImage=_Image;
        onBoardImage=_Image2;
    }

    public boolean getInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
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
        //if(propertyOwnership.get(numProperty)==null)
        {
            numProperty--;
            propertyOwnership.remove(_property);
            _property.deletePlayer(this);
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

    public void drawonBoard(Graphics2D g,int xpos,int ypos,int length,int height,MonopolyProject image  ) 
    {
        g.drawImage(getOnBoardImage(), xpos, ypos, length, height,image);
    }
    public void drawonWindow(Graphics2D g,int xpos,int ypos,int length,int height,MonopolyProject image  )
    {
        g.drawImage(getSplashImage(), xpos, ypos, length, height,image);
    }
    
    public static void InitializeDataBase2()
    {
        players[0]=new Player("Player1",500,0,player1,p1onBoard);
        players[1]=new Player("Player2",500,0,player2,p2onBoard);
        players[2]=new Player("Player3",500,0,player3,p3onBoard);
        players[3]=new Player("Player4",500,0,player4,p4onBoard);
    }

   
    
    
}