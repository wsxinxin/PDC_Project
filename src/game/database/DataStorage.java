/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */
import java.io.Serializable;

import java.util.ArrayList;

public class DataStorage implements Serializable{
    //Player Stats
     int hp;
     
     //Player inventory
     ArrayList<String> itemNames = new ArrayList<>();
}
