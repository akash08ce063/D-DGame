/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.xml.main;

import com.game.models.Configuration;
import com.game.models.MapInformation;
import com.game.models.TileInformation;
import com.game.util.GameUtils;
import com.game.xml.models.MapInformationWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Kaushik
 */
public class MapInit {
    public static void main(String[] args) throws Exception {
        HashMap<Integer,TileInformation> map = new HashMap<>();
        LinkedHashMap<Integer,TileInformation> userLocation = new LinkedHashMap<>();
        TileInformation tile = new TileInformation();
        tile.setLocation(1);
        tile.setStartTile(true);
        map.put(1, tile);
        userLocation.put(0, tile);
        tile = new TileInformation();
        tile.setLocation(2);
        tile.setEndTile(true);
        map.put(2, tile);
        tile = new TileInformation();
        tile.setLocation(3);
        tile.setEndTile(true);
        map.put(3, tile);
        MapInformation mapInfo = new MapInformation();
        mapInfo.setRows(4);
        mapInfo.setColumns(4);
        mapInfo.setPathMap(map);
        mapInfo.setUserLocation(userLocation);
        mapInfo.setMapName("tesst1");
        MapInformationWrapper wrapper = new MapInformationWrapper();
        ArrayList<MapInformation> list = new ArrayList<MapInformation>();
        list.add(mapInfo);
        mapInfo = new MapInformation();
        mapInfo.setRows(4);
        mapInfo.setColumns(4);
        mapInfo.setPathMap(map);
        mapInfo.setUserLocation(userLocation);
        mapInfo.setMapName("tesst2");
        list.add(mapInfo);
        wrapper.setMapList(list);
        GameUtils.writeMapInformation(wrapper, Configuration.PATH_FOR_MAP);
        wrapper = GameUtils.readMapInformation(Configuration.PATH_FOR_MAP);
        System.out.println(wrapper.getMapList());
//        System.out.println(mapInfo.getColumns());
//        System.out.println(mapInfo.getPathMap());
//        System.out.println(mapInfo.getUserLocation());
    }
}
