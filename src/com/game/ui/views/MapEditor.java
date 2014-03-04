/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.models.GameBean;
import com.game.models.MapInformation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Kaushik
 */
public class MapEditor extends JFrame implements ActionListener{
    private SimpleDialog dialog = null;
    private JPanel bottomPanel = null;
    public MapEditor() throws IOException {
        generateGUI();
    }

    public void generateGUI() throws IOException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setResizable(false);
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topPanel.setLayout(new GridBagLayout());
        JLabel headerLbl = new JLabel("Legend : ");
        headerLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
        JLabel lbl1 = new JLabel();
        lbl1.setPreferredSize(new Dimension(50, 20));
        lbl1.setBackground(Color.CYAN);
        lbl1.setOpaque(true);
        JLabel lbl2 = new JLabel("- Represents the path.");
        JLabel lbl3 = new JLabel();
        lbl3.setPreferredSize(new Dimension(50, 20));
        lbl3.setBackground(Color.RED);
        lbl3.setOpaque(true);
        JLabel lbl4 = new JLabel("- Represents the path with monsters");
        JLabel lbl5 = new JLabel();
        lbl5.setPreferredSize(new Dimension(50, 20));
        lbl5.setBackground(Color.darkGray);
        lbl5.setOpaque(true);
        JLabel lbl6 = new JLabel("- Represents the starting point in the path");
        JLabel lbl7 = new JLabel();
        lbl7.setBackground(Color.green);
        lbl7.setOpaque(true);
        lbl7.setPreferredSize(new Dimension(50, 20));
        JLabel lbl8 = new JLabel("- Ending point in the path");
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridwidth = 2;
        topPanel.add(headerLbl, c);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 1;
        c.ipadx = 5;
        c.ipady = 5;
        topPanel.add(lbl1, c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        lbl2.setBorder(new LineBorder(Color.yellow));
        topPanel.add(lbl2, c);
        c.gridx = 0;
        c.gridy = 2;
        topPanel.add(lbl3,c);
        c.gridx = 1;
        topPanel.add(lbl4,c);
        c.gridx = 0;
        c.gridy = 3;
        topPanel.add(lbl5,c);
        c.gridx = 1;
        topPanel.add(lbl6,c);
        c.gridx = 0;
        c.gridy = 4;
        topPanel.add(lbl7,c);
        c.gridx = 1;
        topPanel.add(lbl8,c);
        add(topPanel, BorderLayout.NORTH);
        bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.CENTER);
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5 ,5));
//        bottomPanel.add(new JButton("kaushik"));
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);  
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        setMaximizedBounds(env.getMaximumWindowBounds());  
        setVisible(true);
        callDialogForUsersInput();
    }
    public void callDialogForUsersInput() throws IOException
    {
        dialog = new SimpleDialog(this);
        int rows = dialog.getRows();
        int columns = dialog.getColumn();
        JButton buttons[] = new JButton[rows*columns];
        if(rows != 0 && columns != 0)
        {
            bottomPanel.setLayout(new GridLayout(rows, columns, 5, 5));
            for(int i = 1,k=0; i <= buttons.length; i++,k++)
            {
                buttons[k] = new JButton();
                buttons[k].addActionListener(this);
                buttons[k].setActionCommand(new Integer(i).toString());
                bottomPanel.add(buttons[k]);
            }
            bottomPanel.revalidate();
            bottomPanel.getParent().revalidate();
        }
        GameBean.mapInfo = new MapInformation();
        GameBean.mapInfo.setColumns(columns);
        GameBean.mapInfo.setRows(rows);
    }
    public static void main(String[] args) throws Exception {
        GameBean.doInit();
        new MapEditor();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton src = (JButton) ae.getSource();
        new ComplexDialog(src.getActionCommand());
    }
}
