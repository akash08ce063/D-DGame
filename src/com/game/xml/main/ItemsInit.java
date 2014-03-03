/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.xml.main;

import com.game.models.Armour;
import com.game.models.Configuration;
import com.game.models.Item;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.Treasure;
import com.game.models.Weapon;
import com.game.xml.models.CharacterWrapper;
import com.game.xml.models.ItemWrapper;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Kaushik
 */
public class ItemsInit {
    public static void main(String[] args) throws JAXBException {
        ItemWrapper wrapper = new ItemWrapper();
        ArrayList<Item> list = new ArrayList<Item>();
        Weapon weapon = new Weapon();
        weapon.setName("Long Sword");
        weapon.setWeaponType("Melee Weapon");
        weapon.setAttackPts(2);
        weapon.setAttackRange(1);
        list.add(weapon);
        weapon = new Weapon();
        weapon.setName("Long Sword");
        weapon.setWeaponType("Ranged Weapon");
        weapon.setAttackPts(1);
        weapon.setAttackRange(2);
        list.add(weapon);
        Armour armour = new Armour();
        armour.setName("Helmet");
        armour.setArmourPts(1);
        list.add(armour);
        Ring ring = new Ring();
        ring.setIncArmour(1);
        ring.setIncAttack(1);
        ring.setIncHealth(1);
        ring.setName("Gold Ring");
        list.add(ring);
        Treasure treasure = new Treasure();
        treasure.setName("Large Gold");
        treasure.setValue(1000);
        list.add(treasure);
        Potion potion = new Potion();
        potion.setName("Large Potion");
        potion.setPotionPts(50);
        list.add(potion);
        wrapper.setItem(list);
        JAXBContext context = JAXBContext.newInstance(ItemWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(wrapper, new File(Configuration.PATH_FOR_ITEMS));
    }
    
}
