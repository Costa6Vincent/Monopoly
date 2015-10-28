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
    private Image splashImage=Toolkit.getDefaultToolkit().getImage("./Pictures/BoardPieces/b.GIF");
    private ArrayList<Property>propertyOwnership= new ArrayList<>();
    private boolean isTurn=false;
    private int x;
    private int y;
    private int numProperty;
    

   
    
    Player(String _name,int _money,int numProperties)
    {
        name=_name;
        Money=_money;
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
        players[0]=new Player("Player1",500,0);
        players[1]=new Player("Player2",500,0);
        players[2]=new Player("Player3",500,0);
        players[3]=new Player("Player4",500,0);
    }
    
    
}