/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.main;
/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment1
 */
import game.interfaces.Function;
import game.entities.MobBattle;
import game.entities.Mobs;
import game.entities.Player;
import game.entities.Potions;
import game.entities.Weapon;
import game.maps.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class GameMenu {
    private static final String SaveFiles = "save_game.txt";
    private static Player player;
    private static Mobs mob;
    private static MobBattle mb;
    private static Potions potion;
    
    // instanciate the imported classes to allow to invoke their methods and variables in gameMenu class
    public GameMenu(Potions potion, Player player, MobBattle mb, Mobs mob){
        GameMenu.potion = potion;
        GameMenu.player = player;
        GameMenu.mob = mob;
        GameMenu.mb = mb;
    }

    public static void main(String[] args) {
        
        player = new Player("Noob", 100, new ArrayList<>(), new ArrayList<>(), "Noob", "RustySword", "potion"); //generate the player that the user will control
        
        System.out.println("Welcome to your isekai!");

        loadGameState(); // Load the saved game txt file if it exists
        
        System.out.println("Press M to open the Game Menu");

        Scanner scan = new Scanner(System.in);
        
        Map map = new Map(5, 5); //generate the size of the map
        boolean menuRunning = true;
        try{
            while (menuRunning) { //loops the menu every time player press m
                String openMenu = scan.nextLine();
                if (openMenu.equalsIgnoreCase("m")) {
                    System.out.println("Isekai Menu");
                    System.out.println("1. Player Info");
                    System.out.println("2. Inventory");
                    System.out.println("3. Show Map");
                    System.out.println("4. Move");
                    System.out.println("5. Save Game");
                    System.out.println("6. Load Game");
                    System.out.println("7. Exit Menu");

                    int userChoice = scan.nextInt();
                    scan.nextLine(); // Eats the newline character

                    switch (userChoice) {
                        case 1:
                            displayPlayerInformation(); //player information
                            break;
                        case 2:
                            displayPlayerInventory(); // items like weapons and potions will be displayed here
                            break;
                        case 3:
                            map.displayMap(); //render the map
                            break;
                        case 4:
                            System.out.print("Enter direction ('up', 'down', 'left', 'right'): ");
                            String direction = scan.nextLine(); //scan the player movement
                            handleMovement(map, direction); //moves the player across the map
                            break; // Don't forget to add a break statement here.
                        case 5:
                            saveGameState(); //save game
                            break;
                        case 6:
                            loadGameState(); //load the game
                            break;
                        case 7:
                            menuRunning = false; //close the game
                            break;
                        default:
                            System.out.println("Invalid choice. Please re-enter a valid option.");
                    }
                }
            }
            System.out.println("Leaving isekai. See you next time!");
            scan.close();
        }
        catch (InputMismatchException ex)
        {
            System.out.println("Input Invalid " + ex.getMessage());
        }
    }

    private static void displayPlayerInventory() {
        // Assuming you have a list of items in the player's inventory.
        // Replace 'inventoryList' with the actual variable that holds the player's inventory.
        System.out.println("Inventory: ");
        for (String item : player.getInventoryList()) {
            System.out.println(item);
        }
    }

    private static void displayPlayerInformation() {
        // Assuming you have methods to get player information in the 'Player' class.
        System.out.println("Player Information:");
        System.out.println("Name: " + player.getPlayerName());
        System.out.println("HP: " + player.getPlayerHp());
    }
    //creates a save game file
    private static void saveGameState() {
        try {
            FileWriter fw = new FileWriter(SaveFiles);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(player.getPlayerName() + "\n");
            bw.write(player.getPlayerHp() + "\n");
            bw.write(player.getEquippedWeapon()+ "\n");
            bw.write(player.displayInventory()+ "\n");

            // Assuming you have lists of weapons and potions in the 'Player' class.
            // Replace 'player.getWeapons()' and 'player.getPotions()' with the actual methods to get those lists.
            List<String> weapons = player.getWeapons();
            List<String> potions = player.getPotions();

            // Write weapons and potions to the file
            for (String weapon : weapons) {
                bw.write("weapon:" + weapon + "\n"); // Format the item as "weapon:item_name"
            }
            for (String potion : potions) {
                bw.write("potion:" + potion + "\n"); // Format the item as "potion:item_name"
            }
            bw.close();
            System.out.println("Game State Saved.");
        } catch (IOException ex) {
            System.out.println("Game State NOT Saved. " + ex.getMessage());
        }
    }
    //load the save game file
    private static void loadGameState() {
        try {
            File saveFile = new File(SaveFiles);
            if (!saveFile.exists()) {
                System.out.println("No Save File Found.");
                return;
            }

            Scanner fs = new Scanner(saveFile);
            String name = fs.nextLine();
            int hp = Integer.parseInt(fs.nextLine());

            // Assuming you have lists of weapons and potions in the 'Player' class.
            // Replace 'weapons' and 'potions' with the actual lists in the 'Player' class.
            List<String> weapons = new ArrayList<>();
            List<String> potions = new ArrayList<>();
            
            // Read weapons and potions from the file
            while (fs.hasNextLine()) {
                String item = fs.nextLine();
                // Check if the item is a weapon or a potion and add it to the corresponding list
                if (item.startsWith("weapon:")) {
                    weapons.add(item.substring(7)); // Assuming the item format is "weapon:item_name"
                } else if (item.startsWith("potion:")) {
                    potions.add(item.substring(7)); // Assuming the item format is "potion:item_name"
                }
            }

            fs.close();
            player = new Player(name, hp, weapons, potions,"", "", "");
            System.out.println("Game Save State Loaded.");

        } catch (IOException ex) {
            System.out.println("Unable To Load Game State. " + ex.getMessage());
        }
    }
    //will move the player across the map note: will be make as a thread in the future
    private static void handleMovement(Map map, String direction) {
        int x = map.playerXcord;
        int y = map.playerYcord;

        switch (direction) {
            case "up":
                x += -1;
                y += 0;
                break;
            case "down":
                x += 1;
                y += 0;
                break;
            case "left":
                x += 0;
                y += -1;
                break;
            case "right":
                x += 0;
                y += 1;
                break;
            default:
                System.out.println("Invalid direction. Try again.");
                return;
        }

        map.movePlayer(x, y);
        if (map.isCleared()) {
            System.out.println("You have already cleared this area!");
        } else {
            // Implement mob encounter and fight mechanics here
            System.out.println("You encountered a mob! Prepare for battle!");
            if (!map.isCleared()){
                startBattle();
                map.markCleared(); // Mark the area as cleared after defeating the mob
            }
        }
    }
    
    //method based in turn battles 
    private static void startBattle(){
        System.out.println("Battle started!");
        
        mob = new Mobs(50, 3, 1, "Goblin", "Axe", "Potion"); //creates a hostile mob
        
        while(player.isAlive() && mob.isAlive()) // while player and mob are alive, battle will continue
        {
            System.out.println("1. Attack");
            System.out.println("2. Inventory");
            
            Scanner scan = new Scanner(System.in);
            
            int choice = scan.nextInt();
            
            switch(choice){
                case 1: //player attack option
                    mb = new MobBattle(player, mob, "", "", ""); // will include player and the mob in the battle 
                    mb.playerAttack(); 
                    if(!mob.isAlive())
                    {
                        System.out.println("You defeated " + mob.getMobName() + "!");
                        List<String> loot = mob.lootDrop();
                        player.addLoot(loot);
                        return;
                    }
                    else {
                        System.out.println(mob.getMobName()+ "'s Hp is " + mob.getMobHealth());
                    }
                    mb.handleMobTurn();
                    break;
                case 2: //allow player to regenerate his hp, note it just regenerate one time per battle fight.
                    potion = new Potions(player, "Heal Potion", 30, "", "", ""); //give a potion to the player 
                    potion.usePotion(); //player will use potion 
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        if(!player.isAlive()) // player killed by the mob and game over
        {
            System.out.println("You were defeated by " + mob.getMobName() + "!");
            //Implement game over text or something,probs best to do it in player class
            player.GameOver();
        }  
        else {
            System.out.println(player.getPlayerName() + "'s Hp is " + player.getPlayerHp());  //update the player hp on player info
        }
    }
}