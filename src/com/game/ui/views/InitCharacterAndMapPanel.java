/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class is used to generate the UI of character and map panel
 * and select characters and map to play game.
 * @author 韩信
 */
public class InitCharacterAndMapPanel extends JFrame implements ActionListener{
    private String MapDirectory="F:\\study\\game\\Maps\\";
    private String CharacterDirectory="F:\\study\\game\\Characters\\";
    private JList MapList;
    private JList CharacterList;
    private Label WarningLabel; 
    
    public String MapName = null; //is used to load map
    public String[] CharacterNames = new String[4]; //is used to load character
    
    public InitCharacterAndMapPanel(){
        initUI();
    }
    
    /**
     * this method is used to generate UI for character and map choose panel
     */
    public void initUI() {
        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
        
        JPanel Panel = new JPanel();
        
        JPanel LeftPanel = new JPanel();
        LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.Y_AXIS));
        
        JPanel CharacterPanel = new JPanel();
        CharacterPanel.setLayout(new BorderLayout());
        CharacterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel RightPanel = new JPanel();
        RightPanel.setLayout(new BoxLayout(RightPanel, BoxLayout.Y_AXIS));
        
        JPanel MapsPanel = new JPanel();
        MapsPanel.setLayout(new BorderLayout());
        MapsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Label SelectCharacterLabel = new Label ("SelectCharacter");
        Label SelectMapsLabel = new Label ("SelectMaps");
        WarningLabel = new Label ("Please choose 4 characters !");
        WarningLabel.setVisible(true);
        
        String[] TotalNames1=getNames(CharacterDirectory);
        CharacterList = new JList(TotalNames1);
 
        String[] TotalNames2=getNames(MapDirectory);
        MapList = new JList(TotalNames2);
        MapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane CharacterPane = new JScrollPane();
        CharacterPane.getViewport().add(CharacterList);
        CharacterPane.setPreferredSize(new Dimension(150, 100));
        
        JScrollPane MapPane = new JScrollPane();
        MapPane.getViewport().add(MapList);
        MapPane.setPreferredSize(new Dimension(150, 100));
        
        JButton SubmitButton = new JButton("Submit");
        SubmitButton.addActionListener(this);
        
        LeftPanel.add(SelectCharacterLabel);
        LeftPanel.add(CharacterPane);
        
        RightPanel.add(SelectMapsLabel);
        RightPanel.add(MapPane);
        
        Panel.add(LeftPanel);
        Panel.add(RightPanel);
        Panel.add(SubmitButton);
        
        basicPanel.add(Panel);
        basicPanel.add(WarningLabel);
        
        add(basicPanel);
        
        setTitle("SelectCharatersAndMap");
        setSize(400, 350);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * this method is used to switch interface and load map and character
     * at same time check whether the user has choosed the right value
     * @param event 
     */
     public void actionPerformed(ActionEvent event){
        if(MapList.getSelectedValue() != null && CharacterList.getSelectedValuesList() != null){
            MapName = (String) MapList.getSelectedValue()+".xml";
            System.out.println(MapName);
            List o = CharacterList.getSelectedValuesList();
            if(o.size()==4){
                for(int i =0;i<o.size();i++){
                CharacterNames[i]=(String)o.get(i)+".xml";
                System.out.println(CharacterNames[i]);
            }
                this.dispose();       
            }else{
                WarningLabel.setText("Please choose 4 characters !");
            }
        }else{
            WarningLabel.setText("please choose map and characters !");
        }
    }
    
     /**
      * this method is used to load maps
      * @param NameOfMap the name of map
      * @return return an object of mapinformation
      */
    public MapInformation loadMap(String NameOfMap){
        MapInformation M = new MapInformation();
        
        return M;
    }
    
    /**
     * this method is used to load characters
     * @param NameOfCharacter name of the characters
     * @return a collection of characters
     */
    public LinkedList<Player> loadPlayers(String[] NameOfCharacter){
        LinkedList<Player> collectionOfPlayers = new LinkedList();
        
        return collectionOfPlayers;
    }
     
    /**
     * this method is used to get name of file
     * @param strPath the entire directory of the file
     * @return names of total file in that floder 
     */
    public String[] getNames(String strPath){
        LinkedList list = new LinkedList();
        ArrayList Name = new ArrayList ();
        int num=1;
        String strName = "";
        File dir = new File(strPath);
        File file[] = dir.listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory())
                list.add(file[i]);
            else{
                strName=file[i].getName().toString();
                Name.add(strName);
                num++;
            }
        }
        File tmp;
        while (!list.isEmpty()) {
            tmp = (File) list.removeFirst();
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory())
                        list.add(file[i]);
                    else{
                        strName=file[i].getName().toString();
                        Name.add(strName);
                        num++;
                    }
                }
            } else {
                strName=tmp.getName().toString();
                Name.add(strName);
                num++;
            }
        }
        Iterator it = Name.iterator();
        int i=0;
        String[] fileName = new String [num];
        while(it.hasNext()){
            String a =it.next().toString();
            String b[] = a.split("\\.");
            fileName[i]=b[0];
            i++;
        }
        return fileName;
    }
}
