/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kaushik
 */
@XmlRootElement(name = "MapDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapWrapper {
    @XmlElementWrapper(name = "Map")
    LinkedHashSet<MapInformation> mapDetails = new LinkedHashSet<MapInformation>();

    public LinkedHashSet<MapInformation> getMapDetails() {
        return mapDetails;
    }

    public void setMapDetails(LinkedHashSet<MapInformation> mapDetails) {
        this.mapDetails = mapDetails;
    }
}
