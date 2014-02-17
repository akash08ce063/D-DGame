/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.util.LinkedList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Inventory {
    private Weapon equippedWeapon;
    private Armour helmet;
    private LinkedList<Ring> ring;
    private Armour boot;
    private Long totGold;

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Armour getHelmet() {
        return helmet;
    }

    public void setHelmet(Armour helmet) {
        this.helmet = helmet;
    }

    public LinkedList<Ring> getRing() {
        return ring;
    }

    public void setRing(LinkedList<Ring> ring) {
        this.ring = ring;
    }

    public Armour getBoot() {
        return boot;
    }

    public void setBoot(Armour boot) {
        this.boot = boot;
    }

    public Long getTotGold() {
        return totGold;
    }

    public void setTotGold(Long totGold) {
        this.totGold = totGold;
    }
    
}
