/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

/**
 * Saving and Loading Player Stats
 * @author wangs
 */
import java.io.Serializable;
import game.entity.Entity;
import game.entity.Player;
import java.util.ArrayList;

public class DataStorage implements Serializable{
    //Player Stats
     int hp;
     
     //Player inventory
     ArrayList<String> itemNames = new ArrayList<>();
}
