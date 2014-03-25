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
   int levelSum = 0;
   int aveLevel = 0;
   public MapInformation BuildMap(MapInformation mapInformation) throws Exception
   {
      this.mapInformation = mapInformation;
       if(mapInformation.getUserLocation().size() == 0) // it is a new map
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
       
       
       
       
        for(Map.Entry<Integer,TileInformation> entry : mapInformation.getPathMap().entrySet())
           {
               if(entry.getValue().getEnemy() != null)
              {
                  System.out.println("this is the monsters level " + entry.getValue().getEnemy().getLevel());
              }
           }
       System.out.println("average level " + aveLevel);
       
       
       return mapInformation;
   }
   
}
