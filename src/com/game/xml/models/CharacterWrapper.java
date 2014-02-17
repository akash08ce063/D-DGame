/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.xml.models;

import com.game.models.GameCharacter;
import com.game.models.Player;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Kaushik
 */
@XmlRootElement(name = "characters")
@XmlSeeAlso(Player.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterWrapper {
    @XmlElementWrapper(name = "character")
    private ArrayList<GameCharacter> character = new ArrayList<GameCharacter>();

    public ArrayList<GameCharacter> getCharacter() {
        return character;
    }

    public void setCharacter(ArrayList<GameCharacter> character) {
        this.character = character;
    }
    
}
