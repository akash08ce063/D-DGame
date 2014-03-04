/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import static com.game.models.Configuration.*;

import com.game.models.MapInformation;
import com.game.models.Player;

import com.game.models.TileInformation;
import com.game.util.GameUtils;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author Double
 */
public class MapPanel extends JFrame implements ActionListener
{
    //Map's information
    private MapInformation Map;
    private TreeMap<Integer,TileInformation> pathMap;
    private int mapRows;
    private int mapColumns;
    private JFrame mapFrame;
    private int commandCounter;
    private JButton Tile[];
    private TileInformation tileInformation;
    private int numberofPlayers;
    private int numberofEnemys;
    private static int currentPlayer = 0;
    private LinkedHashMap<Integer,Integer> userLocation;
    private InformationPanel informationPanel;
    public MapPanel()
    {
        
    }
    public MapPanel(MapInformation Map) throws IOException
    {
        this.setLayout(new BorderLayout());
        BuildMap(Map);
        informationPanel = new InformationPanel(false);
        this.add(informationPanel,BorderLayout.EAST);
        this.add(mapFrame,BorderLayout.WEST);
    }
    
    
    
    
    //here is to get the information about the Map
    public void GetMapInformation(MapInformation Map)
    {
    this.Map = Map;
    pathMap = Map.getPathMap();
    mapRows = Map.getRows();
    mapColumns = Map.getColumns();
    userLocation = Map.getUserLocation();
    }
    //here is to build the map panel outline according to the rows and colums
    public void BuildMap (MapInformation Map) throws IOException
    {
        GetMapInformation(Map);
        mapFrame = new JFrame();
        mapFrame.setLayout(new GridLayout(mapRows,mapColumns));
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapFrame.pack();
        mapFrame.setVisible(true);
        Tile = new JButton[mapRows*mapColumns];
        commandCounter = 1;
        for (int x=0; x < mapRows; x++)
        {
            for(int y=0; y < mapColumns; y++)
            {
                (Tile[commandCounter]).setActionCommand(""+commandCounter);
                (Tile[commandCounter]).addActionListener(this);
                mapFrame.add(Tile[commandCounter]);
                tileInformation = pathMap.get(commandCounter);
                if(tileInformation != null)
                {
                    mapPathPoints();
                    if(tileInformation.isEndTile())
                    {
                        mapEndPoints();
                    }
                    if(tileInformation.isStartTile())
                    {
                        mapStartPoints();
                        numberofPlayers++;
                    }
                    if(tileInformation.getEnemy() != null)
                    {
                        mapEnemyPoints(tileInformation);
                        numberofEnemys++;
                    }
                }       
                commandCounter++;
            }
        }     
        UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    here is to get the tile's position by maprow and mapcolumn
    the tile's row number is CommandCounter/maprows;
    the tile's column number is CommandCounter%maprows - 1
    */
       
    public int[] getTileLocation(int commandCounter)
    {
        int row;
        int column;
        int position[] = new int[2];
        this.commandCounter = commandCounter;
        if(commandCounter%mapColumns != 0)
        {
             row = commandCounter/mapColumns;
             column= commandCounter%mapColumns - 1;
             position[0] = row;
             position[1] = column;
             return position;
        }
        else
            position[0] = commandCounter/mapColumns - 1;
            position[1] = mapColumns - 1;
            return position;
    }   
    
    //here is to build the map panel start points
    //put the heros at this tile
    public void mapStartPoints()
    {
       // userLocation.put(currentPlayer,tileInformation.getLocation());
       (Tile[commandCounter]).setBackground(startPointColor);
      // currentPlayer++;
    }
    //here is to build the map panel end points
     public void mapEndPoints()
    {
        (Tile[commandCounter]).setBackground(endPointColor);
    }  
     //here is to build the map panel enemy points
      public void mapEnemyPoints(TileInformation tileInformation) throws IOException
      {
        ImageIcon icon = null;
        try {
            icon = GameUtils.shrinkImage(tileInformation.getEnemy().getImagePath(), 30, 30);
             } 
        catch (IOException e) 
        {
            System.out.println("Dialog : showDialogForMap(): Exception occured :" + e);
            throw new IOException(e);
        }
      }   
      //here is to build the map panel path points
      public void mapPathPoints()
      {
          (Tile[commandCounter]).setBackground(pathColor);
      }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //here is to set the InformationPanel;
        int[] actionPositon = getTileLocation(Integer.parseInt(e.getActionCommand()));
        int actionRow = actionPositon[0];
        int actionColumn = actionPositon[1];
        String informationPanelStartTile = "false";
        String informationPanelEndTile = "false";
        String informationPanelCommandCounter = e.getActionCommand();
        String informationPanelActionRow = "" + actionRow;
        String informationPanelActionColumn = "" + actionColumn;
        String informationPanelPlayerStyle = "false";
        String informationPanelPath = "false";
        
        if(pathMap.get(Integer.parseInt(e.getActionCommand())).isStartTile() == true)
        {
            informationPanelStartTile ="true";
        }
        if(pathMap.get(Integer.parseInt(e.getActionCommand())).isEndTile() == true)
        {
            informationPanelEndTile = "true";
        }
        if(pathMap.get(Integer.parseInt(e.getActionCommand())).getPlayer() != null)
        {
            informationPanelPlayerStyle = pathMap.get(Integer.parseInt(e.getActionCommand())).getPlayer().getType();
        }
        if(pathMap.get(Integer.parseInt(e.getActionCommand())) != null)
        {
            informationPanelPath = "true";
        }
        informationPanel.path.setText("isPath:" + informationPanelPath);
        informationPanel.startTile.setText("isStartTile: "+ informationPanelStartTile);
        informationPanel.endTile.setText("isEndTile:" + informationPanelEndTile);
        informationPanel.commandCounter.setText("commandCounter:" + informationPanelCommandCounter);
        informationPanel.actionRow.setText("actionRow:" + informationPanelActionRow);
        informationPanel.actionColumn.setText("actionColum:" + informationPanelActionColumn);
        informationPanel.playerStyle.setText("playerStyle:" + informationPanelPlayerStyle);
        
        
        
        
        if(currentPlayer == numberofPlayers-1)
        {
            currentPlayer = 0;
        }
        //here is to get where the event happen
        System.out.println("Event happend at" + e.getActionCommand());
        //here is to code the moving part
        
        //make sure the user click on the path
        if(pathMap.get(Integer.parseInt(e.getActionCommand())) != null)
        {
            int moveDistance;
            SortedMap<Integer,TileInformation> temp = null;        
            if(userLocation.get(currentPlayer) < Integer.parseInt(e.getActionCommand()))
            {
                temp = pathMap.subMap(userLocation.get(currentPlayer), Integer.parseInt(e.getActionCommand()));
            }
            else
            {
                temp = pathMap.subMap(Integer.parseInt(e.getActionCommand()), userLocation.get(currentPlayer));
            }
            moveDistance = temp.size();
            if(moveDistance > pathMap.get(userLocation.get(currentPlayer)).getPlayer().getMovement())
            {
                if(pathMap.get(Integer.parseInt(e.getActionCommand())).isEndTile())
                {
                    System.out.println("player has finished this map");
                    
                }
                else
                {
                    pathMap.get(userLocation.get(currentPlayer)).setStartTile(false);
                    Player p = pathMap.get(userLocation.get(currentPlayer)).getPlayer();
                    pathMap.get(userLocation.get(currentPlayer)).setPlayer(null);
                    pathMap.get(Integer.parseInt(e.getActionCommand())).setPlayer(p);
                    pathMap.get(Integer.parseInt(e.getActionCommand())).setStartTile(true);
                    Tile[userLocation.get(currentPlayer)].setIcon(null);
                    //Tile[Integer.parseInt(e.getActionCommand())].setIcon(null);
                }
            }
        }
        else
        {
            System.out.println("hey fucker you can not move to their!");
        }
        currentPlayer++;
    }
    
}
