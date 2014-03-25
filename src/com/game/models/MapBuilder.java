/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import static com.game.models.Configuration.defaultLevel;
import static com.game.models.Configuration.defaultTreasureValue;
import com.game.util.GameUtils;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author shuangshuangzhao
 */
public class MapBuilder 
{
   private MapInformation mapInformation;
   private MapBuilder(MapInformation mapInformation) 
   {
       this.mapInformation = mapInformation;
       if(mapInformation.getUserLocation() == null) // it is a new map
       {
           for (Map.Entry<Integer,TileInformation> entry : mapInformation.getPathMap().entrySet())
           {
              if(entry.getValue().getEnemy() != null)
              {
                  entry.getValue().getEnemy().setLevel(defaultLevel);
              }
           }
       }
       else //it is a map saved before
       {
           int levelSum = 0;
           int aveLevel = 0;
           for(Map.Entry<Integer,Integer> entry : mapInformation.getUserLocation().entrySet())
           {
               levelSum = levelSum + mapInformation.getPathMap().get(entry.getValue()).getPlayer().getLevel();
           }
           aveLevel = levelSum / mapInformation.getUserLocation().size();
           for(Map.Entry<Integer,TileInformation> entry : mapInformation.getPathMap().entrySet())
           {
               if(entry.getValue().getEnemy() != null)
              {
                  entry.getValue().getEnemy().setLevel(aveLevel);
              }
           }
       }
       for(Map.Entry<Integer,TileInformation> entry : mapInformation.getPathMap().entrySet())
       {
           if(entry.getValue().getTreasure() != null)
           {
               entry.getValue().getTreasure().setValue(defaultTreasureValue);
           }
       }
   }
   
   public MapInformation BuildMap(String fileName, String mapName) throws Exception
   {
       new MapBuilder(GameUtils.fetchParticularMapData(fileName, mapName));
       return mapInformation;
   }
   
}
