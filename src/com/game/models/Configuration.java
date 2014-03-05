/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

/**
 *
 * @author Kaushik
 */
public class Configuration {
    public static final int MAX_HEALTH = 1000;
    public static int MAX_ATTACK_RNGE = 0;
    public static int MAX_MOVEMENT = 0;
    public static final int MAX_ATTACK = 50;
    public static final int MAX_STRENGTH = 10;
    public static final int MAX_DEXTERITY = 10;
    public static final int MAX_VITALITY = 10;
    public static final int MAX_WISDOM = 10;
    public static final int MAX_LEVEL = 40;
    public static final int MAX_ARMOUR = 10;
    public static final int MAX_EXP = 50;
    public static final int MAX_INVENTORY = 10;
    public static final int ENEMIESTOBEKILLED = 3;
    public static final String PATH_FOR_USER_CHARACTERS = "./User Character/user.xml" ;
    public static final String PATH_FOR_ENEMY_CHARACTERS = "./Enemy Character/enemy.xml";
    public static final String PATH_FOR_ITEMS = "./items.xml";
    public static final String PATH_FOR_IMAGES = "./Images/";
    public static String pathForUserProgress = null;
    public static String weaponTypes[] = {"Melee Weapon","Ranged Weapon"};
    public static final String PATH_FOR_MAP = "./Map/map.xml";
    public static final int INVENTORY_ROW = 14;
    public static final int INVENTORY_COLUMN = 4;
    public static final int INVENTORY_SIZE = INVENTORY_ROW * INVENTORY_COLUMN;
}
