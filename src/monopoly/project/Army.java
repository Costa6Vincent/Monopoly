/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.project;
import static monopoly.project.MonopolyProject.army;
import static monopoly.project.MonopolyProject.numPlayers;
import static monopoly.project.MonopolyProject.players;
public class Army{
    
    private static int numArmies=numPlayers*5;
    
    private static int maxPower;
    
    private Player thePlayer;
    
    private int currentArmyPower;
    
    private String name;
    
   
    
    Army(int _power,Player _player,String _name)
    {
        currentArmyPower=_power;
        thePlayer=_player;
        name=_name;
    }
    Army(int _power,String _name)
    {
        currentArmyPower=_power;
        name=_name;
    }
    public void addPlayer(Player _player)
    {
        if(thePlayer==null)
        {
            thePlayer= _player;
            _player.addArmy(this);
        }
    }
    public void deletePlayer(Player _player)
    {
        if(thePlayer!=null)
        {
            thePlayer= null;
            _player.deleteArmy(this);
        }
    }

    public static int getNumArmies() {
        return numArmies;
    }

    public static int getMaxPower() {
        return maxPower;
    }

    public Player getThePlayer() {
        return thePlayer;
    }

    public int getCurrentArmyPower() {
        return currentArmyPower;
    }
    
    public String getName() {
        return name;
    }

    public static void setNumArmies(int _numArmies) {
        numArmies = _numArmies;
    }

    public static void setMaxPower(int _maxPower) {
        maxPower = _maxPower;
    }
    
    public void setName(String _name) {
        name = _name;
    }
    
    public boolean determineWin(int _propertyPower)
    {
        if(_propertyPower>getCurrentArmyPower())
        {
            ArmyDestroyed();
            System.out.println("army lost!");
            MonopolyProject.StringMove=true;
            return false;
           // GUIs.Event.=true;
        }
        else
        {
            ArmyWin();
            System.out.println("army won!");
            MonopolyProject.StringMove=true;
            return true;
        }
    }
        

    public void setThePlayer(Player _thePlayer) {
        thePlayer = _thePlayer;
    }

    public void setCurrentArmyPower(int _currentArmyPower) {
        currentArmyPower = _currentArmyPower;
    }
    
    public void ArmyDestroyed()
    {
        currentArmyPower=0;
    }
    public void ArmyWin()
    {
        currentArmyPower+=50;
        
        
    }
    
    public static void InitializeDataBase()
    {
        army[0]=new Army(100,players[0],"Army 1");
        players[0].addArmy(army[0]);
        
        army[1]=new Army(100,players[1],"Army 2");
        players[1].addArmy(army[1]);
        
        army[2]=new Army(100,players[2],"Army 3");
        players[2].addArmy(army[2]);
        
        army[3]=new Army(100,players[3],"Army 4");
        players[3].addArmy(army[3]);
    }
    
    
    
}
