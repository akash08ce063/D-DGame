/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.Armour;
import com.game.models.GameBean;
import com.game.models.GameCharacter;
import com.game.models.MapInformation;
import com.game.models.Player;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.Treasure;
import com.game.models.Weapon;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class InformationPanel extends JPanel implements ActionListener
{
     public JLabel path;
     public JLabel startTile;
     public JLabel endTile;
     public JLabel playerStyle;
     public JLabel commandCounter;
     public JLabel actionRow;
     public JLabel actionColumn;
     public JPanel tilePanel;
     public JPanel mapEditorPanel;
     public MapInformation informationPanelMap;
     
     public InformationPanel(boolean forMapEditor)
     {   
         // this is used for MapPanel
         if(forMapEditor == false)
         {
              tilePanel = new JPanel();
              tilePanel.setLayout(new GridLayout(6,1));
              tilePanel.setVisible(true);
              tilePanel.add(startTile);
              tilePanel.add(endTile);
              tilePanel.add(commandCounter);
              tilePanel.add(playerStyle);
              tilePanel.add(actionRow);
              tilePanel.add(actionColumn);
              
         }

     }

     
     
     
     
     
     
     
     
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
