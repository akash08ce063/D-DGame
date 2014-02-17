/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.xml.main;

import com.game.models.Configuration;
import com.game.models.GameCharacter;
import com.game.models.Inventory;
import com.game.models.Player;
import com.game.xml.models.CharacterWrapper;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Kaushik
 */
public class UserCharacterInit {

    public static void main(String[] args) throws Exception{
        ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();
        Player player = new Player();
        player.setStrength(2);
        player.setName("Barbarian");
        player.setType("Barbarian");
        player.setDexterity(1);
        player.setExp(1);
        player.setAttackRange(1);
        player.setMovement(1);
        player.setHealth(50);
        player.setLevel(1);
        player.setArmor(1);
        player.setImagePath(""); // needs to be changed..
        player.setInventory(new Inventory());
        player.setWisdom(1);
        player.setAttackPts(2);
        player.setVitality(1);

        characters.add(player);

        player = new Player();
        player.setStrength(1);
        player.setType("Paladin");
        player.setDexterity(2);
        player.setExp(1);
        player.setAttackRange(2);
        player.setMovement(1);
        player.setHealth(50);
        player.setLevel(1);
        player.setArmor(1);
        player.setImagePath(""); // needs to be changed..
        player.setInventory(new Inventory());
        player.setWisdom(1);
        player.setAttackPts(1);
        player.setVitality(1);
        player.setName("Paladin");
        characters.add(player);

        player = new Player();
        player.setStrength(1);
        player.setType("Sorcerer");
        player.setDexterity(1);
        player.setExp(1);
        player.setAttackRange(1);
        player.setMovement(1);
        player.setHealth(50);
        player.setLevel(1);
        player.setArmor(2);
        player.setImagePath(""); // needs to be changed..
        player.setInventory(new Inventory());
        player.setWisdom(2);
        player.setAttackPts(1);
        player.setVitality(1);
        player.setName("Sorcerer");
        characters.add(player);

        player = new Player();
        player.setStrength(1);
        player.setType("Dwarf");
        player.setDexterity(1);
        player.setExp(1);
        player.setAttackRange(1);
        player.setMovement(1);
        player.setHealth(60);
        player.setLevel(1);
        player.setArmor(1);
        player.setImagePath(""); // needs to be changed..
        player.setInventory(new Inventory());
        player.setWisdom(1);
        player.setAttackPts(1);
        player.setVitality(2);
        player.setName("Dwarf");
        characters.add(player);

        CharacterWrapper wrapper = new CharacterWrapper();
        wrapper.setCharacter(characters);
        JAXBContext context = JAXBContext.newInstance(CharacterWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(wrapper, new File(Configuration.PATH_FOR_USER_CHARACTERS));
    }
}
