/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.util.GameUtils;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Kaushik
 */
public class ComplexDialog extends JDialog implements ActionListener{
    private JPanel rightWrapper = null;
    public ComplexDialog()
    {
        doGui();
    }
    public void doGui()
    {
        setResizable(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        env.getMaximumWindowBounds();
        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel leftWrapper = new JPanel();
        rightWrapper = new JPanel();
        rightWrapper.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftWrapper.setLayout(new BoxLayout(leftWrapper, BoxLayout.Y_AXIS));
        String text[] = {"Enemy","Weapon","Armour","Ring","Potion","Treasure"};
        JCheckBox checkBox[] = new JCheckBox[6];
        JButton btn[] = new JButton[6];
        JPanel panel[] = new JPanel[6];
        for(int i = 0; i < checkBox.length; i++)
        {
            panel[i] = new JPanel();
            panel[i].setLayout(new FlowLayout(FlowLayout.LEFT, 30,0));
            checkBox[i] = new JCheckBox(text[i], false);
            checkBox[i].setEnabled(false);
            checkBox[i].setPreferredSize(new Dimension(100,30));
            btn[i] = new JButton("Edit "+text[i]+" >>");
            btn[i].setActionCommand(text[i]);
            btn[i].setPreferredSize(new Dimension(200,30));
            btn[i].setActionCommand(text[i]);
            btn[i].addActionListener(this);
            panel[i].add(checkBox[i]);
            panel[i].add(btn[i]);
            leftWrapper.add(panel[i]);
            leftWrapper.add(Box.createVerticalStrut(30));
        }
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
//        c.weighty = 1;
        wrapper.add(leftWrapper,c);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        rightWrapper.setBorder(LineBorder.createBlackLineBorder());
        rightWrapper.setLayout(new CardLayout());
        JPanel dummyPanel = new JPanel();
        dummyPanel.add(new JLabel(" "));
        CharachterEditorPanel charPanel = new CharachterEditorPanel();
        rightWrapper.add(dummyPanel,"Dummy");
        rightWrapper.add(charPanel,"Enemy");
        WeaponEditorPanel weaponPanel = new WeaponEditorPanel();
        rightWrapper.add(weaponPanel,"Weapon");
        rightWrapper.setBackground(Color.GRAY);
//        rightWrapper.add(new JButton(" sdfds"));
        wrapper.add(rightWrapper,c);
//        JScrollPane scrollPane = new JScrollPane(wrapper);
//        scrollPane.setSize(env.getMaximumWindowBounds().getSize());
        getContentPane().add(wrapper);
        pack();
        setSize(env.getMaximumWindowBounds().getSize());
        setVisible(true);
     }
    public static void main(String[] args) throws Exception {
        
        GameBean.enemyDetails = GameUtils.getCharacterDetailsFromFile(Configuration.PATH_FOR_ENEMY_CHARACTERS);
        GameBean.itemDetails = GameUtils.getAllItems();
        ComplexDialog dialog = new ComplexDialog();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        CardLayout c = (CardLayout)rightWrapper.getLayout();
        c.show(rightWrapper, ae.getActionCommand());
    }
}
