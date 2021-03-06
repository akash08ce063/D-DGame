/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TileInformation {
    private int location;
    private GameCharacter enemy;
    private Player player;
    private Potion potion;
    private Ring ring;
    private Treasure treasure;
    private Armour armour;
    private Weapon weapon;
    private boolean endTile;
    private boolean startTile;

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public GameCharacter getEnemy() {
        return enemy;
    }

    public void setEnemy(GameCharacter enemy) {
        this.enemy = enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public Ring getRing() {
        return ring;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    public Armour getArmour() {
        return armour;
    }

    public void setArmour(Armour armour) {
        this.armour = armour;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isEndTile() {
        return endTile;
    }

    public void setEndTile(boolean endTile) {
        this.endTile = endTile;
    }

    public boolean isStartTile() {
        return startTile;
    }

    public void setStartTile(boolean startTile) {
        this.startTile = startTile;
    }
       @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.location;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TileInformation other = (TileInformation) obj;
        if (this.location != other.location) {
            return false;
        }
        return true;
    }
}
