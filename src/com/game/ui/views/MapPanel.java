/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.models.Configuration;
import static com.game.models.Configuration.*;
import com.game.models.MapInformation;
import com.game.models.Player;
import com.game.models.TileInformation;
import com.game.util.GameUtils;
import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Double
 */
public class MapPanel extends JFrame implements ActionListener {
    //Map's information
    private JPanel wrapperPanel = new JPanel(new BorderLayout(5, 5));
    private JPanel wrapperPanel2 = new JPanel(new BorderLayout(5, 5));
    private TreeMap<Integer, TileInformation> pathMap;
    private int mapRows;
    private int mapColumns;
    private JPanel mapPanel;
    private int commandCounter;
    private JButton[] tile;
    private TileInformation tileInformation;
    private int numberofPlayers;
    private int numberofEnemys;
  //  private int currentPlayer = 0;
    private LinkedHashMap<Integer, Integer> userLocation;
    private InformationPanel informationPanel;
    private TurnPanel turnPanel;
    private boolean playerAvailable = false;
    private ImageIcon icon = null;

    public MapPanel() {
    }

    public MapPanel(MapInformation Map) throws IOException {
        buildMap(Map);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        add(wrapperPanel);
    }

    //here is to get the information about the map
    public void getMapInformation(MapInformation Map) {
        pathMap = Map.getPathMap();
        mapRows = Map.getRows();
        mapColumns = Map.getColumns();
        userLocation = Map.getUserLocation();
    }

    //here is to build the map panel outline according to the rows and colums
    public void buildMap(MapInformation Map) throws IOException {
        getMapInformation(Map);
        mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(mapRows, mapColumns));
        tile = new JButton[mapRows * mapColumns];
        commandCounter = 1;
        for (int x = 0; x < mapRows; x++) {
            for (int y = 0; y < mapColumns; y++) {
                tile[commandCounter - 1] = new JButton();
                (tile[commandCounter - 1]).setActionCommand("" + commandCounter);
                tile[commandCounter - 1].addActionListener(this);
                mapPanel.add(tile[commandCounter - 1]);
                tileInformation = pathMap.get(commandCounter);
                if (tileInformation != null) {
                    mapPathPoints();
                    if (tileInformation.isEndTile()) {
                        mapEndPoints();
                    }
                    if (tileInformation.isStartTile()) {
                        mapStartPoints();
                        numberofPlayers++;
                    }
                    if (tileInformation.getEnemy() != null) {
                        mapEnemyPoints(tileInformation);
                        numberofEnemys++;
                    }
                }
                commandCounter++;
            }
        }
        UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");

        wrapperPanel.add(mapPanel,BorderLayout.CENTER);
        add(wrapperPanel);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        setMaximizedBounds(env.getMaximumWindowBounds());
        setVisible(true);
    }
    /*
     here is to get the tile's position by maprow and mapcolumn
     the tile's row number is CommandCounter/maprows;
     the tile's column number is CommandCounter%maprows - 1
     */

    public int[] getTileLocation(int commandCounter) {
        int row;
        int column;
        int position[] = new int[2];
        this.commandCounter = commandCounter;
        if (commandCounter % mapColumns != 0) {
            row = commandCounter / mapColumns;
            column = commandCounter % mapColumns - 1;
            position[0] = row;
            position[1] = column;
            return position;
        } else {
            position[0] = commandCounter / mapColumns - 1;
        }
        position[1] = mapColumns - 1;
        return position;
    }

    public static void main(String[] args) throws IOException {
        MapInformation map = new MapInformation();
        try {
            map = GameUtils.fetchParticularMapData(Configuration.PATH_FOR_MAP,"Test1");
            Player player = new Player();
            player.setType("Barbarian");
            player.setMovement(1);
            int user =0;
            LinkedHashMap<Integer,Integer> userLocation = new LinkedHashMap<>();
            TreeMap<Integer,TileInformation> tileInfo = map.getPathMap();
            for(Map.Entry<Integer,TileInformation> entry :  tileInfo.entrySet()){
                if(entry.getValue().isStartTile()){
                    userLocation.put(user, entry.getValue().getLocation());
                    entry.getValue().setPlayer(player);
                    user++;
                }
            }
             map.setUserLocation(userLocation);
        } catch (Exception ex) {
            Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        new MapPanel(map);
    }

    //here is to build the map panel start points
    //put the heros at this tile
    public void mapStartPoints() {
        tile[commandCounter - 1].setBackground(startPointColor); 
        Player player = pathMap.get(commandCounter).getPlayer();
        if(StringUtils.isNotBlank(player.getImagePath())){
            try {
                icon = GameUtils.shrinkImage(player.getImagePath(), 60, 60);
            } catch (IOException ex) {
                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                icon = GameUtils.shrinkImage("Hero1.gif", 60, 60);
            } catch (IOException ex) {
                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            tile[commandCounter - 1].setIcon(icon);
    }

    //here is to build the map panel end points
    public void mapEndPoints() {
        tile[commandCounter - 1].setBackground(endPointColor);
    }

    //here is to build the map panel enemy points
    public void mapEnemyPoints(TileInformation tileInformation) throws IOException {
        tile[commandCounter - 1].setBackground(enemyColor);
        ImageIcon icon = null;
    }

    //here is to build the map panel path points
    public void mapPathPoints() {
        tile[commandCounter - 1].setBackground(pathColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // we consider we just viewing the map information
        if(playerAvailable == false)
        {
        
            //here is to set the InformationPanel;
            int[] actionPositon = getTileLocation(Integer.parseInt(e.getActionCommand()));
            int actionRow = actionPositon[0];
            int actionColumn = actionPositon[1];
            if(informationPanel == null){
                informationPanel = new InformationPanel();
            }
            if(turnPanel == null)
            {
                turnPanel = new TurnPanel();
                turnPanel.player1.addActionListener(new TurnControl());
                turnPanel.player2.addActionListener(new TurnControl());
                //turnPanel.player3.addActionListener(new TurnControl());
                turnPanel.player4.addActionListener(new TurnControl());
            }
            wrapperPanel2.add(informationPanel.tilePanel,BorderLayout.NORTH);
            wrapperPanel2.add(turnPanel,BorderLayout.SOUTH);
            wrapperPanel.add(wrapperPanel2,BorderLayout.EAST);
            informationPanel.commandCounter.setText("Postion : "+e.getActionCommand());
            informationPanel.actionRow.setText("Row: "+actionRow);
            informationPanel.actionColumn.setText("actionColum: "+actionColumn);
            TileInformation info = pathMap.get(Integer.parseInt(e.getActionCommand()));
            if(info != null){
                if(info.getPlayer() != null)
                    informationPanel.playerStyle.setText("playerStyle: "+info.getPlayer().getType());
                else
                    informationPanel.playerStyle.setText("playerStyle: ");
                informationPanel.endTile.setText("endTile "+info.isEndTile());
                informationPanel.startTile.setText("startTile "+info.isStartTile());}
            this.revalidate();
        }
        // here is to move the players
        else
        {
            
            if(pathMap.get(Integer.parseInt(e.getActionCommand())) != null)
            {
                playerAvailable = false;           
                boolean moveAble = false;
                int [] a = new int[2];
                int [] b = new int[2];
                b = getTileLocation(userLocation.get(0));
                a = getTileLocation(Integer.parseInt(e.getActionCommand()));
                if(Math.abs(a[0] - b[0]) + Math.abs(b[1] - a[1]) == 1)
                {
                    moveAble = true;
                }
//                if(moveDistance <= pathMap.get(userLocation.get(0)).getPlayer().getMovement())
                if(moveAble == true)
                {
                    if(pathMap.get(Integer.parseInt(e.getActionCommand())).isEndTile())
                    {
                        this.dispose();

                    }
                    else
                    {
                        tile[userLocation.get(0) - 1].setIcon(null);
                        tile[Integer.parseInt(e.getActionCommand()) - 1].setIcon(icon);
                        pathMap.get(userLocation.get(0)).setStartTile(false);
                        Player p = pathMap.get(userLocation.get(0)).getPlayer();
                        pathMap.get(userLocation.get(0)).setPlayer(null);
                        pathMap.get(Integer.parseInt(e.getActionCommand())).setPlayer(p);
                        pathMap.get(Integer.parseInt(e.getActionCommand())).setStartTile(true);
                        userLocation.put(0, Integer.parseInt(e.getActionCommand()));
                    }
                }
                else
                {
 
                }
                
                
            }      
            else
            {
                
            }      
            
        }
    } 
    public class TurnControl implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            playerAvailable = true;
        }
        
    }

}
