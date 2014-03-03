/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.test;

import com.game.models.Armour;
import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.Item;
import com.game.models.Player;
import com.game.models.Ring;
import com.game.ui.views.CharachterEditorPanel;
import com.game.ui.views.WeaponEditorPanel;
import com.game.util.GameUtils;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Kaushik
 */
public class Test extends JFrame{
    public Test()
    {
        doGui();
    }
    public void doGui()
    {
        init();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            //        add(new CharachterEditorPanel());
            GameBean.itemDetails = GameUtils.getAllItems();
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        add(new WeaponEditorPanel());
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        setMaximizedBounds(env.getMaximumWindowBounds());  
        setVisible(true);
    }
    public static void main(String[] args) {
        System.out.println("hi..");
        HashMap<Player,Integer> map = new HashMap<Player,Integer>();
        Player p = new Player();
        p.setArmor(5);
        map.put(p, 1);
        System.out.println(p.hashCode());
        Player p1 = new Player();
        p1.setArmor(6);
        map.put(p1, 2);
        Player p2 = new Player();
        p2 .setArmor(7);
        System.out.println(map);
        System.out.println(map.get(p2));
       
        
        
        
//        Object a = null;
//        String x = (String)a;
//        System.out.println(x);
//        new Test();
//        ArrayList<Item> item = new ArrayList<Item>();
//        Ring ring = new Ring();
//        ring.setName("kaushik");
//        item.add(ring);
//        Armour armour = new Armour();
//        armour.setName("fuck u");
//        item.add(armour);
//        Ring ring1 = new Ring();
//        ring1.setName("kaushik");
//        armour = new Armour();
//        armour.setName("fuck u");
//        System.out.println(item.contains(armour));
                
    }
    public void init()
    {
        try {
            GameBean.enemyDetails = GameUtils.getCharacterDetailsFromFile(Configuration.PATH_FOR_USER_CHARACTERS);
            System.out.println(GameBean.enemyDetails);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
