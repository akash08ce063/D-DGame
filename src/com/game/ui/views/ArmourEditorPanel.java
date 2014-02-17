/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.Item;
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
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kaushik
 */
public class ArmourEditorPanel extends JPanel implements ActionListener{
     private JComboBox comboBox = null;
    private JLabel validationMess = null;
    public ArmourEditorPanel() {
        doGui();
    }

    public void doGui() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel noteLbl = new JLabel("<html><div style='width : 500px;'>Pls select a value to choose a Weapon or you can create a new "
                + "Weapon entity below. Once selected a weapon, its' details will be available below</div></html>");
        noteLbl.setAlignmentX(0);
        add(noteLbl);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Item item : GameBean.itemDetails) {
            if (item instanceof Weapon) {
                model.addElement(((Weapon) item).getName());
            }
        }
        comboBox = new JComboBox(model);
        comboBox.setSelectedIndex(-1);
        comboBox.setMaximumSize(new Dimension(100, 30));
        comboBox.setAlignmentX(0);
        comboBox.setActionCommand("dropDown");
        comboBox.addActionListener(this);
        add(Box.createVerticalStrut(10));
        add(comboBox);
        add(Box.createVerticalStrut(10));
        JPanel panel1 = new JPanel();
        panel1.setAlignmentX(0);
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 2;
        JLabel weaponDtsLbl = new JLabel("Weapon Details : ");
        weaponDtsLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(weaponDtsLbl, c);
        c.gridwidth = 1;
        c.gridy = 1;
        JLabel nameLbl = new JLabel("Name : ");
        panel1.add(nameLbl, c);
        c.gridx = 1;
        JTextField name = new JTextField("");
        name.setColumns(20);
        panel1.add(name, c);
        c.gridx = 0;
        c.gridy = 2;
        JLabel weaponTypeLbl = new JLabel("Weapon Type : ");
        panel1.add(weaponTypeLbl, c);
        c.gridx = 1;
        JComboBox weaponType = new JComboBox(Configuration.weaponTypes);
        weaponType.setSelectedIndex(0);
        weaponType.setPreferredSize(name.getPreferredSize());
        System.out.println(name.getPreferredSize());
        panel1.add(weaponType, c);
        c.gridx = 0;
        c.gridy = 3;
        JLabel attackRangeLbl = new JLabel("Attack Range : ");
        panel1.add(attackRangeLbl, c);
        c.gridx = 1;
        JTextField attackRange = new JTextField("");
        attackRange.setColumns(20);
        panel1.add(attackRange, c);
        c.gridx = 0;
        c.gridy = 4;
        JLabel attackPtsLbl = new JLabel("Attack Points : ");
        panel1.add(attackPtsLbl, c);
        c.gridx = 1;
        JTextField attackPts = new JTextField("");
        attackPts.setColumns(20);
        panel1.add(attackPts, c);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        JButton submit = new JButton("Save");
        submit.addActionListener(this);
        submit.setActionCommand("button");
        panel1.add(submit, c);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.weighty = 0;
        c.weightx =1;
        validationMess = new JLabel("Pls enter all the fields or pls choose a weapon from the drop down");
        validationMess.setForeground(Color.red);
        validationMess.setVisible(false);
        panel1.add(validationMess, c);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 7;
        c.weightx = 1;
        c.weighty = 1;
        panel1.add(new JLabel(""), c);
        add(panel1);
        add(Box.createVerticalGlue());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("dropDown")) {
            JPanel panel = (JPanel) comboBox.getParent().getComponent(4);
            String name = comboBox.getSelectedItem().toString();
            for (Item item : GameBean.itemDetails) {
                if (item instanceof Weapon) {
                    Weapon weapon = (Weapon) item;
                    if (weapon.getName().equalsIgnoreCase(name)) {
                        ((JTextField) panel.getComponent(2)).setText(name);
                        ((JComboBox) panel.getComponent(4)).setSelectedItem(weapon.getWeaponType());
                        ((JTextField) panel.getComponent(6)).setText(Integer.toString(weapon.getAttackRange()));
                        ((JTextField) panel.getComponent(8)).setText(Integer.toString(weapon.getAttackPts()));
                        return;
                    }
                }
            }
        } else {
            JButton btn = (JButton) ae.getSource();
            JPanel panel = (JPanel) btn.getParent();
            String name = ((JTextField) panel.getComponent(2)).getText();
            
            String weaponType = (String)(((JComboBox) panel.getComponent(4)).getSelectedItem());
            String attackRnge = ((JTextField) panel.getComponent(6)).getText();
            String attackPts = ((JTextField) panel.getComponent(8)).getText();
//            JLabel message = ((JLabel) this.getComponent(5));
            validationMess.setText("");
            validationMess.setVisible(false);
            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(weaponType) && StringUtils.isNotBlank(attackRnge)
                    && StringUtils.isNotBlank(attackPts)) {
                validationMess.setVisible(false);
                Weapon weapon = new Weapon();
                weapon.setName(name);;
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
