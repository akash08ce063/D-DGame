/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.xml.models;

import com.game.models.TileInformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Kaushik
 */
public class MapAdapterForMapInformation extends XmlAdapter<TileInfoWrapper, HashMap<Integer,TileInformation>>{

    @Override
    public HashMap<Integer, TileInformation> unmarshal(TileInfoWrapper vt) throws Exception {
        HashMap<Integer,TileInformation> map = new HashMap<Integer,TileInformation>();
        for(TileInfoInterim t : vt.getList()){
            map.put(t.getKey(), t.getTileInfo());
        }
        return map;
    }

    @Override
    public TileInfoWrapper marshal(HashMap<Integer, TileInformation> bt) throws Exception {
        TileInfoWrapper wrapper = new TileInfoWrapper();
        ArrayList<TileInfoInterim> list = new ArrayList<TileInfoInterim>();
        for(Map.Entry<Integer,TileInformation> entry : bt.entrySet()){
            TileInfoInterim dummy = new TileInfoInterim();
            dummy.setKey(entry.getKey());
            dummy.setTileInfo(entry.getValue());
            list.add(dummy);
        }
        wrapper.setList(list);
        return wrapper;
    }
    
}
