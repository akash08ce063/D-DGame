/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.GameCharacter;
import com.game.util.GameUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
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
public class CharachterEditorPanel extends JPanel implements ActionListener {

    private JComboBox comboBox = null;
    private LinkedHashSet<GameCharacter> enemyChars = new LinkedHashSet<GameCharacter>();

    public CharachterEditorPanel() {
        doGui();
    }

    public void doGui() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel noteLbl = new JLabel("Pls select a value to choose an enemy or you can create a new "
                + "Enemy entity below. Once selected an Enemy character, its' details will be available below");
        noteLbl.setAlignmentX(0);
//        noteLbl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(noteLbl);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (GameCharacter character : GameBean.enemyDetails) {
            System.out.println(character.getName());
            model.addElement(character.getName());
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
        panel1.setBorder(LineBorder.createGrayLineBorder());
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        JLabel enemyDtlLbl = new JLabel("Enemy Character Details : ");
        enemyDtlLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(enemyDtlLbl, c);
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
        JLabel imageLbl = new JLabel("Image Path : ");
        panel1.add(imageLbl, c);
        c.gridx = 1;
        JTextField image = new JTextField("");
        image.setColumns(20);
        panel1.add(image, c);
        c.gridx = 0;
        c.gridy = 3;
        JLabel healthLbl = new JLabel("Health Pts : ");
        panel1.add(healthLbl, c);
        c.gridx = 1;
        JTextField health = new JTextField("");
        health.setColumns(20);
        panel1.add(health, c);
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
        JLabel armoursPtsLbl = new JLabel("Armour Points : ");
        panel1.add(armoursPtsLbl, c);
        c.gridx = 1;
        JTextField armourPts = new JTextField("");
        armourPts.setColumns(20);
        panel1.add(armourPts, c);
        c.gridx = 0;
        c.gridy = 6;
        JLabel attackRngeLbl = new JLabel("Attack Range : ");
        panel1.add(attackRngeLbl, c);
        c.gridx = 1;
        JTextField attackRnge = new JTextField("");
        attackRnge.setColumns(20);
        panel1.add(attackRnge, c);
        c.gridx = 0;
        c.gridy = 7;
        JLabel movementLbl = new JLabel("Movement : ");
        panel1.add(movementLbl, c);
        c.gridx = 1;
        JTextField movement = new JTextField("");
        movement.setColumns(20);
        panel1.add(movement, c);
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 2;
        JButton submit = new JButton("Save");
        submit.addActionListener(this);
        submit.setActionCommand("button");
        panel1.add(submit, c);
        add(panel1);
        c.gridx = 0;
        c.gridy = 9;
        JLabel validationMess = new JLabel("Pls enter all the fields or pls choose a character from the drop down");
        validationMess.setForeground(Color.red);
        validationMess.setVisible(false);
        add(validationMess, c);
        add(Box.createVerticalGlue());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("dropDown")) {
            JComboBox comboBox = (JComboBox) ae.getSource();
            JPanel panel = (JPanel) comboBox.getParent().getComponent(4);
            String name = comboBox.getSelectedItem().toString();
            for (GameCharacter enemy : GameBean.enemyDetails) {
                if (enemy.getName().equalsIgnoreCase(name)) {
                    ((JTextField) panel.getComponent(2)).setText(enemy.getName());
                    ((JTextField) panel.getComponent(4)).setText(enemy.getImagePath());
                    ((JTextField) panel.getComponent(6)).setText(new Integer(enemy.getHealth()).toString());
                    ((JTextField) panel.getComponent(8)).setText(new Integer(enemy.getAttackPts()).toString());
                    ((JTextField) panel.getComponent(10)).setText(new Integer(enemy.getArmor()).toString());
                    ((JTextField) panel.getComponent(12)).setText(new Integer(enemy.getAttackRange()).toString());
                    ((JTextField) panel.getComponent(14)).setText(new Integer(enemy.getMovement()).toString());
                    return;
                }
            }
        } else {
            JButton btn = (JButton) ae.getSource();
            JPanel panel = (JPanel) btn.getParent();
            int indexOfBtn = btn.getAccessibleContext().getAccessibleIndexInParent();
            String name = ((JTextField) panel.getComponent(2)).getText();
            String image = ((JTextField) panel.getComponent(4)).getText();
            String health = ((JTextField) panel.getComponent(6)).getText();
            String attackPts = ((JTextField) panel.getComponent(8)).getText();
            String armourPts = ((JTextField) panel.getComponent(10)).getText();
            String attackRnge = ((JTextField) panel.getComponent(12)).getText();
            String movement = ((JTextField) panel.getComponent(14)).getText();
            System.out.println("Index : " + indexOfBtn);
            JLabel message = ((JLabel) this.getComponent(5));
            message.setText("");
            message.setVisible(false);
            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(image) && StringUtils.isNotBlank(health)
                    && StringUtils.isNotBlank(attackPts) && StringUtils.isNotBlank(armourPts) && StringUtils.isNotBlank(attackRnge) && StringUtils.isNotBlank(movement)) {
                message.setVisible(false);
                GameCharacter character = new GameCharacter();
                character.setName(name);
                character.setAttackPts(Integer.parseInt(attackPts));
                character.setAttackRange(Integer.parseInt(attackRnge));
                character.setHealth(Integer.parseInt(health));
                character.setImagePath(image);
                character.setMovement(Integer.parseInt(movement));
                character.setArmor(Integer.parseInt(armourPts));
                boolean characterAlrdyPresent = false;
                for (int i = 0; i < GameBean.enemyDetails.size(); i++) {
                    GameCharacter charFromList = GameBean.enemyDetails.get(i);
                    if (charFromList.getName().equalsIgnoreCase(name)) {
                        GameBean.enemyDetails.remove(i);
                        GameBean.enemyDetails.add(i, character);
                        characterAlrdyPresent = true;
                    }
                }
                if (!characterAlrdyPresent) {
                    GameBean.enemyDetails.add(character);
                }
                try {
                    GameUtils.writeCharactersToXML(GameBean.enemyDetails, Configuration.PATH_FOR_ENEMY_CHARACTERS);
                    message.setText("Saved Successfully..");
                    message.setVisible(true);
                    if (!characterAlrdyPresent) {
                        comboBox.addItem(name);
                        comboBox.setSelectedItem(name);
                    }
                    this.revalidate();
                    return;
                } catch (Exception e) {
                    System.out.println("CharachterEditorPanel : actionPerformed() : Some error occured " + e);
                }

            } else {
                message.setText("Pls enter all the fields or pls choose a character from the drop down");
                message.setVisible(true);
                this.revalidate();
            }
        }
    }
}
