/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.utilities;

import game.content.GameContent;
//import game.main.GameMenu;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment1
 */
public class Weapon extends GameContent //This class extends Game_Content
 //This class extends Game_Content
{
    private String weaponStat;  //String instance variable to store the weapon stat on the file
    private int weaponDamage;
    
    //Weapon constructor
    public Weapon(String weaponStat, int weaponDamage, String name, String weapon, String consumable) {
        super(name, weapon, consumable);
        this.weaponStat = weaponStat;
        this.weaponDamage = weaponDamage;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }
    
    public String getWeaponStat() //Getter method for weaponStat
    {
        return weaponStat;
    }
    
    public void setWeaponStat(String weaponStat) //Setter method for weaponStat
    {
        this.weaponStat = weaponStat;
    }
    
    @Override
    public boolean match(String query)//A Boolean method to return true if the input query is contained in the weapon
    {
        return getWeapon().toUpperCase().contains(query);
    }
    
    @Override
    public String toString() //A toString method which uses super to invoke Game_Content toString
                             //but also adding the weaponStat to the output string
    {
       return super.toString()+", STR +"+this.weaponStat;
    }

}
