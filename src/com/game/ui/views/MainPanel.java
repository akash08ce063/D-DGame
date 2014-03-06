/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.GameBean;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This class is used to generate UI for MainPanel and implement the 
 * function that switching interface to character and map choose panel.
 * @author 韩信
 */
public class MainPanel extends JFrame implements ActionListener {
     public MainPanel(){
        initUI();
    }
     
    /**
     * initiate the UI for Main Panel
     */
    public void initUI() {
        JPanel basicPanel = new JPanel();
        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new BoxLayout(functionPanel, BoxLayout.Y_AXIS));
        
        JButton startGame = new JButton("Strat");
        startGame.setActionCommand("0");
        startGame.addActionListener(this);
        
        JButton creatCharater = new JButton("Creat \n" + "Character");
        creatCharater.setActionCommand("1");
        creatCharater.addActionListener(this);
        
        JButton mapBuilder = new JButton("Map \n" + "Builder");
        mapBuilder.setActionCommand("2");
        mapBuilder.addActionListener(this);
        
        JButton exitGame = new JButton("exit");
        exitGame.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
             System.exit(0);
            }
        });
        
        functionPanel.add(startGame);
        functionPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        functionPanel.add(creatCharater);
        functionPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        functionPanel.add(mapBuilder);
        functionPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        functionPanel.add(exitGame);
        basicPanel.add(functionPanel);
        add(basicPanel);
        
        setTitle("SelectCharatersAndMap");
        setSize(500, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * this method is used to switch interface
     * @param event 
     */
    public void actionPerformed(ActionEvent event){
        int command = Integer.parseInt(event.getActionCommand());
        if(command == 0){
            this.dispose();
            SwingUtilities.invokeLater(new Runnable() {          
	            public void run() {
                        try {
                           InitCharacterAndMapPanel icam = new InitCharacterAndMapPanel();
                           icam.setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

	            }
	        });
        }else if (command == 1){
            
        }else if (command == 2){
            try {
                GameBean.doInit();
            } catch (Exception ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MapEditor();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        }
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	MainPanel ex = new MainPanel();
	                ex.setVisible(true);
	            }
	        });
	}
}
