/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.GameCharacter;
import com.game.models.Inventory;
import com.game.models.Item;
import com.game.models.Player;
import com.game.models.TileInformation;
import com.game.models.Weapon;
import com.game.util.GameUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kaushik
 */
public class PlayerEditor extends JPanel implements ActionListener {

    private JComboBox comboBox = null;
    private LevelPanel lvlPanel = null;
    private JPanel leftPanel = null;
    private JLabel validationMess = null;

    public PlayerEditor() {
        doGui();
    }

    public void doGui() {
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.NONE;
        c1.anchor = GridBagConstraints.WEST;
        c1.insets = new Insets(10, 5, 10, 5);
        c1.weightx = 0;
        c1.weighty = 0;
        JLabel noteLbl = new JLabel("Pls select a value to choose a Player or you can create a new "
                + "Player entity below. Once selected a Player character, its' details will be available below");
        add(noteLbl, c1);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (GameCharacter character : GameBean.playerDetails) {
            model.addElement(((Player) character).getType());
        }
        c1.gridy = 1;
        comboBox = new JComboBox(model);
        comboBox.setSelectedIndex(-1);
        comboBox.setMaximumSize(new Dimension(100, 30));
        comboBox.setActionCommand("dropDown");
        comboBox.addActionListener(this);
        add(comboBox, c1);
        JPanel wrapperPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        wrapperPanel.setBorder(LineBorder.createGrayLineBorder());
        leftPanel = new JPanel();
        leftPanel.setAlignmentX(0);
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        JLabel enemyDtlLbl = new JLabel("Enemy Character Details : ");
        enemyDtlLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
        leftPanel.add(enemyDtlLbl, c);
        c.gridwidth = 1;
        c.gridy = 1;
        JLabel nameLbl = new JLabel("Name : ");
        leftPanel.add(nameLbl, c);
        c.gridx = 1;
        JTextField name = new JTextField("");
        name.setColumns(20);
        leftPanel.add(name, c);
        c.gridx = 0;
        c.gridy = 2;
        JLabel imageLbl = new JLabel("Image Path : ");
        leftPanel.add(imageLbl, c);
        c.gridx = 1;
        JTextField image = new JTextField("");
        image.setColumns(20);
        leftPanel.add(image, c);
        c.gridx = 0;
        c.gridy = 3;
        JLabel healthLbl = new JLabel("Health Pts : ");
        leftPanel.add(healthLbl, c);
        c.gridx = 1;
        JTextField health = new JTextField("");
        health.setColumns(20);
        leftPanel.add(health, c);
        c.gridx = 0;
        c.gridy = 4;
        JLabel attackPtsLbl = new JLabel("Attack Points : ");
        leftPanel.add(attackPtsLbl, c);
        c.gridx = 1;
        JTextField attackPts = new JTextField("");
        attackPts.setColumns(20);
        leftPanel.add(attackPts, c);
        c.gridx = 0;
        c.gridy = 5;
        JLabel armoursPtsLbl = new JLabel("Armour Points : ");
        leftPanel.add(armoursPtsLbl, c);
        c.gridx = 1;
        JTextField armourPts = new JTextField("");
        armourPts.setColumns(20);
        leftPanel.add(armourPts, c);
        c.gridx = 0;
        c.gridy = 6;
        JLabel attackRngeLbl = new JLabel("Attack Range : ");
        leftPanel.add(attackRngeLbl, c);
        c.gridx = 1;
        JTextField attackRnge = new JTextField("");
        attackRnge.setColumns(20);
        leftPanel.add(attackRnge, c);
        c.gridx = 0;
        c.gridy = 7;
        JLabel movementLbl = new JLabel("Movement : ");
        leftPanel.add(movementLbl, c);
        c.gridx = 1;
        JTextField movement = new JTextField("");
        movement.setColumns(20);
        leftPanel.add(movement, c);
        c.gridx = 0;
        c.gridy = 8;
        JLabel typeLbl = new JLabel("Type : ");
        leftPanel.add(typeLbl, c);
        c.gridx = 1;
        JTextField typeTxt = new JTextField("");
        typeTxt.setColumns(20);
        leftPanel.add(typeTxt, c);
        c.gridx = 0;
        c.gridy = 9;
        JLabel weaponLbl = new JLabel("Equipped Weapon : ");
        leftPanel.add(weaponLbl, c);
        c.gridx = 1;
        DefaultComboBoxModel weapon = new DefaultComboBoxModel();
        for (Item item : GameBean.weaponDetails) {
            if (item instanceof Weapon) {
                weapon.addElement(((Weapon) item).getName());
            }
        }
        JComboBox weaponDrpDown = new JComboBox(weapon);
        weaponDrpDown.setSelectedIndex(-1);
        weaponDrpDown.setMaximumSize(new Dimension(100, 30));
        leftPanel.add(weaponDrpDown, c);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        JButton submit = new JButton("Save");
        submit.addActionListener(this);
        submit.setActionCommand("button");
        leftPanel.add(submit, c);
        wrapperPanel.add(leftPanel);
        lvlPanel = new LevelPanel(true, null);
        wrapperPanel.add(lvlPanel);
        c1.gridy = 2;
        c1.fill = GridBagConstraints.BOTH;
        add(wrapperPanel, c1);
        c1.fill = GridBagConstraints.NONE;
        validationMess = new JLabel("Pls enter all the fields or pls choose a character from the drop down");
        validationMess.setForeground(Color.red);
        validationMess.setVisible(false);
        c1.gridy = 3;
        add(validationMess, c1);
        c1.weightx = 1;
        c1.fill = GridBagConstraints.BOTH;
        c1.weighty = 1;
        c1.gridy = 4;
        add(new JPanel(), c1);
    }

    public static void main(String[] args) {
        try {
            GameBean.doInit();
            if (GameBean.weaponDetails.size() > 0) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.add(new PlayerEditor());
                frame.setName("Frame");
                frame.pack();
                frame.setVisible(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(PlayerEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        validationMess.setText("");
        validationMess.setVisible(false);
        if (ae.getActionCommand().equalsIgnoreCase("dropDown")) {
            JComboBox comboBox = (JComboBox) ae.getSource();
            String type = comboBox.getSelectedItem().toString();
            for (GameCharacter user : GameBean.playerDetails) {
                Player player = (Player) user;
                if (player.getType().equalsIgnoreCase(type)) {
                    ((JTextField) leftPanel.getComponent(2)).setText(player.getName());
                    ((JTextField) leftPanel.getComponent(4)).setText(player.getImagePath());
                    ((JTextField) leftPanel.getComponent(6)).setText(new Integer(player.getHealth()).toString());
                    ((JTextField) leftPanel.getComponent(8)).setText(new Integer(player.getAttackPts()).toString());
                    ((JTextField) leftPanel.getComponent(10)).setText(new Integer(player.getArmor()).toString());
                    ((JTextField) leftPanel.getComponent(12)).setText(new Integer(player.getAttackRange()).toString());
                    ((JTextField) leftPanel.getComponent(14)).setText(new Integer(player.getMovement()).toString());
                    ((JTextField) leftPanel.getComponent(16)).setText(player.getType());
                    JComboBox cBox = (JComboBox) leftPanel.getComponent(18);
                    cBox.setSelectedItem(player.getInventory().getEquippedWeapon().getName());
                    lvlPanel.txtFields[0].setText("" + player.getLevel());
                    lvlPanel.txtFields[1].setText("" + player.getExp());
                    lvlPanel.txtFields[2].setText("" + player.getStrength());
                    lvlPanel.txtFields[3].setText("" + player.getVitality());
                    lvlPanel.txtFields[4].setText("" + player.getDexterity());
                    lvlPanel.txtFields[5].setText("" + player.getWisdom());
                    break;
//                    return;
                }
            }
        } else {
            String name = ((JTextField) leftPanel.getComponent(2)).getText();
            String image = ((JTextField) leftPanel.getComponent(4)).getText();
            String health = ((JTextField) leftPanel.getComponent(6)).getText();
            String attackPts = ((JTextField) leftPanel.getComponent(8)).getText();
            String armourPts = ((JTextField) leftPanel.getComponent(10)).getText();
            String attackRnge = ((JTextField) leftPanel.getComponent(12)).getText();
            String movement = ((JTextField) leftPanel.getComponent(14)).getText();
            String type = ((JTextField) leftPanel.getComponent(16)).getText();
            String weapon = ((JComboBox) leftPanel.getComponent(18)).getSelectedItem().toString();
            int attr[] = new int[6];
            attr[0] = Integer.parseInt(lvlPanel.txtFields[0].getText());
            attr[1] = Integer.parseInt(lvlPanel.txtFields[1].getText());
            attr[2] = Integer.parseInt(lvlPanel.txtFields[2].getText());
            attr[3] = Integer.parseInt(lvlPanel.txtFields[3].getText());
            attr[4] = Integer.parseInt(lvlPanel.txtFields[4].getText());
            attr[5] = Integer.parseInt(lvlPanel.txtFields[5].getText());
//            validationMess.setText("");
//            validationMess.setVisible(false);
            if (StringUtils.isNotBlank(image) && StringUtils.isNotBlank(health)
                    && StringUtils.isNotBlank(attackPts) && StringUtils.isNotBlank(armourPts) && StringUtils.isNotBlank(attackRnge) && StringUtils.isNotBlank(movement)
                    && StringUtils.isNotBlank(type) && StringUtils.isNotBlank(weapon)) {
                validationMess.setVisible(false);
                Player player = new Player();
                //have to remove the following statement later..
                player.setName(name);
                player.setAttackPts(Integer.parseInt(attackPts));
                player.setAttackRange(Integer.parseInt(attackRnge));
                player.setHealth(Integer.parseInt(health));
                player.setImagePath(image);
                player.setMovement(Integer.parseInt(movement));
                player.setArmor(Integer.parseInt(armourPts));
                player.setType(type);
                Inventory inventory = new Inventory();
                int position = GameUtils.getPositionOfWeaponItem(weapon);
                Weapon weaponObj = (Weapon) GameBean.weaponDetails.get(position);
                inventory.setEquippedWeapon(weaponObj);
                player.setInventory(inventory);
                player.setLevel(attr[0]);
                player.setExp(attr[1]);
                player.setStrength(attr[2]);
                player.setVitality(attr[3]);
                player.setDexterity(attr[4]);
                player.setWisdom(attr[5]);
                boolean characterAlrdyPresent = false;
                for (int i = 0; i < GameBean.playerDetails.size(); i++) {
                    Player charFromList = (Player) GameBean.playerDetails.get(i);
                    if (charFromList.getType().equalsIgnoreCase(type)) {
                        GameBean.playerDetails.remove(i);
                        characterAlrdyPresent = true;
                    }
                }
                GameBean.playerDetails.add(player);
                try {
                    GameUtils.writeCharactersToXML(GameBean.playerDetails, Configuration.PATH_FOR_USER_CHARACTERS);
                    validationMess.setText("Saved Successfully..");
                    validationMess.setVisible(true);
                    if (!characterAlrdyPresent) {
                        comboBox.addItem(type);
                        comboBox.removeActionListener(this);
                        comboBox.setSelectedItem(type);
                        comboBox.addActionListener(this);
                    }
                } catch (Exception e) {
                    System.out.println("PlayerEditor : actionPerformed() : Some error occured " + e);
                    validationMess.setText("Some error occured..");
                    validationMess.setVisible(true);
                    e.printStackTrace();
                }

            } else {
                validationMess.setText("Pls enter all the fields or pls choose a character from the drop down");
                validationMess.setVisible(true);
            }
        }
        this.revalidate();
//        JFrame frame = (JFrame)SwingUtilities.getAncestorNamed("Frame", this);
//        frame.revalidate();
    }

}
