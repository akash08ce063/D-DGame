/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.Player;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Kaushik
 */
public class LevelPanel extends JPanel implements ActionListener{
    private boolean forPlayerEditor = false;
    private int strength = 0;
    private int vitality = 0;
    private int dexterity = 0;
    private int wisdom = 0;
    private int level = 1;
    private Player player = null;
    public JTextField txtFields[] = null;
    public LevelPanel(boolean forPlayerEditor, Player player){
        this.forPlayerEditor = forPlayerEditor;
        doGui();
    }
    public void doGui()
    {
        setLayout(new GridLayout(6,1,10,10));
        String[] lbls = {"Level : ","Experience : ","Strength : ","Vitality : ","Dexterity : ","Wisdom : "};
        JLabel label1 = new JLabel(lbls[0]);
        label1.setPreferredSize(new Dimension(100, 30));
        txtFields = new JTextField[6];
        txtFields[0] = new JTextField("0");
        txtFields[0].setPreferredSize(new Dimension(70,30));
        txtFields[0].setMaximumSize(new Dimension(70,30));
        txtFields[1] = new JTextField("0");
        txtFields[1].setPreferredSize(new Dimension(70,30));
        txtFields[1].setMaximumSize(new Dimension(70,30));
        if(!forPlayerEditor){
             txtFields[0].setEditable(false);
             txtFields[1].setEditable(false);
        }
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.X_AXIS));
        firstPanel.add(Box.createHorizontalStrut(10));
        firstPanel.add(label1);
        firstPanel.add(Box.createHorizontalStrut(60));
        firstPanel.add(txtFields[0]);
        firstPanel.add(Box.createHorizontalGlue());
        add(firstPanel);
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
        secondPanel.add(Box.createHorizontalStrut(10));
        JLabel lbl2 = new JLabel(lbls[1]);
        lbl2.setPreferredSize(new Dimension(100, 30));
        secondPanel.add(lbl2);
        secondPanel.add(Box.createHorizontalStrut(60));
        secondPanel.add(txtFields[1]);
        secondPanel.add(Box.createHorizontalGlue());
        add(secondPanel);
        if(forPlayerEditor){
            for(int i = 2; i < txtFields.length; i++){
                JPanel subPanel = new JPanel();
                subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
                subPanel.add(Box.createHorizontalStrut(10));
                JLabel lbl = new JLabel(lbls[i]);
                lbl.setPreferredSize(new Dimension(100,30));
                subPanel.add(lbl);
                JButton subBtn = new JButton("-");
                subBtn.addActionListener(this);
                subBtn.setPreferredSize(new Dimension(40,30));
                subBtn.setMaximumSize(new Dimension(40,30));
                subBtn.setActionCommand(lbls[i]+" Subtract");
                subPanel.add(Box.createHorizontalStrut(10));
                subPanel.add(subBtn);
                subPanel.add(Box.createHorizontalStrut(10));
                txtFields[i] = new JTextField("0");
                txtFields[i].setPreferredSize(new Dimension(70,30));
                txtFields[i].setMaximumSize(new Dimension(70,30));
                subPanel.add(txtFields[i]);
                subPanel.add(Box.createHorizontalStrut(10));
                JButton addBtn = new JButton("+");
                addBtn.addActionListener(this);
                addBtn.setActionCommand(lbls[i]+" Add");
                addBtn.setPreferredSize(new Dimension(50,30));
                addBtn.setMaximumSize(new Dimension(50,30));
                subPanel.add(addBtn);
                subPanel.add(Box.createHorizontalGlue());
                add(subPanel);
            }
        }
        else{
            for (int i = 2; i < txtFields.length; i++) {
                JPanel subPanel = new JPanel();
                subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
                subPanel.add(Box.createHorizontalStrut(10));
                JLabel lbl = new JLabel(lbls[i]);
                lbl.setPreferredSize(new Dimension(100, 30));
                subPanel.add(lbl);
                JButton subBtn = new JButton("-");
                subBtn.addActionListener(this);
                subBtn.setPreferredSize(new Dimension(40, 30));
                subBtn.setMaximumSize(new Dimension(40, 30));
                subBtn.setActionCommand(lbls[i] + " Subtract");
                subPanel.add(Box.createHorizontalStrut(10));
                subPanel.add(subBtn);
                subPanel.add(Box.createHorizontalStrut(10));
                txtFields[i] = new JTextField();
                txtFields[i].setPreferredSize(new Dimension(70, 30));
                txtFields[i].setMaximumSize(new Dimension(70, 30));
                txtFields[i].setEditable(false);
                subPanel.add(txtFields[i]);
                subPanel.add(Box.createHorizontalStrut(10));
                JButton addBtn = new JButton("+");
                addBtn.addActionListener(this);
                addBtn.setActionCommand(lbls[i] + " Add");
                addBtn.setPreferredSize(new Dimension(50, 30));
                addBtn.setMaximumSize(new Dimension(50, 30));
                subPanel.add(addBtn);
                subPanel.add(Box.createHorizontalGlue());
                add(subPanel);
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(400,400));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(new LevelPanel(true,null));
        frame.pack();
        frame.setVisible(true);
    }
     @Override
    public void actionPerformed(ActionEvent ae) {
        String actionCommand = ae.getActionCommand();
        String[] lbls = {"Level : ","Experience : ","Strength : ","Vitality : ","Dexterity : ","Wisdom : "};
        if(actionCommand.contains("Strength : "))
        {
            strength = Integer.parseInt(txtFields[2].getText());
            if(actionCommand.contains("Subtract")){
                --strength;
            }
            else{
                ++strength;
            }
            if(strength < 0){
                strength = 0;
            }
            txtFields[2].setText(""+strength);
        }
        else if(actionCommand.contains("Vitality : ")){
            vitality = Integer.parseInt(txtFields[3].getText());
            if(actionCommand.contains("Subtract")){
                --vitality;
            }
            else{
                ++vitality;
            }
            if(vitality < 0){
                vitality = 0;
            }
            txtFields[3].setText(""+vitality);
        }
        else if(actionCommand.contains("Dexterity : ")){
            dexterity = Integer.parseInt(txtFields[4].getText());
            if(actionCommand.contains("Subtract")){
                --dexterity;
            }
            else{
                ++dexterity;
            }
            if(dexterity < 0){
                dexterity = 0;
            }
            txtFields[4].setText(""+dexterity);
        }
        else if(actionCommand.contains("Wisdom : ")){
            wisdom = Integer.parseInt(txtFields[5].getText());
            if(actionCommand.contains("Subtract")){
                --wisdom;
            }
            else{
                ++wisdom;
            }
            if(wisdom < 0){
                wisdom = 0;
            }
            txtFields[5].setText(""+wisdom);
        }
    }
}
