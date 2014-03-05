/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.*;
import com.game.util.GameUtils;
import com.game.xml.models.MapInformationWrapper;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * This class is used to generate the UI of character and map panel
 * and select characters and map to play game.
 * @author 韩信
 */
public class InitCharacterAndMapPanel extends JFrame implements ActionListener{
    private String mapDirectory;
    private String characterDirectory;
    private JList mapList;
    private JList characterList;
    private Label warningLabel; 
    
    private String mapName = null; //is used to save the name of map which is selected by user
    
    public ArrayList<GameCharacter> collectionOfPlayers = new ArrayList(); //is used to load players
    public MapInformation selectedMap = new MapInformation();
    public MapInformationWrapper totalMaps = new MapInformationWrapper(); 
    public ArrayList<Player> gamePlayers = new ArrayList();   //is used to save players selected by user
    
    public InitCharacterAndMapPanel() throws Exception{
        mapDirectory=Configuration.PATH_FOR_MAP;
        characterDirectory=Configuration.PATH_FOR_USER_CHARACTERS;
        initUI();
    }
    
    /**
     * this method is used to generate UI for character and map choose panel
     */
    public void initUI() throws Exception {
        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
        
        JPanel panel = new JPanel();
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BorderLayout());
        characterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        
        JPanel mapsPanel = new JPanel();
        mapsPanel.setLayout(new BorderLayout());
        mapsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Label selectCharacterLabel = new Label ("SelectCharacter");
        Label selectMapsLabel = new Label ("SelectMaps");
        warningLabel = new Label ("Please choose 4 characters !");
        warningLabel.setVisible(true);
        
        String[] totalNames1 = getCharacterNames();
        characterList = new JList(totalNames1);
 
        String[] totalNames2 = getMapNames(mapDirectory);
        mapList = new JList(totalNames2);
        mapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane characterPane = new JScrollPane();
        characterPane.getViewport().add(characterList);
        characterPane.setPreferredSize(new Dimension(150, 100));
        
        JScrollPane MapPane = new JScrollPane();
        MapPane.getViewport().add(mapList);
        MapPane.setPreferredSize(new Dimension(150, 100));
        
        JButton SubmitButton = new JButton("Submit");
        SubmitButton.addActionListener(this);
        
        leftPanel.add(selectCharacterLabel);
        leftPanel.add(characterPane);
        
        rightPanel.add(selectMapsLabel);
        rightPanel.add(MapPane);
        
        panel.add(leftPanel);
        panel.add(rightPanel);
        panel.add(SubmitButton);
        
        basicPanel.add(panel);
        basicPanel.add(warningLabel);
        
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
    @Override
     public void actionPerformed(ActionEvent event){
        if(mapList.getSelectedValue() != null && characterList.getSelectedValuesList() != null){
            mapName = (String) mapList.getSelectedValue();
            System.out.println(mapName);
            List o = characterList.getSelectedValuesList();
            if(o.size()==4){
                Player GC = new Player();
                Iterator it = collectionOfPlayers.iterator();
                for(int i =0;i<o.size();i++){
                        while(it.hasNext()){
                            GC = (Player)it.next();
                            if(GC.getName() == o.get(i)){
                                gamePlayers.add(GC);
                            }
                        }           
            }
                try{
                        MapInformation finalMapInformation = loadMap(mapName);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(InitCharacterAndMapPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                this.dispose();       
            }else{
                warningLabel.setText("Please choose 4 characters !");
            }
        }else{
            warningLabel.setText("please choose map and characters !");
        }
    }
    
     /**
      * this method is used to load maps
      * @param NameOfMap the name of map
      * @return return an object of mapinformation
      */
    public MapInformation loadMap(String NameOfMap) throws Exception{
        MapInformation M = new MapInformation();
        TreeMap<Integer,TileInformation>  NewpathMap = new TreeMap<>();
        LinkedHashMap<Integer,Integer> NewuserLocation = new LinkedHashMap<>();
        M = GameUtils.fetchParticularMapData(mapDirectory,NameOfMap);
        ArrayList<Integer> S = M.getStartPointInfo();
        Iterator it = S.iterator();
        int i =0;
        while(it.hasNext()){
            TreeMap HM = M.getPathMap();
            TileInformation T = (TileInformation)HM.get((int)it.next());//it.next is the start point i is the user number
            T.setPlayer(gamePlayers.get(i));
            NewpathMap.put((int)it.next(), T);
            NewuserLocation.put(i,(int)it.next());
        }
        M.setPathMap(NewpathMap);
        M.setUserLocation(NewuserLocation);
        return M;
    }
    
    /**
     * this method is used to load characters and get names
     * @return a collection of characters
     * @throws java.lang.Exception
     */
    public String[] getCharacterNames () throws Exception{
        collectionOfPlayers = GameUtils.getCharacterDetailsFromFile(characterDirectory);
        String[] names = new String[collectionOfPlayers.size()];
        Iterator it = collectionOfPlayers.iterator();
        int i=0;
        while(it.hasNext()){
            GameCharacter GC = new GameCharacter();
            GC = (GameCharacter)it.next();
            names[i] = GC.getName();
            i++;
        }
        return names;
    }
    
    
    
    
    /**
     * this method is used to get name of file
     * @param strPath the entire directory of the file
     * @return names of total file in that floder 
     */
    public String[] getMapNames(String strPath) throws Exception{
        totalMaps = GameUtils.readMapInformation(strPath);
        ArrayList<MapInformation> M = totalMaps.getMapList();
        String[] fileName = new String[M.size()];
        Iterator it = M.iterator();
        int i = 0;
        while(it.hasNext()){
            MapInformation Ma = (MapInformation) it.next();
            fileName[i] = Ma.getMapName();
            i++;
        }
        return fileName;
    }
    
    public static void main(String[] args) {
        InitCharacterAndMapPanel i;
        try {
            i = new InitCharacterAndMapPanel();
            MapInformation M = i.loadMap("Test1");
            System.out.println("finished?");
        } catch (Exception ex) {
            Logger.getLogger(InitCharacterAndMapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

	}
}