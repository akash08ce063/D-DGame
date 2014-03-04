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
    private final String MapDirectory;
    private final String CharacterDirectory;
    private JList MapList;
    private JList CharacterList;
    private Label WarningLabel; 
    
    public String MapName = null; //is used to load map
    
    public ArrayList<GameCharacter> collectionOfPlayers = new ArrayList(); //is used to load players
    public MapInformation SelectedMap = new MapInformation();
    public MapInformationWrapper TotalMaps = new MapInformationWrapper();
    public ArrayList<Player> GamePlayers = new ArrayList(); 
    
    public InitCharacterAndMapPanel() throws Exception{
        MapDirectory=Configuration.PATH_FOR_MAP;
        CharacterDirectory=Configuration.PATH_FOR_USER_CHARACTERS;
        initUI();
    }
    
    /**
     * this method is used to generate UI for character and map choose panel
     */
    public void initUI() throws Exception {
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
        
        String[] TotalNames1 = getCharacterNames();
        CharacterList = new JList(TotalNames1);
 
        String[] TotalNames2 = getMapNames(MapDirectory);
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
    @Override
     public void actionPerformed(ActionEvent event){
        if(MapList.getSelectedValue() != null && CharacterList.getSelectedValuesList() != null){
            MapName = (String) MapList.getSelectedValue();
            System.out.println(MapName);
            List o = CharacterList.getSelectedValuesList();
            if(o.size()==4){
                Player GC = new Player();
                Iterator it = collectionOfPlayers.iterator();
                for(int i =0;i<o.size();i++){
                        while(it.hasNext()){
                            GC = (Player)it.next();
                            if(GC.getName() == o.get(i)){
                                GamePlayers.add(GC);
                            }
                        }           
            }
                try{
                        MapInformation finalMapInformation = loadMap(MapName);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(InitCharacterAndMapPanel.class.getName()).log(Level.SEVERE, null, ex);
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
    public MapInformation loadMap(String NameOfMap) throws Exception{
        MapInformation M = new MapInformation();
        HashMap<Integer,TileInformation>  NewpathMap = new HashMap<Integer,TileInformation>();
        LinkedHashMap<Integer,Integer> NewuserLocation = new LinkedHashMap<Integer,Integer>();
        M = GameUtils.fetchParticularMapData(MapDirectory,NameOfMap);
        ArrayList<Integer> S = M.getStartPointInfo();
        Iterator it = S.iterator();
        int i =0;
        while(it.hasNext()){
            HashMap HM = M.getPathMap();
            TileInformation T = (TileInformation)HM.get((int)it.next());//it.next is the start point i is the user number
            T.setPlayer(GamePlayers.get(i));
            NewpathMap.put((int)it.next(), T);
            NewuserLocation.put(i,(int)it.next());
        }
        M.setPathMap(NewpathMap);
        M.setUserLocation(NewuserLocation);
        return M;
    }
    
    /**
     * this method is used to load characters
     * @return a collection of characters
     * @throws java.lang.Exception
     */
    public String[] getCharacterNames () throws Exception{
        collectionOfPlayers = GameUtils.getCharacterDetailsFromFile(CharacterDirectory);
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
        TotalMaps = GameUtils.readMapInformation(strPath);
        ArrayList<MapInformation> M = TotalMaps.getMapList();
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
            MapInformation M = i.loadMap("tesst1");
            System.out.println("finished?");
        } catch (Exception ex) {
            Logger.getLogger(InitCharacterAndMapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

	}
}