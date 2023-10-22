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
public class Potions extends GameContent{
    private Player player;
    private final String potionName;
    private int hpRegeneration;

    //Potion constructor
    public Potions(Player player, String potionName, int hpRegeneration, String name, String weapon, String consumable) {
        super(name, weapon, consumable);
        this.player = player;
        this.potionName = potionName;
        this.hpRegeneration = hpRegeneration;
    }
    // Method allow player to regenerate the Hp
    public void usePotion() {
        this.hpRegeneration = player.getPlayerHp() + 30;
        player.removePotion("potion");
        System.out.println("Using " + potionName + ". It regenerates 30 HP, you are now at " + hpRegeneration + " HP.");
        System.out.println("You have used a potion");
    }

}
