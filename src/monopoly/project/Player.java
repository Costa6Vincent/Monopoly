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
    private int numProperties;
    private Image splashImage;
    static private Image player1=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player1.GIF");
    static private Image player2=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player2.GIF");
    static private Image player3=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player3.GIF");
    static private Image player4=Toolkit.getDefaultToolkit().getImage("./Pictures/PlayerPieces/Player4.GIF");
    private ArrayList<Property>propertyOwnership= new ArrayList<>();
    private boolean isTurn=false;
    private int x;
    private int y;
    private int numProperty;
    

   
    
    Player(String _name,int _money,int numProperties, Image _Image )
    {
        name=_name;
        Money=_money;
        splashImage=_Image;
    }
    
    public void addProperty(Property _property)
    {
        if(propertyOwnership==null)
        {
            numProperty++;
            propertyOwnership.add(_property);
            _property.addPlayer(this);
        }
    }

    public ArrayList<Property> getPropertyOwnership() {
        return propertyOwnership;
    }
    
    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
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

    public int getNumProperties() {
        return numProperties;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int Money) {
        this.Money = Money;
    }

    public void setNumProperties(int numProperties) {
        this.numProperties = numProperties;
    }
    
    public void draw(Graphics2D g,int xpos,int ypos,int length,int height,MonopolyProject image  ) 
    {
        g.drawImage(getSplashImage(), xpos, ypos, length, height,image);
    }
    
    public static void InitializeDataBase2()
    {
        players[0]=new Player("Player1",500,0,player1);
        players[1]=new Player("Player2",500,0,player2);
        players[2]=new Player("Player3",500,0,player3);
        players[3]=new Player("Player4",500,0,player4);
    }
    
    
}