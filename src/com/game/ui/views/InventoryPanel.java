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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *this class is for creating inventory panel 
 * @author 韩信
 */
public class InventoryPanel extends JDialog implements ActionListener{
    private int inventoryRow ;
    private int inventoryColumn;
    private int inventorySize;
    //public  static final Inventory in = new Inventory();
    
    public Inventory characterInventory = new Inventory();
    public ArrayList<Item> itemsOfCharacter = new ArrayList<>();
    public Weapon currentWeapon = new Weapon();
    public HashMap<Integer,Item> inventoryButtonMap = new HashMap<>(); 
    public String itemInformation = null;
    public JTextArea informationLable;
    public JButton equip;
    public JButton unequip;
    public JButton use;
    
    public InventoryPanel(Inventory Inventories){
        characterInventory = Inventories;
        System.out.println("Item: " + Inventories.getItems());
        inventoryRow = Configuration.INVENTORY_ROW;
        inventoryColumn = Configuration.INVENTORY_COLUMN;
        inventorySize = Configuration.INVENTORY_SIZE;
        informationLable = new JTextArea();
        informationLable.setEditable(false);
        equip = new JButton("Equip");
        unequip = new JButton("Unequip");
        use = new JButton("Use");
        putInventoriesIntoItem();
        initUI();
        //ItemInformation = "name:\n" + "Damage:\n" + "AttackRange:\n";
        //initUI();
    }
    
    /**
     * this method is to initiate inventory panel UI
     */
     public void initUI(){
         JPanel topPanel = new JPanel();
         JPanel bottomPanel = new JPanel();
         JPanel RightBottomPanel = new JPanel();
         JPanel basicPanel = new JPanel();
         JPanel[] ButtonPanel = new JPanel[59];
         basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
         
         JButton[] Buttons = new JButton[inventorySize];
         
         JTextArea Money;
         if(characterInventory.getTotGold() != null){
            Money = new JTextArea("Gold:" + characterInventory.getTotGold().toString());
         }else{
            Money = new JTextArea("Gold: ");
         }
         Money.setEditable(false);
         
         equip.setEnabled(false);
         equip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                characterInventory.setEquippedWeapon(currentWeapon);
                MapPanel.inventory = characterInventory;
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }
        });
         
         unequip.setEnabled(false);
         unequip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Weapon nullWeapon = new Weapon();
                LinkedList<Item> li = characterInventory.getItems();
                if(li != null){
                    li.add(characterInventory.getEquippedWeapon());
                }else{
                    li = new LinkedList<>();
                    li.add(characterInventory.getEquippedWeapon());
                }
                characterInventory.setEquippedWeapon(nullWeapon);
                characterInventory.setItems(li);
                System.out.println("add item" + li);
                MapPanel.inventory = characterInventory;
                System.out.println("add item" + MapPanel.inventory.getItems());
                System.out.println("wtf");
                equip.setEnabled(true);
                unequip.setEnabled(false);
            }
        });
         
         use.setEnabled(false);
         
         RightBottomPanel.setLayout(new GridLayout(4, 1, 5, 5));
         RightBottomPanel.add(Money);
         RightBottomPanel.add(equip);
         RightBottomPanel.add(unequip);
         RightBottomPanel.add(use);
         
         
         JScrollPane Pane = new JScrollPane();
        
         informationLable.setPreferredSize(new Dimension(400,100));
         
         Pane.setPreferredSize(new Dimension(450,450));
         
         topPanel.setLayout(new GridLayout(inventoryRow, inventoryColumn, 0, 0));
         
         Iterator it = itemsOfCharacter.iterator();
         int i = 0;
         for(; i < itemsOfCharacter.size(); i++){
             Item in = (Item)it.next();
             inventoryButtonMap.put(i,in);
             ButtonPanel[i] = new JPanel();
             Buttons[i] = new JButton();
             if(in instanceof Weapon){
                Weapon w = new Weapon();
                w = (Weapon)in;
                Buttons[i].setText(w.getName());
                System.out.println(w.getName());
                Buttons[i].setActionCommand(Integer.toString(i));
                Buttons[i].addActionListener(this);
                Buttons[i].setPreferredSize(new Dimension(130,50));
                ButtonPanel[i].add(Buttons[i]);            
                topPanel.add(ButtonPanel[i]);
             }else if ( in instanceof Armour){
                 Armour a = new Armour();
                 a = (Armour)in;
                 Buttons[i].setText(a.getName());
                 Buttons[i].setActionCommand(Integer.toString(i));
                 Buttons[i].addActionListener(this);
                 Buttons[i].setPreferredSize(new Dimension(130,50));
                 ButtonPanel[i].add(Buttons[i]);            
                 topPanel.add(ButtonPanel[i]);
             }else if ( in instanceof Potion){
                 Potion p = new Potion();
                 p = (Potion)in;
                 Buttons[i].setText(p.getName());
                 Buttons[i].setActionCommand(Integer.toString(i));
                 Buttons[i].addActionListener(this);
                 Buttons[i].setPreferredSize(new Dimension(130,50));
                 ButtonPanel[i].add(Buttons[i]);            
                 topPanel.add(ButtonPanel[i]);
             }else if ( in instanceof Ring){
                 Ring r = new Ring();
                 r = (Ring)in;
                 Buttons[i].setText(r.getName());
                 Buttons[i].setActionCommand(Integer.toString(i));
                 Buttons[i].addActionListener(this);
                 Buttons[i].setPreferredSize(new Dimension(130,50));
                 ButtonPanel[i].add(Buttons[i]);            
                 topPanel.add(ButtonPanel[i]);
             } 
         }
         
         for(; i < inventorySize; i++){
             Buttons[i] = new JButton();
             ButtonPanel[i] = new JPanel();
             Buttons[i].setPreferredSize(new Dimension(130,50));
             ButtonPanel[i].add(Buttons[i]);            
             topPanel.add(ButtonPanel[i]);
         }
         Pane.getViewport().add(topPanel);
         
         bottomPanel.add(informationLable);
         bottomPanel.add(RightBottomPanel);
         
         basicPanel.add(Pane);
         basicPanel.add(bottomPanel);
         add(basicPanel);
         setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Inventory Panel");
        setSize(550,800);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        //setLocationRelativeTo(frame);
        setVisible(true); 
     }
    
     /**
      * this method is used to make the weapon information
      * @param W the weapon which is used to make information
      * @return the information of the weapon
      */
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
    
    /**
     * this method is used to make information for armor
     * @param A an object of armor 
     * @return information of armor
     */
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
    
    /**
     * this method is put all itmes of CharacterInventory into an arrylist of itme
     */
    public void putInventoriesIntoItem(){
        Armour A1 = characterInventory.getBoot();
        Armour A2 = characterInventory.getHelmet();
        LinkedList<Ring> R = characterInventory.getRing();
        Weapon W = characterInventory.getEquippedWeapon();
        LinkedList<Item> I = characterInventory.getItems();
        System.out.println("item:" + I);
        if(A1 != null){
            itemsOfCharacter.add(A1);
        }
        
        if(W != null){
            itemsOfCharacter.add(W);
        }
        
        if(A2 != null){
            itemsOfCharacter.add(A2);
        }
        
        if(R != null){
            Iterator it = R.iterator();
        while(it.hasNext()){
            Ring r = (Ring)it.next();
            if(r != null){
                itemsOfCharacter.add((Item)r);
            }
        }
        }
        
        if(I != null){
            Iterator it = I.iterator();
        while(it.hasNext()){
            Item item = (Item)it.next();
            if(item != null){
                if (item instanceof Weapon){
                Weapon w = (Weapon)item;
                itemsOfCharacter.add(w);
                System.out.println("have a weapon");
            }
            }
        }
        }
    }
    
    /**
     * this method is used to make information for other items
     * @param name
     * @return the information of item
     */
    public String makingInformationForOthers(String name){
        String information = "Name: " + name + "\n";
        return information;
    }
    
    @Override
    public void actionPerformed(ActionEvent event){
        int command = Integer.parseInt(event.getActionCommand());
        System.out.println(command);
        Item item = inventoryButtonMap.get(command);
        
        if(item instanceof Weapon){
            Weapon w = (Weapon) item;
            informationLable.setText(makingInformationOfWeapon(w));
            if (w.getName() != characterInventory.getEquippedWeapon().getName() || characterInventory.getEquippedWeapon().getName() == null){
                equip.setEnabled(true);
                unequip.setEnabled(false);
                use.setEnabled(false);
                currentWeapon = w;
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(true);
                use.setEnabled(false);
            }
        }else if (item instanceof Armour) {
            Armour a = (Armour) item;
            informationLable.setText(makingInformationOfArmor(a));
            equip.setEnabled(true);
            unequip.setEnabled(true);
            use.setEnabled(false);
        }else if (item instanceof Ring) {
            Ring r = (Ring) item;
            informationLable.setText(makingInformationForOthers(r.getName()));
            equip.setEnabled(true);
            unequip.setEnabled(true);
            use.setEnabled(false);
        }else if (item instanceof Potion){
            Potion p = (Potion) item;
            informationLable.setText(makingInformationForOthers(p.getName()));
            equip.setEnabled(false);
            unequip.setEnabled(false);
            use.setEnabled(true);
        }
    }
    
      public static void main(String[] args) {
		// TODO Auto-generated method stub
         Inventory in = new Inventory();
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
          InventoryPanel ex = new InventoryPanel(in);
          System.out.println("current weapon:" + in.getEquippedWeapon().getName());
	}
}
