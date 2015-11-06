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
public abstract class Cards 
{
    public String name;
    public String description;
    
    Cards(String _name,String _desc)
    {
        name=_name;
        description=_desc;
        
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
