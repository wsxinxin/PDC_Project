/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.content;

import game.interfaces.Function;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment1
 */
abstract public class GameContent //Abstract Game_Content Class
{                                                                                      
    public String name;          //Private instance variables
    public String weapon;        //3 String instance variables with their respected Getter and Setter methods
    public String potion;
    
    public GameContent(String name, String weapon, String consumable) //Constructor, Game_Content objects can only be instantiated by suppling
    {                                                                  //input parameters for all three instance variables
        this.setName(name);
        this.setWeapon(weapon);
        this.setConsumable(potion);
    }
    
    public String getName() //Get method for Name
    {
        return name;
    }
    
    public void setName(String name) //Set method for Name
    {
        this.name = name;
    }
    
    public String getWeapon()  //Get method for Weapon
    {
        return weapon;
    }
    
    public void setWeapon(String weapon) //Set method for Weapon
    {
        this.weapon = weapon;
    }
    
    public String getConsumable() //Get method for Consumable
    {
        return potion;
    }
    
    public void setConsumable(String consumable) //Set method for Consumable
    {
        this.potion = consumable;
    }
    
    public String toString()  //A toString method to return a string describing the Game_Content
    {
        return "Greetings: "+this.name+ "Your weapon equipped is: "+this.weapon+ "Potion(s) left: "+this.potion;
    }
    
    public int compareTo(GameContent otherstuff)  //A Compare method to compare weapon with other weapon
    {
        return this.weapon.compareTo(getWeapon());
    }
    
    public boolean match(String query)  //A Boolean method to return true if the input query is contained in the weapon
    {
        return getWeapon().toUpperCase().contains(query);
    }
}
