/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        startGame.addActionListener(this);
        
        JButton exitGame = new JButton("exit");
        exitGame.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
             System.exit(0);
            }
        });
        
        functionPanel.add(startGame);
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
        this.dispose();
         SwingUtilities.invokeLater(new Runnable() {          
	            public void run() {
	            	InitCharacterAndMapPanel icam = new InitCharacterAndMapPanel();
	                icam.setVisible(true);
	            }
	        });
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
