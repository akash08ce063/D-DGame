/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import com.game.util.GameUtils;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Kaushik
 */
public class GameBean {
    public static ArrayList<GameCharacter> enemyDetails = null;
    public static ArrayList<Player> playerDetails = null;
    public static ArrayList<Item> weaponDetails = null;
    public static ArrayList<Item> ringDetails = null;
    public static ArrayList<Item> potionDetails = null;
    public static ArrayList<Item> armourDetails = null;
    public static ArrayList<Item> treasureDetails = null;
    public static MapInformation mapInfo= null;
    
    public static void doInit() throws Exception{
        GameBean.enemyDetails = GameUtils.getCharacterDetailsFromFile(Configuration.PATH_FOR_ENEMY_CHARACTERS);
        File file = new File(Configuration.PATH_FOR_ARMOURS);
        if(file.exists()){
            GameBean.armourDetails = GameUtils.getAllItems(Configuration.PATH_FOR_ARMOURS);
        }
        else{
            GameBean.armourDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_TREASURES);
        if(file.exists()){
            GameBean.treasureDetails = GameUtils.getAllItems(Configuration.PATH_FOR_TREASURES);
        }
        else{
            GameBean.treasureDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_RINGS);
        if(file.exists()){
            GameBean.ringDetails = GameUtils.getAllItems(Configuration.PATH_FOR_RINGS);
        }
        else{
            GameBean.ringDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_POTIONS);
        if(file.exists()){
            GameBean.potionDetails = GameUtils.getAllItems(Configuration.PATH_FOR_POTIONS);
        }
        else{
            GameBean.potionDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_WEAPONS);
        if(file.exists()){
            GameBean.weaponDetails = GameUtils.getAllItems(Configuration.PATH_FOR_WEAPONS);
        }
        else{
            GameBean.weaponDetails  = new ArrayList<>();
        }
    }
}
