/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.utilities;

import game.content.GameContent;


/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment1
 */
public class MobBattle extends GameContent 
{
    private Player player;
    private Weapon weapon;
    private Mobs mob;

    //MobBattle constructor
    public MobBattle(Player player, Mobs mob, String name, String weapon, String consumable) 
    {
        super(name, weapon, consumable);
        this.player = player;
        this.mob = mob;
    }
    
    //Mob Turn
    public void handleMobTurn(){
        System.out.println(mob.getMobName() + "'s turn.");
        
        //Random chance for mob miss attack
        if(Math.random() < 0.2){
            System.out.println(mob.getMobName() + " missed the attack!");
        }
        else {
            mobAttack();
        }
    }
    
    //Player damage calculation
    public void playerAttack() 
    {
        weapon = new Weapon("Rusty Sword Common", 8, "", "", "");
        int playerDamage = weapon.getWeaponDamage()* 2;
        mob.decreaseHP(playerDamage);
        System.out.println("You attacked " + mob.getMobName() + " for " + playerDamage + " damage!");
    }
    
    //Mob damage calculation
    private void mobAttack()
    {
        int mobDamage = mob.getAttackPower();
        player.decreaseHP(mobDamage);
        System.out.println(mob.getMobName() + " attacked you for " + mobDamage + " damage!");
    }       
    
}