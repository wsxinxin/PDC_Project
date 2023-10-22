/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.entity;

import javax.swing.JTextArea;

/**
 *
 * @author Christian
 */
public class Battle {
   
    Player player;
    Weapon weapon;
    Monster mob;
    private JTextArea textArea;

    public Battle(Player player, Weapon weapon, Monster mob) {
        this.player = player;
        this.weapon = weapon;
        this.mob = mob;
    }
    
    public void appendText(String text) {
        textArea.append(text + "\n");
    }
    
    public void handleMobTurn() {
        // Add your mob turn logic here
        appendText(mob.getMobName() + "'s turn.");

        // Random chance for mob to miss the attack
        if (Math.random() < 0.2) {
            appendText(mob.getMobName() + " missed the attack!");
        } else {
            mobAttack();
        }
    }

    public void playerAttack() {
        // Add your player attack logic here
        weapon = new Weapon();
        int playerDamage = weapon.getWeaponDamage() * 2;
        mob.decreaseHP(playerDamage);
        appendText("You attacked " + mob.getMobName() + " for " + playerDamage + " damage!");
    }

    private void mobAttack() {
        // Add your mob attack logic here
        int mobDamage = mob.getAttackPower();
        player.decreaseHP(mobDamage);
        appendText(mob.getMobName() + " attacked you for " + mobDamage + " damage!");
    }
}
