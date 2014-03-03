/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.xml.models;

import java.util.ArrayList;

/**
 *
 * @author Kaushik
 */
public class TileInfoWrapper {
    private ArrayList<TileInfoInterim> list = new ArrayList<TileInfoInterim>();

    public ArrayList<TileInfoInterim> getList() {
        return list;
    }

    public void setList(ArrayList<TileInfoInterim> list) {
        this.list = list;
    }
}
