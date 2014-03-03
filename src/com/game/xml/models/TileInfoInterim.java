/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.xml.models;

import com.game.models.TileInformation;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TileInfoInterim {
    private Integer key;
    private TileInformation tileInfo;
    public TileInfoInterim(){
        
    }
    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public TileInformation getTileInfo() {
        return tileInfo;
    }

    public void setTileInfo(TileInformation tileInfo) {
        this.tileInfo = tileInfo;
    }
    
}
