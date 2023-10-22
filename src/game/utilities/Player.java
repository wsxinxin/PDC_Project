package game.utilities;

import game.content.GameContent;
//import game.main.GameMenu;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment1
 */
public class Player extends GameContent {
    private String PlayerName;
    private int PlayerHp;
    private List<String> weapons;
    private List<String> potions;
    private int potionCount;
    private List<String> loot;

    //player constructor
    public Player(String PlayerName, int PlayerHp, List<String> weapons, List<String> potions, String name, String weapon, String consumable) {
        super(name, weapon, consumable);
        this.PlayerName = PlayerName;
        this.PlayerHp = PlayerHp;
        this.weapons = weapons;
        this.potions = potions;
        this.loot = new ArrayList<String>();
    }
    
    public String getPlayerName() {
        return this.PlayerName;
    }
    
    public String setPlayerName(String PlayerName){
        PlayerName = "Noob";
        this.PlayerName = PlayerName;
        return PlayerName;
    }

    public int getPlayerHp() {
        return this.PlayerHp;
    }
    
    public int setPlayerHp(int PlayerHp){
        PlayerHp = 100;
        this.PlayerHp = PlayerHp;
        return this.PlayerHp;
    }

    public List<String> getWeapons() {
        return weapons;
    }
    
    public void setWeapon(){
        this.weapon = "RustySword";
    }

    public List<String> getPotions() {
        return potions;
    }

    public boolean isAlive() {
        return PlayerHp > 0;
    }

    //Add a weapon to player inventory
    public void addWeapon(String weapon) {
        this.weapons.add(weapon);
        System.out.println(PlayerName + " added " + weapon + " to their inventory!");
    }

    public String getEquippedWeapon() {
        // Implement this method to return the currently equipped weapon
        if (weapons.isEmpty()) {
            return "No weapons equipped";
        } else {
            return weapons.get(0);
        }
    }
  
    // Add the method for getting the currently equipped potion (similar to getEquippedWeapon)
    public String getEquippedPotion() {
        if (potions.isEmpty()) {
            return "No potion equipped";
        } else {
            return potions.get(0);
        }
    }
    
    // decrease the hp after suffer damage
    public void decreaseHP(int damage) {
        PlayerHp -= damage;
        if (PlayerHp < 0) {
            PlayerHp = 0;
        }
    }
    
    //generate and update player inventory
    public ArrayList<String> getInventoryList() {
        ArrayList<String> inventoryList = new ArrayList<>();
        inventoryList.add("Weapons:\n");
        if(weapons.isEmpty())
        {
            inventoryList.add("No weapons in inventory");
        }
        else
        {
            for(String weapon : weapons)
            {
                inventoryList.add(weapon + "\n");
            }
        }
        inventoryList.add("Potions:\n");
        if(potions.isEmpty())
        {
            inventoryList.add("No potions in inventory");
        }
        else
        {
            for(String potion : potions)
            {
                inventoryList.add(potion + "\n");
            }
        }
        return inventoryList;

    }
    
    // add the loots droped to the inventory
    public void addLoot(List<String> addLoot)
    {
        //for each item in the list of loot items
        for(String loot : addLoot)
        {
            //add the item to player's loot
            this.loot.add(loot);
            //also filter
            if("Sword".equals(loot))
            {
                //if the loot item is a weapon, add it to player's weapons
                this.weapons.add(loot);
            }
            else
            {
                //if not, add to potions
                this.potions.add(loot);
                this.potionCount++;
            }
        }
    }
   
   // remove a potion when consumed
   public void removePotion(String potion) {
        this.potions.remove(potion);
        potionCount--;
    }
   
   // check if there is a potion on inventory or not
   public boolean checkPotion()
   {
       if(potionCount == 0)
       {
           getInventoryList();
           return false;
       }
       else if(potionCount >= 0)
       {
           getInventoryList();
       }
       return true;
   }

    public String displayInventory() {
        return this.PlayerName + "'s Inventory: \n" + "Weapons: " + weapons + "\n" + "Potions " + potions + "\n";
    }
    
    public String displayPlayerInfo(){
        return "Player Name: " + this.PlayerName + "\nPlayer Health: " + this.PlayerHp;
    }
    
    //Display gameOver when player hp reaches 0
    public void GameOver() {
       if (PlayerHp <= 0){
          System.out.println("Game Over");
       }
    }
}
