/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MapInformation {
    @XmlTransient
    private HashMap<Integer,TileInformation> pathMap = new HashMap<Integer,TileInformation>();
    private int rows;
    private int columns;
    @XmlElement(required = true)
    private String mapName;
    @XmlElementWrapper(name = "path")
    private LinkedHashSet<TileInformation> path = new LinkedHashSet<TileInformation>();
    @XmlElementWrapper(name = "startPointInfo")
    private LinkedHashSet<TileInformation> startPointInfo = new LinkedHashSet<TileInformation>();
    @XmlElementWrapper(name = "enemyPathInfo")
    private LinkedHashSet<TileInformation> enemyPathInfo = new LinkedHashSet<TileInformation>();
    @XmlElementWrapper(name = "endPointInfo")
    private LinkedHashSet<TileInformation> endPointInfo = new LinkedHashSet<TileInformation>();

    public LinkedHashSet<TileInformation> getPath() {
        return path;
    }

    public void setPath(LinkedHashSet<TileInformation> path) {
        this.path = path;
    }

    public LinkedHashSet<TileInformation> getStartPointInfo() {
        return startPointInfo;
    }

    public void setStartPointInfo(LinkedHashSet<TileInformation> startPointInfo) {
        this.startPointInfo = startPointInfo;
    }

    public LinkedHashSet<TileInformation> getEnemyPathInfo() {
        return enemyPathInfo;
    }

    public void setEnemyPathInfo(LinkedHashSet<TileInformation> enemyPathInfo) {
        this.enemyPathInfo = enemyPathInfo;
    }

    public LinkedHashSet<TileInformation> getEndPointInfo() {
        return endPointInfo;
    }

    public void setEndPointInfo(LinkedHashSet<TileInformation> endPointInfo) {
        this.endPointInfo = endPointInfo;
    }
    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.mapName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MapInformation other = (MapInformation) obj;
        if (!Objects.equals(this.mapName, other.mapName)) {
            return false;
        }
        return true;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public HashMap<Integer, TileInformation> getPathMap() {
        return pathMap;
    }

    public void setPathMap(HashMap<Integer, TileInformation> pathMap) {
        this.pathMap = pathMap;
    }
}
