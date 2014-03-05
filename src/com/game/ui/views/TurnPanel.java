/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Administrator
 */
public class TurnPanel extends JPanel
{
    public JButton player1;
    public JButton player2;
    public JButton player3;
    public JButton player4;
    public TurnPanel()
    {
       player1 = new JButton();
       player2 = new JButton();
       player3 = new JButton();
       player4 = new JButton();
       
       player1.setText("player 1");
       player2.setText("player 2");
       player3.setText("player 3");
       player4.setText("player 4");
       
       
       setLayout(new GridLayout(4, 1,10,10));
       add(player1);
       //add(player2);
       //add(player3);
       //add(player4);
       UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
    }
}
