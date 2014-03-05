/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.models.MapInformation;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class InformationPanel extends JPanel {

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

    public InformationPanel() {

        path = new JLabel("");
        startTile = new JLabel("");
        endTile = new JLabel("");
        playerStyle = new JLabel("");
        commandCounter = new JLabel("");
        actionRow = new JLabel("");
        actionColumn = new JLabel("");

        tilePanel = new JPanel();
        tilePanel.setLayout(new GridLayout(6, 1,10,10));
//        tilePanel.setVisible(true);
        tilePanel.add(startTile);
        tilePanel.add(endTile);
        tilePanel.add(commandCounter);
        tilePanel.add(playerStyle);
        tilePanel.add(actionRow);
        tilePanel.add(actionColumn);
//        tilePanel.setVisible(true);
    }
}
