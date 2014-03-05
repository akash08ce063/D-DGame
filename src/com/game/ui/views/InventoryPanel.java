/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.Armour;
import com.game.models.Configuration;
import com.game.models.Inventory;
import com.game.models.Item;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.Weapon;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author 韩信
 */
public class InventoryPanel extends JDialog implements ActionListener{
    public Inventory CharacterInventory = new Inventory();
    public ArrayList<Item> ItemsOfCharacter = new ArrayList<>();
    public Weapon CurrentWeapon = new Weapon();
    public HashMap<Integer,Item> InventoryButtonMap = new HashMap<>(); 
    public String ItemInformation = null;
    public int InventoryRow ;
    public int InventoryColumn;
    public int InventorySize;
    public JTextArea InformationLable;
    public JButton Equip;
    public JButton Unequip;
    public JButton Use;
    
    public InventoryPanel(Inventory Inventories){
        CharacterInventory = Inventories;
        InventoryRow = Configuration.INVENTORY_ROW;
        InventoryColumn = Configuration.INVENTORY_COLUMN;
        InventorySize = Configuration.INVENTORY_SIZE;
        InformationLable = new JTextArea();
        Equip = new JButton("Equip");
        Unequip = new JButton("Unequip");
        Use = new JButton("Use");
        putInventoriesIntoItem();
        initUI();
        //ItemInformation = "name:\n" + "Damage:\n" + "AttackRange:\n";
        //initUI();
    }
    
     public void initUI(){
         JPanel topPanel = new JPanel();
         JPanel bottomPanel = new JPanel();
         JPanel RightBottomPanel = new JPanel();
         JPanel basicPanel = new JPanel();
         JPanel[] ButtonPanel = new JPanel[59];
         basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
         
         JButton[] Buttons = new JButton[InventorySize];
         JTextArea Money = new JTextArea("Gold:" + CharacterInventory.getTotGold().toString());
         
         Equip.setEnabled(false);
         Equip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                CharacterInventory.setEquippedWeapon(CurrentWeapon);
                Equip.setEnabled(false);
                Unequip.setEnabled(true);
            }
        });
         
         Unequip.setEnabled(false);
         Unequip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Weapon nullWeapon = new Weapon();
                CharacterInventory.setEquippedWeapon(nullWeapon);
                Equip.setEnabled(true);
                Unequip.setEnabled(false);
            }
        });
         
         Use.setEnabled(false);
         
         RightBottomPanel.setLayout(new GridLayout(4, 1, 5, 5));
         RightBottomPanel.add(Money);
         RightBottomPanel.add(Equip);
         RightBottomPanel.add(Unequip);
         RightBottomPanel.add(Use);
         
         
         JScrollPane Pane = new JScrollPane();
        
         InformationLable.setPreferredSize(new Dimension(400,100));
         
         Pane.setPreferredSize(new Dimension(450,450));
         
         topPanel.setLayout(new GridLayout(InventoryRow, InventoryColumn, 0, 0));
         
         Iterator it = ItemsOfCharacter.iterator();
         int i = 0;
         for(; i < ItemsOfCharacter.size(); i++){
             Item in = (Item)it.next();
             InventoryButtonMap.put(i,in);
             ButtonPanel[i] = new JPanel();
             Buttons[i] = new JButton();
             if(in instanceof Weapon){
                Weapon w = new Weapon();
                w = (Weapon)in;
                Buttons[i].setText(w.getName());
                System.out.println(w.getName());
                Buttons[i].setActionCommand(Integer.toString(i));
                Buttons[i].addActionListener(this);
                Buttons[i].setPreferredSize(new Dimension(90,50));
                ButtonPanel[i].add(Buttons[i]);            
                topPanel.add(ButtonPanel[i]);
             }else if ( in instanceof Armour){
                 Armour a = new Armour();
                 a = (Armour)in;
                 Buttons[i].setText(a.getName());
                 Buttons[i].setActionCommand(Integer.toString(i));
                 Buttons[i].addActionListener(this);
                 Buttons[i].setPreferredSize(new Dimension(90,50));
                 ButtonPanel[i].add(Buttons[i]);            
                 topPanel.add(ButtonPanel[i]);
             }else if ( in instanceof Potion){
                 Potion p = new Potion();
                 p = (Potion)in;
                 Buttons[i].setText(p.getName());
                 Buttons[i].setActionCommand(Integer.toString(i));
                 Buttons[i].addActionListener(this);
                 Buttons[i].setPreferredSize(new Dimension(90,50));
                 ButtonPanel[i].add(Buttons[i]);            
                 topPanel.add(ButtonPanel[i]);
             }else if ( in instanceof Ring){
                 Ring r = new Ring();
                 r = (Ring)in;
                 Buttons[i].setText(r.getName());
                 Buttons[i].setActionCommand(Integer.toString(i));
                 Buttons[i].addActionListener(this);
                 Buttons[i].setPreferredSize(new Dimension(90,50));
                 ButtonPanel[i].add(Buttons[i]);            
                 topPanel.add(ButtonPanel[i]);
             } 
         }
         
         for(; i < InventorySize; i++){
             Buttons[i] = new JButton();
             ButtonPanel[i] = new JPanel();
             Buttons[i].setPreferredSize(new Dimension(90,50));
             ButtonPanel[i].add(Buttons[i]);            
             topPanel.add(ButtonPanel[i]);
         }
         Pane.getViewport().add(topPanel);
         
         bottomPanel.add(InformationLable);
         bottomPanel.add(RightBottomPanel);
         
         basicPanel.add(Pane);
         basicPanel.add(bottomPanel);
         add(basicPanel);
        setTitle("Inventory Panel");
        setSize(550,800);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true); 
     }
    
    public String makingInformationOfWeapon(Weapon W){
        String AttackPts = Integer.toString(W.getAttackPts());
        String AttackRange = Integer.toString(W.getAttackRange());
        String WeaponName = W.getName();
        String WeaponType = W.getWeaponType();
        StringBuilder InformationSB = new StringBuilder();
        InformationSB.append("WeaponName: ");
        InformationSB.append(WeaponName);
        InformationSB.append("\n");
        InformationSB.append("WeaponType: ");
        InformationSB.append(WeaponType);
        InformationSB.append("\n");
        InformationSB.append("AttackRange: ");
        InformationSB.append(AttackRange);
        InformationSB.append("\n");
        InformationSB.append("AttackPts: ");
        InformationSB.append(AttackPts);
        InformationSB.append("\n");
        String Information = InformationSB.toString();
        return Information;
    }
    
    public String makingInformationOfArmor(Armour A){
        String ArmourPts = Integer.toString(A.getArmourPts());
        String ArmourName = A.getName();
        StringBuilder InformationSB = new StringBuilder();
        InformationSB.append("ArmourName: ");
        InformationSB.append(ArmourName);
        InformationSB.append("\n");
        InformationSB.append("ArmourClass: ");
        InformationSB.append(ArmourPts);
        InformationSB.append("\n");
        String Information = InformationSB.toString();
        return Information;
    }
    
    public void putInventoriesIntoItem(){
        Armour A1 = CharacterInventory.getBoot();
        Armour A2 = CharacterInventory.getHelmet();
        LinkedList<Ring> R = CharacterInventory.getRing();
        Weapon W = CharacterInventory.getEquippedWeapon();
        ItemsOfCharacter.add(W);
        ItemsOfCharacter.add(A1);
        ItemsOfCharacter.add(A2);
        Iterator it = R.iterator();
        while(it.hasNext()){
            ItemsOfCharacter.add((Item)it.next());
        }
    }
    
    public String makingInformationForOthers(String name){
        String information = "Name: " + name + "\n";
        return information;
    }
    
    @Override
    public void actionPerformed(ActionEvent event){
        int command = Integer.parseInt(event.getActionCommand());
        System.out.println(command);
        Item item = InventoryButtonMap.get(command);
        
        if(item instanceof Weapon){
            Weapon w = (Weapon) item;
            InformationLable.setText(makingInformationOfWeapon(w));
            if (w.getName() != CharacterInventory.getEquippedWeapon().getName() || CharacterInventory.getEquippedWeapon().getName() == null){
                Equip.setEnabled(true);
                Unequip.setEnabled(true);
                Use.setEnabled(false);
                CurrentWeapon = w;
            }else{
                Equip.setEnabled(false);
                Unequip.setEnabled(true);
                Use.setEnabled(false);
            }
        }else if (item instanceof Armour) {
            Armour a = (Armour) item;
            InformationLable.setText(makingInformationOfArmor(a));
            Equip.setEnabled(true);
            Unequip.setEnabled(true);
            Use.setEnabled(false);
        }else if (item instanceof Ring) {
            Ring r = (Ring) item;
            InformationLable.setText(makingInformationForOthers(r.getName()));
            Equip.setEnabled(true);
            Unequip.setEnabled(true);
            Use.setEnabled(false);
        }else if (item instanceof Potion){
            Potion p = (Potion) item;
            InformationLable.setText(makingInformationForOthers(p.getName()));
            Equip.setEnabled(false);
            Unequip.setEnabled(false);
            Use.setEnabled(true);
        }
    }
    
      public static void main(String[] args) {
		// TODO Auto-generated method stub
          final Inventory in = new Inventory();
          Weapon w = new Weapon();
          w.setAttackPts(12);
          w.setAttackRange(2);
          w.setName("Sword");
          w.setWeaponType("Meele");
          
          Weapon w2 = new Weapon();
          w2.setAttackPts(10);
          w2.setAttackRange(5);
          w2.setName("LongBow");
          w2.setWeaponType("Range");
          
          Ring r = new Ring();
          r.setName("bigRing");
          
          Ring r2 = new Ring();
          r.setName("smallRing");
          
          Armour a = new Armour();
          a.setArmourPts(2);
          a.setName("Boot");
          
          LinkedList<Ring> ring = new LinkedList<>();
          ring.add(r);
          ring.add(r2);
          
          in.setBoot(a);
          in.setEquippedWeapon(w2);
          in.setRing(ring);
          in.setTotGold(new Long(178972));
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	InventoryPanel ex = new InventoryPanel(in);
	                ex.setVisible(true);
                        //System.out.println("lllll" + ex.CharacterInventory.getEquippedWeapon().getName());
	            }
	        });
	}
}
