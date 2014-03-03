/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.models.Armour;
import com.game.models.GameBean;
import com.game.models.Item;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.Treasure;
import com.game.models.Weapon;
import com.game.util.GameUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kaushik
 */
public class ItemPanel extends JPanel implements ActionListener {

    private String type = null;
    boolean ringPanel = false;
    boolean armourPanel = false;
    boolean potionPanel = false;
    boolean treasurePanel = false;
    private JComboBox comboBox = null;
    private JLabel validationMess = null;

    public ItemPanel(String type) {
        this.type = type;
        if ("Ring".equalsIgnoreCase(type)) {
            ringPanel = true;
        } else if ("Armour".equalsIgnoreCase(type)) {
            armourPanel = true;
        } else if ("Potion".equalsIgnoreCase(type)) {
            potionPanel = true;
        } else if ("Treasure".equalsIgnoreCase(type)) {
            treasurePanel = true;
        } else {
            ringPanel = true;
            type = "Ring";
        }
    }

    public void doGui() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel noteLbl = new JLabel("<html><div style='width : 500px;'>Pls select a value from the dropdown or you can create a new "
                + "entity below. Once selected an Item, its' details will be available below</div></html>");
        noteLbl.setAlignmentX(0);
        add(noteLbl);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        if (ringPanel) {
            for (Item item : GameBean.itemDetails) {
                if (item instanceof Ring) {
                    model.addElement(((Ring) item).getName());
                }
            }
        } else if (armourPanel) {
            for (Item item : GameBean.itemDetails) {
                if (item instanceof Armour) {
                    model.addElement(((Armour) item).getName());
                }
            }
        } else if (potionPanel) {
            for (Item item : GameBean.itemDetails) {
                if (item instanceof Potion) {
                    model.addElement(((Potion) item).getName());
                }
            }
        } else if (treasurePanel) {
            for (Item item : GameBean.itemDetails) {
                if (item instanceof Treasure) {
                    model.addElement(((Treasure) item).getName());
                }
            }
        }
    }

    public void doCommonStuffForDropDown(DefaultComboBoxModel model) {
        comboBox = new JComboBox(model);
        comboBox.setSelectedIndex(-1);
        comboBox.setMaximumSize(new Dimension(100, 30));
        comboBox.setAlignmentX(0);
        comboBox.setActionCommand("dropDown");
        comboBox.addActionListener(this);
        add(Box.createVerticalStrut(10));
        add(comboBox);
        add(Box.createVerticalStrut(10));
    }

    public void doCommonStuffForContent() {
        JPanel panel1 = new JPanel();
        panel1.setAlignmentX(0);
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        JLabel dtlLbl = new JLabel(type + "Details : ");
        dtlLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(dtlLbl, c);
        c.gridwidth = 1;
        c.gridy = 1;
        JLabel nameLbl = new JLabel("Name : ");
        panel1.add(nameLbl, c);
        c.gridx = 1;
        JTextField name = new JTextField("");
        name.setColumns(20);
        panel1.add(name, c);
        if (ringPanel) {
            createComponentsForRing(panel1, c);
        } else if (armourPanel) {
            createComponentsForArmour(panel1, c);
        } else if (potionPanel) {
            createComponentsForPotion(panel1, c);
        } else if (treasurePanel) {
            createComponentsForTreasure(panel1, c);
        }
        c.gridx = 0;
        c.gridy = c.gridy + 1;
        c.gridwidth = 2;
        JButton submit = new JButton("Save");
        submit.addActionListener(this);
        submit.setActionCommand("button");
        panel1.add(submit, c);
        c.gridx = 0;
        c.gridy = c.gridy + 1;
        c.gridwidth = 2;
        c.weighty = 0;
        c.weightx = 1;
        validationMess = new JLabel("Pls enter all the fields or pls choose a " + type + " from the drop down");
        validationMess.setForeground(Color.red);
        validationMess.setVisible(false);
        panel1.add(validationMess, c);
        panel1.setBorder(LineBorder.createGrayLineBorder());
        add(panel1);
        add(Box.createVerticalGlue());
    }

    public void createComponentsForRing(JPanel panel1, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 2;
        JLabel incHealth = new JLabel("Health Increase Pts");
        incHealth.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(incHealth, c);
        c.gridx = 1;
        JTextField incHealthTxt = new JTextField("");
        incHealthTxt.setColumns(20);
        panel1.add(incHealthTxt, c);
        c.gridx = 0;
        c.gridy = 3;
        JLabel incArmour = new JLabel("Armour Increase Pts");
        incArmour.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(incArmour, c);
        c.gridx = 1;
        JTextField incArmourTxt = new JTextField("");
        incArmourTxt.setColumns(20);
        panel1.add(incArmourTxt, c);
        c.gridy = 4;
        c.gridx = 0;
        JLabel incAttack = new JLabel("Attack Increase Pts");
        incAttack.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(incAttack, c);
        c.gridx = 1;
        JTextField incAttackTxt = new JTextField("");
        incAttackTxt.setColumns(20);
        panel1.add(incAttackTxt, c);
    }

    public void createComponentsForPotion(JPanel panel1, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 2;
        JLabel potionPts = new JLabel("Potion Pts");
        potionPts.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(potionPts, c);
        c.gridx = 1;
        JTextField potionPtsTxt = new JTextField("");
        potionPtsTxt.setColumns(20);
        panel1.add(potionPtsTxt, c);
    }

    public void createComponentsForArmour(JPanel panel1, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 2;
        JLabel armourPts = new JLabel("Armour Pts");
        armourPts.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(armourPts, c);
        c.gridx = 1;
        JTextField armourPtsTxt = new JTextField("");
        armourPtsTxt.setColumns(20);
        panel1.add(armourPtsTxt, c);
    }

    public void createComponentsForTreasure(JPanel panel1, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 2;
        JLabel treasureVal = new JLabel("Treasure Value");
        treasureVal.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(treasureVal, c);
        c.gridx = 1;
        JTextField treasureValTxt = new JTextField("");
        treasureValTxt.setColumns(20);
        panel1.add(treasureValTxt, c);
    }

    public void getRingDetailForName(String name, JPanel panel) {
        for (Item item : GameBean.itemDetails) {
            if (item instanceof Ring) {
                Ring ring = (Ring) item;
                if (ring.getName().equalsIgnoreCase(name)) {
                    ((JTextField) panel.getComponent(2)).setText(name);
                    ((JComboBox) panel.getComponent(4)).setSelectedItem(ring.getIncHealth());
                    ((JTextField) panel.getComponent(6)).setText(Integer.toString(ring.getIncArmour()));
                    ((JTextField) panel.getComponent(8)).setText(Integer.toString(ring.getIncAttack()));
                    return;
                }
            }
        }
    }

    public void getArmourDetailForName(String name, JPanel panel) {
        for (Item item : GameBean.itemDetails) {
            if (item instanceof Armour) {
                Armour armour = (Armour) item;
                if (armour.getName().equalsIgnoreCase(name)) {
                    ((JTextField) panel.getComponent(2)).setText(name);
                    ((JComboBox) panel.getComponent(4)).setSelectedItem(armour.getArmourPts());
                    return;
                }
            }
        }
    }

    public void getTreasureDetailForName(String name, JPanel panel) {
        for (Item item : GameBean.itemDetails) {
            if (item instanceof Treasure) {
                Treasure treasure = (Treasure) item;
                if (treasure.getName().equalsIgnoreCase(name)) {
                    ((JTextField) panel.getComponent(2)).setText(name);
                    ((JComboBox) panel.getComponent(4)).setSelectedItem(treasure.getValue());
                    return;
                }
            }
        }
    }

    public void getPotionDetailForName(String name, JPanel panel) {
        for (Item item : GameBean.itemDetails) {
            if (item instanceof Potion) {
                Potion potion = (Potion) item;
                if (potion.getName().equalsIgnoreCase(name)) {
                    ((JTextField) panel.getComponent(2)).setText(name);
                    ((JComboBox) panel.getComponent(4)).setSelectedItem(potion.getPotionPts());
                    return;
                }
            }
        }
    }

    public void persistRingData(String name, JPanel panel) {
        String incHealth = ((JTextField) panel.getComponent(4)).getText();
        String incArmour = ((JTextField) panel.getComponent(6)).getText();
        String incAttack = ((JTextField) panel.getComponent(8)).getText();
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(incHealth) && StringUtils.isNotBlank(incArmour) && StringUtils.isNotBlank(incAttack)) {
            int position = GameUtils.getPositionOfRingItem(name);
            if (position != -1) {
                GameBean.itemDetails.remove(position);
            } else {
                Ring ring = new Ring();
                ring.setName(name);
                ring.setIncArmour(Integer.parseInt(incArmour));
                ring.setIncHealth(Integer.parseInt(incHealth));
                ring.setIncAttack(Integer.parseInt(incAttack));
                boolean result = persistItemDetails(ring);
                if(result){
                    if(position == -1){
                        comboBox.addItem(name);
                        comboBox.setSelectedItem(name);
                    }
                }
                else{
                    validationMess.setText("Some error occured...");
                    validationMess.setVisible(true);
                }
            }
        }
        else{
            validationMess.setText("Pls enter all the fields or pls choose a weapon from the drop down");
            validationMess.setVisible(true);
        }
        this.revalidate();
    }
    public void persistArmourData(String name, JPanel panel) {
        String armourPts = ((JTextField) panel.getComponent(4)).getText();
        if (StringUtils.isNotBlank(armourPts) && StringUtils.isNotBlank(name)) {
            int position = GameUtils.getPositionOfArmourItem(name);
            if (position != -1) {
                GameBean.itemDetails.remove(position);
            } else {
                Armour armour = new Armour();
                armour.setName(name);
                armour.setArmourPts(Integer.parseInt(armourPts));
                boolean result = persistItemDetails(armour);
                if(result){
                    if(position == -1){
                        comboBox.addItem(name);
                        comboBox.setSelectedItem(name);
                    }
                }
                else{
                    validationMess.setText("Some error occured...");
                    validationMess.setVisible(true);
                }
            }
        }
        else{
            validationMess.setText("Pls enter all the fields or pls choose a weapon from the drop down");
            validationMess.setVisible(true);
        }
        this.revalidate();
    }
    public void persistPotionData(String name, JPanel panel) {
        String armourPts = ((JTextField) panel.getComponent(4)).getText();
        if (StringUtils.isNotBlank(armourPts) && StringUtils.isNotBlank(name)) {
            int position = GameUtils.getPositionOfArmourItem(name);
            if (position != -1) {
                GameBean.itemDetails.remove(position);
            } else {
                Armour armour = new Armour();
                armour.setName(name);
                armour.setArmourPts(Integer.parseInt(armourPts));
                boolean result = persistItemDetails(armour);
                if(result){
                    if(position == -1){
                        comboBox.addItem(name);
                        comboBox.setSelectedItem(name);
                    }
                }
                else{
                    validationMess.setText("Some error occured...");
                    validationMess.setVisible(true);
                }
            }
        }
        else{
            validationMess.setText("Pls enter all the fields or pls choose a weapon from the drop down");
            validationMess.setVisible(true);
        }
        this.revalidate();
    }

    public boolean persistItemDetails(Item item) {
        boolean retValue = false;
        try {
            GameBean.itemDetails.add(item);
            GameUtils.writeItemsToXML(GameBean.itemDetails);
            validationMess.setText("Saved Successfully..");
            validationMess.setVisible(true);
//            this.revalidate();
            retValue = true;
        } catch (Exception e) {
            System.out.println("ItemPanel : persisitItemDetails() : Some error occured " + e);
        }
        return retValue;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("dropDown")) {
            JPanel panel = (JPanel) comboBox.getParent().getComponent(4);
            String name = comboBox.getSelectedItem().toString();
            if (ringPanel) {
                getRingDetailForName(name, panel);
            } else if (armourPanel) {
                getArmourDetailForName(name, panel);
            } else if (potionPanel) {
                getPotionDetailForName(name, panel);
            } else if (treasurePanel) {
                getTreasureDetailForName(name, panel);
            }
        } else {
            JButton btn = (JButton) ae.getSource();
            JPanel panel = (JPanel) btn.getParent();
            String name = ((JTextField) panel.getComponent(2)).getText();

            String weaponType = (String) (((JComboBox) panel.getComponent(4)).getSelectedItem());
            String attackRnge = ((JTextField) panel.getComponent(6)).getText();
            String attackPts = ((JTextField) panel.getComponent(8)).getText();
//            JLabel message = ((JLabel) this.getComponent(5));
            validationMess.setText("");
            validationMess.setVisible(false);
            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(weaponType) && StringUtils.isNotBlank(attackRnge)
                    && StringUtils.isNotBlank(attackPts)) {
                validationMess.setVisible(false);
                Weapon weapon = new Weapon();
                weapon.setName(name);
                weapon.setAttackRange(Integer.parseInt(attackRnge));
                weapon.setAttackPts(Integer.parseInt(attackPts));
                weapon.setWeaponType(weaponType);
                boolean weaponAlrdyPresent = false;
                for (int i = 0; i < GameBean.itemDetails.size(); i++) {
                    Item item = GameBean.itemDetails.get(i);
                    if (item instanceof Weapon) {
                        Weapon wpn = (Weapon) item;
                        if (wpn.getName().equalsIgnoreCase(name)) {
                            GameBean.itemDetails.remove(i);
                            GameBean.itemDetails.add(i, weapon);
                            weaponAlrdyPresent = true;
                            break;
                        }
                    }
                }
                if (!weaponAlrdyPresent) {
                    GameBean.itemDetails.add(weapon);
                }
                try {
                    GameUtils.writeItemsToXML(GameBean.itemDetails);
                    validationMess.setText("Saved Successfully..");
                    validationMess.setVisible(true);
                    if (!weaponAlrdyPresent) {
                        comboBox.addItem(name);
                        comboBox.setSelectedItem(name);
                    }
                    this.revalidate();
                    return;
                } catch (Exception e) {
                    System.out.println("WeaponEditorPanel : actionPerformed() : Some error occured " + e);
                }

            } else {
                validationMess.setText("Pls enter all the fields or pls choose a weapon from the drop down");
                validationMess.setVisible(true);
                panel.revalidate();
            }
        }
    }
}
